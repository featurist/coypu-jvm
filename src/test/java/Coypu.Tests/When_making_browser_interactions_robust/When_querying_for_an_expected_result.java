package Coypu.Tests.When_making_browser_interactions_robust;

import Coypu.Robustness.RetryUntilTimeoutRobustWrapper;
import Coypu.TimeSpan;
import org.junit.Before;
import org.junit.Test;

import static Coypu.Tests.When_interacting_with_the_browser.InRange.inRange;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class When_querying_for_an_expected_result
{
    private RetryUntilTimeoutRobustWrapper retryUntilTimeoutRobustWrapper;
    private TimeSpan retryInterval;

    @Before
    public void setUp()
    {
        retryInterval = TimeSpan.fromMilliseconds(10);
        retryUntilTimeoutRobustWrapper = new RetryUntilTimeoutRobustWrapper();
    }

    @Test
    public void when_the_expected_result_is_found_It_returns_the_expected_result_immediately()
    {
        Object expectedResult = new Object();

        Object actualResult = retryUntilTimeoutRobustWrapper.robustly(new AlwaysSucceedsQuery(expectedResult, expectedResult, TimeSpan.fromMilliseconds(200), retryInterval));

        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void when_the_expected_result_is_never_found_It_returns_the_actual_result_after_timeout()
    {
        TimeSpan expectedTimeout = TimeSpan.fromMilliseconds(250);
        int retryIntervalMS = 70;
        retryInterval = TimeSpan.fromMilliseconds(retryIntervalMS);

        Object expectedResult = new Object();
        Object unexpectedResult = new Object();

        AlwaysSucceedsQuery query = new AlwaysSucceedsQuery(unexpectedResult, expectedResult, expectedTimeout, TimeSpan.fromMilliseconds(retryIntervalMS));

        Object actualResult = retryUntilTimeoutRobustWrapper.robustly(query);

        assertThat(actualResult, is(equalTo(unexpectedResult)));
        assertThat(query.getLastCall(), is(inRange(expectedTimeout.getMilliseconds() - retryIntervalMS,
                                                   expectedTimeout.getMilliseconds() + retryIntervalMS)));
    }

    @Test
    public void when_exceptions_are_always_thrown_It_rethrows_eventually()
    {
        RuntimeException exception = new RuntimeException();
        AlwaysThrowsQuery query = new AlwaysThrowsQuery(TimeSpan.fromMilliseconds(200), retryInterval, exception);
        try {
            retryUntilTimeoutRobustWrapper.robustly(query);
            fail("expected the exception to be rethrown eventually");
        }
        catch (Exception e) {
            return;
        }
    }

    @Test
    public void when_exceptions_are_thrown_It_retries_And_when_expected_result_found_subsequently_It_returns_expected_result_immediately()
    {
        int throwsHowManyTimes = 2;
        Object expectedResult = new Object();
        ThrowsThenSubsequentlySucceedsQuery query = new ThrowsThenSubsequentlySucceedsQuery(expectedResult, expectedResult, throwsHowManyTimes, TimeSpan.fromMilliseconds(100),retryInterval);

        assertThat(retryUntilTimeoutRobustWrapper.robustly(query), is(equalTo(expectedResult)));
        assertThat(query.Tries, is(equalTo(throwsHowManyTimes + 1)));
    }

    @Test
    public void when_a_not_supported_exception_is_thrown_It_does_not_retry()
    {
        UnsupportedOperationException exception = new UnsupportedOperationException();
        AlwaysThrowsQuery<Object> throwsNotSupported = new AlwaysThrowsQuery(TimeSpan.fromMilliseconds(200), retryInterval, exception);

        try {
            retryUntilTimeoutRobustWrapper.robustly(throwsNotSupported);
            fail("Expected UnsupportedOperationException");
        }
        catch(UnsupportedOperationException ex) {
            assertThat(throwsNotSupported.getTries(), is(equalTo(1)));
        }
    }

    @Test
    public void when_exceptions_are_thrown_It_retries_And_when_unexpected_result_found_subsequently_It_keeps_looking_for_expected_result_But_returns_unexpected_result_after_timeout()
    {
        TimeSpan expectedTimeout = TimeSpan.fromMilliseconds(250);
        int retryIntervalMS = 70;
        retryInterval = TimeSpan.fromMilliseconds(retryIntervalMS);

        Object expectedResult = new Object();
        Object unexpectedResult = new Object();

        ThrowsThenSubsequentlySucceedsQuery<Object> throwsTwiceTimesThenReturnOppositeResult = 
                new ThrowsThenSubsequentlySucceedsQuery<Object>(unexpectedResult, expectedResult, 2, expectedTimeout, retryInterval);

        assertThat(retryUntilTimeoutRobustWrapper.robustly(throwsTwiceTimesThenReturnOppositeResult), is(equalTo(unexpectedResult)));
        assertThat(throwsTwiceTimesThenReturnOppositeResult.Tries >= 3, is(true));
        assertThat(throwsTwiceTimesThenReturnOppositeResult.LastCall, is(inRange(expectedTimeout.getMilliseconds() - retryIntervalMS,
                expectedTimeout.getMilliseconds() + retryIntervalMS)));
    }
}

