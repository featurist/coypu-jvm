package Coypu.Tests.When_making_browser_interactions_robust;

import Coypu.Options;
import Coypu.Robustness.RetryUntilTimeoutRobustWrapper;
import Coypu.Stopwatch;
import Coypu.Tests.TestException;
import Coypu.TimeSpan;
import Coypu.TimeoutException;
import org.junit.Before;
import org.junit.Test;

import static Coypu.Tests.When_interacting_with_the_browser.InRange.inRange;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_trying_an_action_until_a_given_state_is_reached
{
    private RetryUntilTimeoutRobustWrapper retryUntilTimeoutRobustWrapper;
    private Options options;

    @Before
    public void setUp()
    {
        options = new Options();
        options.Timeout = TimeSpan.fromMilliseconds(200);
        options.RetryInterval = TimeSpan.fromMilliseconds(10);
        retryUntilTimeoutRobustWrapper = new RetryUntilTimeoutRobustWrapper();
    }

    @Test
    public void when_state_exists_It_returns_immediately()
    {
        CountTriesAction toTry = new CountTriesAction(options);
        TimeSpan retryInterval = TimeSpan.fromMilliseconds(10);

        Options predicateOptions = new Options();
        predicateOptions.Timeout = TimeSpan.zero();
        predicateOptions.RetryInterval = retryInterval;
        AlwaysSucceedsPredicateQuery until = new AlwaysSucceedsPredicateQuery(true,predicateOptions);

        retryUntilTimeoutRobustWrapper.tryUntil(toTry, until,TimeSpan.fromMilliseconds(20), retryInterval);

        assertThat(toTry.getTries(), is(equalTo(1)));
    }

    @Test
    public void when_state_exists_after_three_tries_It_tries_three_times()
    {
        options.Timeout = TimeSpan.fromMilliseconds(1000);
        options.RetryInterval = TimeSpan.fromMilliseconds(10);
        
        CountTriesAction toTry = new CountTriesAction(options);
        ThrowsThenSubsequentlySucceedsPredicateQuery until = new ThrowsThenSubsequentlySucceedsPredicateQuery(true, 2, options);

        retryUntilTimeoutRobustWrapper.tryUntil(toTry, until, TimeSpan.fromMilliseconds(100), options.RetryInterval);

        assertThat(toTry.getTries(), is(equalTo(3)));
    }

    @Test
    public void when_state_never_exists_It_fails_after_timeout()
    {
        CountTriesAction toTry = new CountTriesAction(options);
        AlwaysSucceedsPredicateQuery until = new AlwaysSucceedsPredicateQuery(false, TimeSpan.zero(), options.RetryInterval);

        Stopwatch stopwatch = Stopwatch.startNew();
        TimeSpan timeout = TimeSpan.fromMilliseconds(200);
        
        try {
            retryUntilTimeoutRobustWrapper.tryUntil(toTry, until, timeout, options.RetryInterval);
            fail("Expected TimeoutException");
        }
        catch (TimeoutException ex) {}

        stopwatch.stop();
        long elapsedMilliseconds = stopwatch.getElapsedMilliseconds();

        assertThat(elapsedMilliseconds, is(inRange(timeout.getMilliseconds() - (options.RetryInterval.getMilliseconds() + When_waiting.AccuracyMilliseconds),
                                                    timeout.getMilliseconds() + (options.RetryInterval.getMilliseconds() + When_waiting.AccuracyMilliseconds))));
    }

    @Test
    public void it_applies_the_retryAfter_timeout_within_until()
    {
        CountTriesAction toTry = new CountTriesAction(options);
        TimeSpan retryAfter = TimeSpan.fromMilliseconds(20);
        TestException exception = new TestException("From tests");
        AlwaysThrowsPredicateQuery until = new AlwaysThrowsPredicateQuery(options.Timeout, retryAfter, exception);

        try {
            retryUntilTimeoutRobustWrapper.tryUntil(toTry, until, options.Timeout, options.RetryInterval);
        }
        catch (TestException ex){
            assertThat(ex, is(sameInstance(exception)));
        }

        assertThat((long) toTry.getTries(), is(inRange(1L,12L)));
    }
}
