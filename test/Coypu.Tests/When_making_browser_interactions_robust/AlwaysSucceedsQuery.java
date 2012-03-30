package Coypu.Tests.When_making_browser_interactions_robust;

import Coypu.Queries.Query;
import Coypu.Stopwatch;
import Coypu.TimeSpan;

public class AlwaysSucceedsQuery<T> implements Query<T>
{
    private final Stopwatch stopWatch = new Stopwatch();
    private T result;
    private final T actualResult;
    private T expecting;
    private final TimeSpan _timeout;
    private final TimeSpan _retryInterval;
    
    public AlwaysSucceedsQuery(T actualResult, TimeSpan timeout, TimeSpan retryInterval) {
        _timeout = timeout;
        _retryInterval = retryInterval;
        this.actualResult = actualResult;
        stopWatch.start();
    }
    
    public AlwaysSucceedsQuery(T actualResult, T expecting, TimeSpan timeout, TimeSpan retryInterval) {
        this(actualResult, timeout, retryInterval);
        this.expecting = expecting;
    }

    int _tries;
    public int getTries() { return _tries; }
    public void setTries(int tries) { _tries = tries; }

    long _lastCall;
    public long getLastCall() { return _lastCall; }
    public void setLastCall(long lastCall) { _lastCall = lastCall; }
    
    public void Run()
    {
        _tries++;
        _lastCall = stopWatch.getElapsedMilliseconds();
        result = actualResult;
    }
    
    public T ExpectedResult() {
        return expecting;
    }
    
    public T Result() {
        return result;
    }
    
    public TimeSpan Timeout() {
        return _timeout;
    }
    
    public TimeSpan RetryInterval() {
        return _retryInterval;
    }
}