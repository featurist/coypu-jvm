package coypu.Robustness;

import coypu.Actions.BrowserAction;
import coypu.Queries.PredicateQuery;
import coypu.Queries.Query;
import coypu.TimeSpan;

public interface RobustWrapper {
    public <T> T robustly(Query<T> query);

    public void tryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan overallTimeout, TimeSpan waitBeforeRetry);

    public boolean getZeroTimeout();

    public void setZeroTimeout(boolean value);

    public void setOverrideTimeout(TimeSpan timeout);

    public void clearOverrideTimeout();
}
