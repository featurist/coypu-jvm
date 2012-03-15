package Coypu.Robustness;
import Coypu.TimeSpan;

public class StopwatchWaiter implements Waiter
{
    public void Wait( TimeSpan duration)
    {
        Stopwatch stopWatch = Stopwatch.StartNew();
        while(stopWatch.ElapsedMilliseconds < duration.TotalMilliseconds)
        {
        }
        stopWatch.Stop();
    }
}