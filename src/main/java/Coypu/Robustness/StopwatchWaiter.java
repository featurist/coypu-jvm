package Coypu.Robustness;

import Coypu.Stopwatch;
import Coypu.TimeSpan;

public class StopwatchWaiter implements Waiter {
    public void wait(TimeSpan duration) {
        Stopwatch stopWatch = Stopwatch.startNew();
        while (stopWatch.getElapsedMilliseconds() < duration.getMilliseconds()) {
        }
        stopWatch.stop();
    }
}
