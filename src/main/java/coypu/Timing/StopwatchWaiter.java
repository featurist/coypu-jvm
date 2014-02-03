//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:19
//

package coypu.Timing;

import CS2JNet.System.TimeSpan;

public class StopwatchWaiter   implements Waiter
{
    public void Wait(TimeSpan duration) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ stopWatch = Stopwatch.StartNew();
        while (stopWatch.ElapsedMilliseconds < duration.TotalMilliseconds)
        {
        }
        stopWatch.Stop();
    }

}


