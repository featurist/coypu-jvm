package Coypu.Tests.TestDoubles;

import Coypu.Actions.BrowserAction;
import Coypu.Queries.PredicateQuery;
import Coypu.Queries.Query;
import Coypu.Robustness.RobustWrapper;
import Coypu.TimeSpan;

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
