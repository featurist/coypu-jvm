package Coypu.Robustness;

import Coypu.Actions.BrowserAction;
import Coypu.Queries.Query;
import Coypu.TimeSpan;

public interface RobustWrapper
{
    T Robustly<T>(Query<T> query);
    void TryUntil(BrowserAction tryThis, Query<Boolean> until, TimeSpan overallTimeout, TimeSpan waitBeforeRetry);
    boolean GetZeroTimeout();
    void SetZeroTimeout(boolean value);
    void SetOverrideTimeout(TimeSpan timeout);
    void ClearOverrideTimeout();
}
