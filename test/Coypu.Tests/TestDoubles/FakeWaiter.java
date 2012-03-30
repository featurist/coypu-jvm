package Coypu.Tests.TestDoubles;

import Coypu.Robustness.Waiter;
import Coypu.TimeSpan;

public class FakeWaiter implements Waiter
{
    Action doOnWait = new Action<TimeSpan>() {
        @Override
        public void act(TimeSpan on) {
        }
    };

    public void Wait(TimeSpan duration)
    {
        doOnWait.act(duration);
    }

    public void DoOnWait(Action action)
    {
        doOnWait = action;
    }
}
