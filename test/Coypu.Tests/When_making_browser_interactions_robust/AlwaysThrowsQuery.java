package Coypu.Tests.When_making_browser_interactions_robust;

import Coypu.Queries.Query;
import Coypu.Stopwatch;
import Coypu.TimeSpan;

public class AlwaysThrowsQuery<TResult> extends Query<TResult> {
    private final TimeSpan _retryInterval;
    private final Stopwatch stopWatch = new Stopwatch();
    private int _tries;
    private long _lastCall;
    private TimeSpan _timeout;
    private RuntimeException _exception;

    public AlwaysThrowsQuery(TimeSpan timeout, TimeSpan retryInterval, RuntimeException exception) {
        _retryInterval = retryInterval;
        setTimeout(timeout);
        stopWatch.start();
        _exception = exception;
    }

    public void Run() {
        _tries++;
        setLastCall(stopWatch.getElapsedMilliseconds());
        throw _exception;
    }

    public TResult ExpectedResult()
    {
        return null;
    }

    public TResult Result()
    {
        return null;
    }

    public int getTries() {
        return _tries;    
    }
    
    public void setTries(int tries) {
        _tries = tries;
    }
    
    public long getLastCall() {
        return _lastCall;    
    }
    
    public void setLastCall(long lastCall) {
        _lastCall = lastCall;   
    }
    
    public TimeSpan getTimeout() {
        return _timeout;   
    }
    
    public void setTimeout(TimeSpan timeout) {
        _timeout = timeout;
    }

    public TimeSpan getRetryInterval() {
        return _retryInterval; }
    }
}