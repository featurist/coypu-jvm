package Coypu.Robustness;

import Coypu.Actions.BrowserAction;
import Coypu.MissingHtmlException;
import Coypu.Queries.Query;
import Coypu.TimeSpan;
import Coypu.TimeoutException;

public interface RobustWrapper
{
    public <T> T Robustly(Query<T> query) throws MissingHtmlException;
    public void TryUntil(BrowserAction tryThis, Query<Boolean> until, TimeSpan overallTimeout, TimeSpan waitBeforeRetry) throws MissingHtmlException, TimeoutException;
    public boolean GetZeroTimeout();
    public void SetZeroTimeout(boolean value);
    public void SetOverrideTimeout(TimeSpan timeout);
    public void ClearOverrideTimeout();
}
