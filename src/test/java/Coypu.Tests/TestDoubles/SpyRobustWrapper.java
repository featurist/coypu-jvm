package Coypu.Tests.TestDoubles;

import Coypu.Actions.BrowserAction;
import Coypu.Queries.PredicateQuery;
import Coypu.Queries.Query;
import Coypu.Robustness.RobustWrapper;
import Coypu.TimeSpan;

import java.util.*;

public class SpyRobustWrapper implements RobustWrapper
{
    public List<TryUntilArgs> DeferredTryUntils = new ArrayList<TryUntilArgs>();

    private Object alwaysReturn;
    private final Dictionary<Object, Object> stubbedQueryResult = new Hashtable<Object, Object>();
    private final List<Query> queriesRan = new ArrayList<Query>();
    public static final Object NO_EXPECTED_RESULT = new Object();
    private boolean zeroTimeout;

    public boolean noQueriesRan()  {
        return queriesRan.size() == 0;
    }

    public List<Query> queriesRan() {
        return queriesRan;
    }

    public <T> T robustly(Query<T> query)
    {
        queriesRan.add(query);

        if (alwaysReturn != null)
            return (T) alwaysReturn;

        Object key = query.getExpectedResult();
        if (key == null) key = NO_EXPECTED_RESULT;
        
        if (Collections.list(stubbedQueryResult.keys()).contains(key))
            return (T)stubbedQueryResult.get(key);

        return null;
    }

    public void tryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan overallTimeout, TimeSpan waitBeforeRetry)
    {
        DeferredTryUntils.add(new TryUntilArgs(tryThis, until, overallTimeout, waitBeforeRetry));
    }

    public boolean getZeroTimeout() {
        return zeroTimeout;
    }

    public void setZeroTimeout(boolean value) {
        zeroTimeout = value;
    }

    public void setOverrideTimeout(TimeSpan timeout)
    {
    }

    public void clearOverrideTimeout()
    {
    }

    public void alwaysReturnFromRobustly(Object result)
    {
        alwaysReturn = result;
    }

    public <T> void stubQueryResult(T expectedResult, T result)
    {
        stubbedQueryResult.put(expectedResult, result);
    }

    public class TryUntilArgs
    {
        public TimeSpan OverallTimeout;
        public TimeSpan WaitBeforeRetry;
        public BrowserAction TryThisBrowserAction;
        public PredicateQuery Until;

        public TryUntilArgs(BrowserAction tryThis, PredicateQuery until, TimeSpan overallTimeout, TimeSpan waitBeforeRetry)
        {
            OverallTimeout = overallTimeout;
            WaitBeforeRetry = waitBeforeRetry;
            TryThisBrowserAction = tryThis;
            Until = until;
        }
    }
}
