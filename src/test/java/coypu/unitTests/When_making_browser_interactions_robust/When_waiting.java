package coypu.unitTests.When_making_browser_interactions_robust;

import coypu.Robustness.StopwatchWaiter;
import coypu.Stopwatch;
import coypu.TimeSpan;
import org.junit.Test;

import static coypu.unitTests.When_interacting_with_the_browser.InRange.inRange;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_waiting
{
    public static final int AccuracyMilliseconds = 30;

    @Test
    public void it_sleeps_for_the_expected_time_Case_1()
    {
        it_sleeps_for_the_expected_time(100);
    }

    @Test
    public void it_sleeps_for_the_expected_time_Case_2()
    {
        it_sleeps_for_the_expected_time(200);
    }

    public void it_sleeps_for_the_expected_time(long expectedDurationMilliseconds)
    {
        StopwatchWaiter waiter = new StopwatchWaiter();
        Stopwatch stopWatch = Stopwatch.startNew();
        TimeSpan expectedDuration = TimeSpan.fromMilliseconds(expectedDurationMilliseconds);

        waiter.wait(expectedDuration);

        long actualWait = stopWatch.getElapsedMilliseconds();

        int toleranceMilliseconds = AccuracyMilliseconds;

        assertThat(actualWait, is(inRange(expectedDurationMilliseconds - toleranceMilliseconds,
                                          expectedDurationMilliseconds + toleranceMilliseconds)));
    }
}