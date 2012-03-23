package Coypu.Robustness;
import Coypu.Stopwatch;
import Coypu.TimeSpan;

public class StopwatchWaiter implements Waiter
{
    public void Wait( TimeSpan duration)
    {
        Stopwatch stopWatch = Stopwatch.StartNew();
        while(stopWatch.getElapsedMilliseconds() < duration.getMilliseconds())
        {
        }
        stopWatch.stop();
    }
}
