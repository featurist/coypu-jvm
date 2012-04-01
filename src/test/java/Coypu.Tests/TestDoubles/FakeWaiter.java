package Coypu.Tests.TestDoubles;

import Coypu.Robustness.Waiter;
import Coypu.TimeSpan;

public class FakeWaiter implements Waiter
{
    Action doOnWait = new Action<TimeSpan>() {
        @Override
        public void invoke(TimeSpan on) {}
    };

    public void wait(TimeSpan duration)
    {
        doOnWait.invoke(duration);
    }

    public void doOnWait(Action action)
    {
        doOnWait = action;
    }
}
