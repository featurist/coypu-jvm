//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:19
//

package coypu.Timing;

import coypu.Actions.BrowserAction;
import coypu.MissingHtmlException;
import coypu.Options;
import coypu.Queries.ActionSatisfiesPredicateQuery;
import coypu.Queries.PredicateQuery;
import coypu.Queries.Query;
import CS2JNet.System.TimeSpan;

public class RetryUntilTimeoutTimingStrategy   implements TimingStrategy
{
    public void tryUntil(BrowserAction tryThis, PredicateQuery until, Options options) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ outcome = Synchronise(new ActionSatisfiesPredicateQuery(tryThis, until, options, this));
        if (!outcome)
            throw new MissingHtmlException("Timeout from TryUntil: the page never reached the required state.");
         
    }

    private boolean __ZeroTimeout;
    public boolean getZeroTimeout() {
        return __ZeroTimeout;
    }

    public void setZeroTimeout(boolean value) {
        __ZeroTimeout = value;
    }

    private TimeSpan? overrideTimeout;
    public void setOverrideTimeout(TimeSpan timeout) throws Exception {
        overrideTimeout = timeout;
    }

    public void clearOverrideTimeout() throws Exception {
        overrideTimeout = null;
    }

    public <TResult>TResult synchronise(Query<TResult> query) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ interval = query.getOptions().RetryInterval;
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ stopWatch = Stopwatch.StartNew();
        while (true)
        {
            try
            {
                /* [UNSUPPORTED] 'var' as type is unsupported "var" */ result = query.run();
                if (ExpectedResultNotFoundWithinTimeout(query.getExpectedResult(), result, stopWatch, timeout(query), interval))
                {
                    WaitForInterval(interval);
                    continue;
                }
                 
                return result;
            }
            catch (NotSupportedException __dummyCatchVar0)
            {
                throw __dummyCatchVar0;
            }
            catch (Exception ex)
            {
                if (TimeoutReached(stopWatch, timeout(query), interval))
                {
                    throw ex;
                }
                 
                WaitForInterval(interval);
            }
        
        }
    }

    private <TResult>TimeSpan timeout(Query<TResult> query) throws Exception {
        TimeSpan timeout;
        if (getZeroTimeout())
        {
            timeout = TimeSpan.Zero;
        }
        else if (overrideTimeout.HasValue)
        {
            timeout = overrideTimeout.Value;
        }
        else
        {
            timeout = query.getOptions().Timeout;
        }  
        return timeout;
    }

    private void waitForInterval(TimeSpan interval) throws Exception {
        Thread.Sleep(interval);
    }

    private <TResult>boolean expectedResultNotFoundWithinTimeout(Object expectedResult, TResult result, Stopwatch stopWatch, TimeSpan timeout, TimeSpan interval) throws Exception {
        return expectedResult != null && !result.equals(expectedResult) && !timeoutReached(stopWatch,timeout,interval);
    }

    private boolean timeoutReached(Stopwatch stopWatch, TimeSpan timeout, TimeSpan interval) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ elapsedTimeToNextCall = TimeSpan.FromMilliseconds(stopWatch.ElapsedMilliseconds) + interval;
        Boolean timeoutReached = elapsedTimeToNextCall >= timeout;
        return timeoutReached;
    }

}


