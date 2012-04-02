package coypu.Robustness;

import coypu.Actions.BrowserAction;
import coypu.Queries.ActionSatisfiesPredicateQuery;
import coypu.Queries.PredicateQuery;
import coypu.Queries.Query;
import coypu.Stopwatch;
import coypu.TimeSpan;
import coypu.TimeoutException;

public class RetryUntilTimeoutRobustWrapper implements RobustWrapper {
    private boolean zeroTimeout;
    private TimeSpan overrideTimeout;

    public boolean getZeroTimeout() {
        return zeroTimeout;
    }

    public void setZeroTimeout(boolean value) {
        zeroTimeout = value;
    }

    public void setOverrideTimeout(TimeSpan timeout) {
        overrideTimeout = timeout;
    }

    public void clearOverrideTimeout() {
        overrideTimeout = null;
    }

    public void tryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan overallTimeout, TimeSpan waitBeforeRetry) {
        boolean outcome = robustly(new ActionSatisfiesPredicateQuery(tryThis, until, overallTimeout, until.getRetryInterval(), waitBeforeRetry, this));
        if (!outcome)
            throw new TimeoutException("Timeout from TryUntil: the page never reached the required state.");
    }

    public <TResult> TResult robustly(Query<TResult> query) {
        TimeSpan interval = query.getRetryInterval();
        TimeSpan timeout = timeout(query);
        Stopwatch stopWatch = Stopwatch.startNew();
        while (true) {
            try {
                TResult result = query.run();
                if (expectedResultNotFoundWithinTimeout(query.getExpectedResult(), result, stopWatch, timeout, interval)) {
                    waitForInterval(interval);
                    continue;
                }
                return result;
            } catch (UnsupportedOperationException ex) {
                throw ex;
            } catch (Exception ex) {
                if (timeoutReached(stopWatch, timeout, interval)) {
                    if (ex instanceof RuntimeException)
                        throw (RuntimeException) ex;
                    else
                        throw new RuntimeException(ex);
                }
                waitForInterval(interval);
            }
        }
    }

    private <TResult> TimeSpan timeout(Query<TResult> query) {
        TimeSpan timeout;
        if (zeroTimeout) {
            timeout = TimeSpan.zero();
        } else if (overrideTimeout != null) {
            timeout = overrideTimeout;
        } else {
            timeout = query.getTimeout();
        }
        return timeout;
    }

    private void waitForInterval(TimeSpan interval) {
        try {
            Thread.sleep(interval.getMilliseconds());
        } catch (InterruptedException e) {
            // TODO: What is this?
        }
    }

    private <TResult> boolean expectedResultNotFoundWithinTimeout(Object expectedResult, TResult result, Stopwatch stopWatch, TimeSpan timeout, TimeSpan interval) {
        return expectedResult != null && !result.equals(expectedResult) && !timeoutReached(stopWatch, timeout, interval);
    }

    private boolean timeoutReached(Stopwatch stopWatch, TimeSpan timeout, TimeSpan interval) {
        long elapsedTimeToNextCall = stopWatch.getElapsedMilliseconds() + interval.getMilliseconds();

        boolean timeoutReached = elapsedTimeToNextCall >= timeout.getMilliseconds();

        return timeoutReached;
    }
}
