package coypu.unitTests.TestDoubles;

import coypu.Actions.BrowserAction;
import coypu.Queries.PredicateQuery;
import coypu.Queries.Query;
import coypu.Robustness.RobustWrapper;
import coypu.TimeSpan;

public class ImmediateSingleExecutionFakeRobustWrapper implements RobustWrapper
{
    private boolean zeroTimeout;

    public <T> T robustly(Query<T> query)
    {
        return query.run();
    }

    public void tryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan overallTimeout, TimeSpan waitBeforeRetry)
    {
        tryThis.act();
    }

    public boolean getZeroTimeout() {
        return zeroTimeout;
    }

    public void setZeroTimeout(boolean value) {
        zeroTimeout = value;
    }

    public void setOverrideTimeout(TimeSpan timeout)
    {
    }

    public void clearOverrideTimeout()
    {
    }
}
