package Coypu.Robustness;

import Coypu.Actions.BrowserAction;
import Coypu.MissingHtmlException;
import Coypu.Queries.Query;
import Coypu.TimeSpan;

import java.util.concurrent.TimeoutException;

public interface RobustWrapper
{
    public <T> T Robustly(Query<T> query) throws Coypu.TimeoutException, InterruptedException;
    public void TryUntil(BrowserAction tryThis, Query<Boolean> until, TimeSpan overallTimeout, TimeSpan waitBeforeRetry) throws MissingHtmlException, TimeoutException, Coypu.TimeoutException, InterruptedException;
    public boolean GetZeroTimeout();
    public void SetZeroTimeout(boolean value);
    public void SetOverrideTimeout(TimeSpan timeout);
    public void ClearOverrideTimeout();
}
