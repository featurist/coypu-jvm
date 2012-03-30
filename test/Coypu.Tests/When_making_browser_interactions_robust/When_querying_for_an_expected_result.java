package Coypu.Tests.When_making_browser_interactions_robust;

import Coypu.Robustness.RetryUntilTimeoutRobustWrapper;
import Coypu.TimeSpan;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class When_querying_for_an_expected_result
{
    private RetryUntilTimeoutRobustWrapper retryUntilTimeoutRobustWrapper;
    private TimeSpan retryInterval;

    @Before
    public void SetUp()
    {
        retryInterval = TimeSpan.FromMilliseconds(10);
        retryUntilTimeoutRobustWrapper = new RetryUntilTimeoutRobustWrapper();
    }

    @Test
    public void When_the_expected_result_is_found_It_returns_the_expected_result_immediately()
    {
        Object expectedResult = new Object();

        Object actualResult = retryUntilTimeoutRobustWrapper.Robustly(new AlwaysSucceedsQuery(expectedResult, expectedResult, TimeSpan.FromMilliseconds(200), retryInterval));

        assertThat(actualResult, is(expectedResult));
    }

    @Test
    public void When_the_expected_result_is_never_found_It_returns_the_actual_result_after_timeout()
    {
        TimeSpan expectedTimeout = TimeSpan.FromMilliseconds(250);
        int retryIntervalMS = 70;
        retryInterval = TimeSpan.FromMilliseconds(retryIntervalMS);

        Object expectedResult = new Object();
        Object unexpectedResult = new Object();

        AlwaysSucceedsQuery query = new AlwaysSucceedsQuery<Object>(unexpectedResult, expectedResult, expectedTimeout, TimeSpan.FromMilliseconds(retryIntervalMS));

        Object actualResult = retryUntilTimeoutRobustWrapper.Robustly(query);

        assertThat(actualResult, Is.EqualTo(unexpectedResult));
        assertThat(query.LastCall, Is.InRange(expectedTimeout.Milliseconds - retryIntervalMS,
                                         expectedTimeout.Milliseconds + retryIntervalMS));
    }

    @Test
    public void When_exceptions_are_always_thrown_It_rethrows_eventually()
    {
        RuntimeException exception = new RuntimeException();
        AlwaysThrowsQuery query = new AlwaysThrowsQuery(TimeSpan.FromMilliseconds(200), retryInterval, exception);
        try {
            retryUntilTimeoutRobustWrapper.Robustly(query);
            fail("expected the exception to be rethrown eventually");
        }
        catch (Exception e) {
            return;
        }
    }

    @Test
    public void When_exceptions_are_thrown_It_retries_And_when_expected_result_found_subsequently_It_returns_expected_result_immediately()
    {
        int throwsHowManyTimes = 2;
        Object expectedResult = new Object();
        var query = new ThrowsThenSubsequentlySucceedsQuery(expectedResult, expectedResult, throwsHowManyTimes, TimeSpan.FromMilliseconds(100),retryInterval);

        assertThat(retryUntilTimeoutRobustWrapper.Robustly(query), Is.EqualTo(expectedResult));
        assertThat(query.Tries, Is.EqualTo(throwsHowManyTimes + 1));
    }

    @Test
    public void When_a_not_supported_exception_is_thrown_It_does_not_retry()
    {
        var throwsNotSupported = new AlwaysThrowsQuery<object, NotSupportedException>(TimeSpan.FromMilliseconds(200),retryInterval);

        Assert.Throws<NotSupportedException>(() => retryUntilTimeoutRobustWrapper.Robustly(throwsNotSupported));
        assertThat(throwsNotSupported.Tries, Is.EqualTo(1));
    }

    @Test
    public void When_exceptions_are_thrown_It_retries_And_when_unexpected_result_found_subsequently_It_keeps_looking_for_expected_result_But_returns_unexpected_result_after_timeout()
    {
        var expectedTimeout = TimeSpan.FromMilliseconds(250);
        const int retryIntervalMS = 70;
        retryInterval = TimeSpan.FromMilliseconds(retryIntervalMS);

        var expectedResult = new object();
        var unexpectedResult = new object();

        var throwsTwiceTimesThenReturnOppositeResult = new ThrowsThenSubsequentlySucceedsQuery<object>(unexpectedResult, expectedResult, 2, expectedTimeout,retryInterval);

        assertThat(retryUntilTimeoutRobustWrapper.Robustly(throwsTwiceTimesThenReturnOppositeResult), Is.EqualTo(unexpectedResult));
        assertThat(throwsTwiceTimesThenReturnOppositeResult.Tries, Is.GreaterThanOrEqualTo(3));
        assertThat(throwsTwiceTimesThenReturnOppositeResult.LastCall, Is.InRange(expectedTimeout.Milliseconds - retryIntervalMS,
                                                                                  expectedTimeout.Milliseconds + retryIntervalMS));
    }
}

