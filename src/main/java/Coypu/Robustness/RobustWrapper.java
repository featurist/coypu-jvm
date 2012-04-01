package Coypu.Robustness;

import Coypu.Actions.BrowserAction;
import Coypu.Queries.PredicateQuery;
import Coypu.Queries.Query;
import Coypu.TimeSpan;

public interface RobustWrapper {
    public <T> T robustly(Query<T> query);

    public void tryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan overallTimeout, TimeSpan waitBeforeRetry);

    public boolean getZeroTimeout();

    public void setZeroTimeout(boolean value);

    public void setOverrideTimeout(TimeSpan timeout);

    public void clearOverrideTimeout();
}
