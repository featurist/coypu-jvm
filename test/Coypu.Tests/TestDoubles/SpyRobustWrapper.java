package Coypu.Tests.TestDoubles;

import Coypu.Actions.BrowserAction;
import Coypu.Actions.DriverAction;
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
    private final List<Object> queriesRan = new ArrayList<Object>();
    public static final Object NO_EXPECTED_RESULT = new Object();
    private boolean zeroTimeout;

    public <T> Iterable<Query<T>> QueriesRan(Class<Query<T>> type)
    {
        return ofType(type);
    }

    public List<DriverAction> ActionsRan()
    {
        return ofType(DriverAction.class);
    }

    private <T> List<T> ofType(Class<T> type) {
        List<T> queries = new ArrayList<T>();
        for (Object query : queriesRan) {
            if (query.getClass().equals(type.getClass()))   {
                queries.add((T) query);
            }
        }
        return queries;
    }

    public boolean NoQueriesRan()  {
        return queriesRan.size() == 0;
    }

    public <T> T Robustly(Query<T> query)
    {
        queriesRan.add(query);

        if (alwaysReturn != null)
            return (T) alwaysReturn;

        Object key = query.ExpectedResult();
        if (key == null) key = NO_EXPECTED_RESULT;
        
        if (Arrays.asList(stubbedQueryResult.keys()).contains(key))
            return (T)stubbedQueryResult.get(key);

        return null;
    }

    public void TryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan overallTimeout, TimeSpan waitBeforeRetry)
    {
        DeferredTryUntils.add(new TryUntilArgs(tryThis, until, overallTimeout, waitBeforeRetry));
    }

    public boolean GetZeroTimeout() {
        return zeroTimeout;
    }

    public void SetZeroTimeout(boolean value) {
        zeroTimeout = value;
    }

    public void SetOverrideTimeout(TimeSpan timeout)
    {
    }

    public void ClearOverrideTimeout()
    {
    }

    public void AlwaysReturnFromRobustly(Object result)
    {
        alwaysReturn = result;
    }

    public <T> void StubQueryResult(T expectedResult, T result)
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
