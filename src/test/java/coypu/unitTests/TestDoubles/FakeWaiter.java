package coypu.unitTests.TestDoubles;

import coypu.Robustness.Waiter;
import coypu.TimeSpan;

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
