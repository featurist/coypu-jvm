package Coypu.Robustness;

import Coypu.Actions.BrowserAction;
import Coypu.MissingHtmlException;
import Coypu.Queries.ActionSatisfiesPredicateQuery;
import Coypu.Queries.Query;
import Coypu.Stopwatch;
import Coypu.TimeSpan;
import Coypu.TimeoutException;

public class RetryUntilTimeoutRobustWrapper implements RobustWrapper
{
    private boolean zeroTimeout;
    private TimeSpan overrideTimeout;

    public boolean GetZeroTimeout()
    {
        return zeroTimeout;
    }

    public void SetZeroTimeout(boolean value)
    {
        zeroTimeout = value;
    }

    public void SetOverrideTimeout(TimeSpan timeout)
    {
        overrideTimeout = timeout;
    }

    public void ClearOverrideTimeout()
    {
        overrideTimeout = null;
    }

    public void TryUntil(BrowserAction tryThis, Query<Boolean> until,  TimeSpan overallTimeout,  TimeSpan waitBeforeRetry)
            throws MissingHtmlException, TimeoutException {
        boolean outcome = Robustly(new ActionSatisfiesPredicateQuery(tryThis, until, overallTimeout, until.RetryInterval(), waitBeforeRetry, this));
        if (!outcome)
            throw new MissingHtmlException("Timeout from TryUntil: the page never reached the required state.");
    }

    public <TResult> TResult Robustly(Query<TResult> query) throws TimeoutException {
        TimeSpan interval = query.RetryInterval();
        TimeSpan timeout = Timeout(query);
        Stopwatch stopWatch = Stopwatch.StartNew();
        while (true)
        {
            try
            {
                query.Run();
                TResult result = query.Result();
                if (ExpectedResultNotFoundWithinTimeout(query.ExpectedResult(), result, stopWatch, timeout, interval))
                {
                    WaitForInterval(interval);
                    continue;
                }
                return result;
            }
            catch (UnsupportedOperationException ex) { throw ex; }
            catch (Exception ex)
            {
                if (TimeoutReached(stopWatch, timeout, interval))
                {
                    throw new TimeoutException(ex);
                }
                WaitForInterval(interval);
            }
        }
    }

    private <TResult> TimeSpan Timeout(Query<TResult> query)
    {
        TimeSpan timeout;
        if (zeroTimeout)
        {
            timeout = TimeSpan.Zero();
        }
        else if (overrideTimeout != null)
        {
            timeout = overrideTimeout;
        }
        else
        {
            timeout = query.Timeout();
        }
        return timeout;
    }

    private void WaitForInterval( TimeSpan interval) {
        try {
            Thread.sleep(interval.getMilliseconds());
        } catch (InterruptedException e) {
            // TODO: What is this?
        }
    }

    private <TResult> boolean ExpectedResultNotFoundWithinTimeout(Object expectedResult, TResult result, Stopwatch stopWatch,  TimeSpan timeout,  TimeSpan interval)
    {
        return expectedResult != null && !result.equals(expectedResult) && !TimeoutReached(stopWatch, timeout, interval);
    }

    private boolean TimeoutReached(Stopwatch stopWatch,  TimeSpan timeout,  TimeSpan interval)
    {
        long elapsedTimeToNextCall = stopWatch.getElapsedMilliseconds() + interval.getMilliseconds();
        boolean timeoutReached = elapsedTimeToNextCall >= timeout.getMilliseconds();

        return timeoutReached;
    }
}
