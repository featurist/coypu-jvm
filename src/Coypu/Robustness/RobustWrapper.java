package Coypu.Robustness;

import Coypu.Actions.BrowserAction;
import Coypu.Queries.Query;
import Coypu.TimeSpan;

public interface RobustWrapper
{
    public <T> T Robustly(Query<T> query) ;
    public void TryUntil(BrowserAction tryThis, Query<Boolean> until, TimeSpan overallTimeout, TimeSpan waitBeforeRetry);
    public boolean GetZeroTimeout();
    public void SetZeroTimeout(boolean value);
    public void SetOverrideTimeout(TimeSpan timeout);
    public void ClearOverrideTimeout();
}
