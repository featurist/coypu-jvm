package Coypu.Tests.TestDoubles;

import Coypu.Actions.BrowserAction;
import Coypu.Queries.PredicateQuery;
import Coypu.Queries.Query;
import Coypu.Robustness.RobustWrapper;
import Coypu.TimeSpan;

public class ImmediateSingleExecutionFakeRobustWrapper implements RobustWrapper
{
    private boolean zeroTimeout;

    public <T> T Robustly(Query<T> query)
    {
        query.Run();
        return query.Result();
    }

    public void TryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan overallTimeout, TimeSpan waitBeforeRetry)
    {
        tryThis.Act();
    }

    public boolean GetZeroTimeout() {
        return zeroTimeout;
    }

    public void SetZeroTimeout(boolean value) {
        zeroTimeout = value;
    }

    public void SetOverrideTimeout(TimeSpan timeout)
    {
    }

    public void ClearOverrideTimeout()
    {
    }
}
