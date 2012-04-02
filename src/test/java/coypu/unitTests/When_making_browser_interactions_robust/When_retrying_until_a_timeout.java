package coypu.unitTests.When_making_browser_interactions_robust;

import coypu.Robustness.RetryUntilTimeoutRobustWrapper;
import coypu.unitTests.TestException;
import coypu.TimeSpan;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class When_retrying_until_a_timeout
{
    @Test
    public void when_a_query_succeeds_first_time_It_only_tries_once()
    {
        Object expectedResult = new Object();
        AlwaysSucceedsQuery alwaysSucceedsQuery = new AlwaysSucceedsQuery(expectedResult, TimeSpan.zero(),TimeSpan.zero());
        Object actualResult = new RetryUntilTimeoutRobustWrapper().robustly(alwaysSucceedsQuery);

        assertThat(alwaysSucceedsQuery.getTries(), is(equalTo(1)));
        assertThat(actualResult, is(sameInstance(expectedResult)));
    }

    @Test
    public void when_a_query_throws_an_exception_first_time_It_retries()
    {
        Object expectedResult = new Object();
        ThrowsSecondTimeQuery query = new ThrowsSecondTimeQuery(expectedResult, TimeSpan.fromMilliseconds(100),TimeSpan.fromMilliseconds(10));

        Object actualReturnValue = new RetryUntilTimeoutRobustWrapper().robustly(query);

        assertThat(query.getTries(), is(equalTo(2)));
        assertThat(actualReturnValue, is(sameInstance(expectedResult)));
    }

    @Test
    public void when_a_query_always_throws_an_exception_It_rethrows_eventually()
    {
        TestException exception = new TestException("From always throws");
        AlwaysThrowsQuery query = new AlwaysThrowsQuery(TimeSpan.fromMilliseconds(1000), TimeSpan.fromMilliseconds(20), exception);

        try {
            new RetryUntilTimeoutRobustWrapper().robustly(query);
        }
        catch (TestException ex) {}
        assertThat(query.getTries(), is(greaterThan(2)));
    }

//    @Test
//    public void when_a_query_always_throws_an_exception_It_retries_until_the_timeout_is_reached()
//    {
//        when_a_query_always_throws_an_exception_It_retries_until_the_timeout_is_reached(1500, 100);
//        when_a_query_always_throws_an_exception_It_retries_until_the_timeout_is_reached(300, 70);
//    }
//
//    private void when_a_query_always_throws_an_exception_It_retries_until_the_timeout_is_reached(int timeoutMilliseconds, int intervalMilliseconds)
//    {
//        var expectedTimeout = TimeSpan.fromMilliseconds(timeoutMilliseconds);
//        var getRetryInterval = TimeSpan.fromMilliseconds(intervalMilliseconds);
//
//        var query = new AlwaysThrowsQuery<object, TestException>(expectedTimeout, getRetryInterval);
//
//        var retryUntilTimeoutRobustWrapper = new RetryUntilTimeoutRobustWrapper();
//
//        try
//        {
//            retryUntilTimeoutRobustWrapper.robustly(query);
//            Assert.fail("Expecting test exception");
//        }
//        catch (TestException){}
//
//        assertThat(query.LastCall, Is.inRange(expectedTimeout.TotalMilliseconds - (getRetryInterval.Milliseconds + When_waiting.AccuracyMilliseconds),
//                expectedTimeout.TotalMilliseconds + getRetryInterval.Milliseconds + When_waiting.AccuracyMilliseconds));
//    }
//
//
//    @Test
//    public void when_a_query_throws_a_not_supported_exception_It_does_not_retry()
//    {
//        var getRetryInterval = TimeSpan.fromMilliseconds(10);
//
//        var robustness = new RetryUntilTimeoutRobustWrapper();
//
//        var query = new AlwaysThrowsQuery<object, NotSupportedException>(TimeSpan.fromMilliseconds(100), getRetryInterval);
//        Assert.Throws<NotSupportedException>(() => robustness.robustly(query));
//        assertThat(query.Tries, Is.equalTo(1));
//    }
//
//    @Test
//    public void when_an_action_succeeds_first_time_It_only_tries_once()
//    {
//        var alwaysSucceedsQuery = new AlwaysSucceedsQuery<object>(new Object(),TimeSpan.Zero,TimeSpan.Zero);
//        new RetryUntilTimeoutRobustWrapper().robustly(alwaysSucceedsQuery);
//
//        assertThat(alwaysSucceedsQuery.Tries, Is.equalTo(1));
//    }
//
//    @Test
//    public void when_an_action_throws_an_exception_first_time_It_retries()
//    {
//        var getRetryInterval = TimeSpan.fromMilliseconds(10);
//
//        var result = new Object();
//        var query = new ThrowsSecondTimeQuery<object>(result,TimeSpan.fromMilliseconds(100),getRetryInterval);
//
//        new RetryUntilTimeoutRobustWrapper().robustly(query);
//
//        assertThat(query.Tries, Is.equalTo(2));
//    }
//
//    @Test
//    public void when_an_action_always_throws_an_exception_It_rethrows_eventually()
//    {
//        var query = new AlwaysThrowsQuery<object, TestException>(TimeSpan.fromMilliseconds(100),TimeSpan.fromMilliseconds(10));
//
//        Assert.Throws<TestException>(() => new RetryUntilTimeoutRobustWrapper().robustly(query));
//        assertThat(query.Tries, Is.greaterThan(2));
//    }
//
//    @Test
//    public void when_an_action_always_throws_an_exception_It_retries_until_the_timeout_is_reached()
//    {
//        when_an_action_always_throws_an_exception_It_retries_until_the_timeout_is_reached(1500, 100);
//        when_an_action_always_throws_an_exception_It_retries_until_the_timeout_is_reached(300, 70);
//    }
//
//    private void when_an_action_always_throws_an_exception_It_retries_until_the_timeout_is_reached(int timeoutMilliseconds, int intervalMilliseconds)
//    {
//        var expectedTimeout = TimeSpan.fromMilliseconds(timeoutMilliseconds);
//        var getRetryInterval = TimeSpan.fromMilliseconds(intervalMilliseconds);
//
//        var query = new AlwaysThrowsQuery<object, TestException>(expectedTimeout,getRetryInterval);
//
//        var retryUntilTimeoutRobustWrapper = new RetryUntilTimeoutRobustWrapper();
//
//        try
//        {
//            retryUntilTimeoutRobustWrapper.robustly(query);
//            Assert.fail("Expecting test exception");
//        }
//        catch (TestException) { }
//
//        assertThat(query.LastCall, Is.inRange(expectedTimeout.TotalMilliseconds - (getRetryInterval.Milliseconds + When_waiting.AccuracyMilliseconds),
//                expectedTimeout.TotalMilliseconds + getRetryInterval.Milliseconds + When_waiting.AccuracyMilliseconds));
//    }
//
//
//    @Test
//    public void when_an_action_throws_a_not_supported_exception_It_does_not_retry()
//    {
//        var getRetryInterval = TimeSpan.fromMilliseconds(10);
//
//        var robustness = new RetryUntilTimeoutRobustWrapper();
//
//        var query = new AlwaysThrowsQuery<object, NotSupportedException>(TimeSpan.fromMilliseconds(100), getRetryInterval);
//        Assert.Throws<NotSupportedException>(() => robustness.robustly(query));
//        assertThat(query.Tries, Is.equalTo(1));
//    }
}