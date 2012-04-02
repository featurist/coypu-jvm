package coypu.unitTests.When_making_browser_interactions_robust;

import coypu.Queries.Query;
import coypu.Stopwatch;
import coypu.TimeSpan;

public class AlwaysSucceedsQuery<T> extends Query<T> {
    private final Stopwatch stopWatch = new Stopwatch();
    private final T actualResult;
    private T expecting;
    private final TimeSpan timeout;
    private final TimeSpan retryInterval;

    public AlwaysSucceedsQuery(T actualResult, TimeSpan timeout, TimeSpan retryInterval) {
        this.timeout = timeout;
        this.retryInterval = retryInterval;
        this.actualResult = actualResult;
        stopWatch.start();
    }

    public AlwaysSucceedsQuery(T actualResult, T expecting, TimeSpan timeout, TimeSpan retryInterval) {
        this(actualResult, timeout, retryInterval);
        this.expecting = expecting;
    }

    int tries;
    public int getTries() { return tries; }

    long lastCall;
    public long getLastCall() { return lastCall; }

    public T getExpectedResult() {
        return expecting;
    }

    public T run() {
        tries++;
        lastCall = stopWatch.getElapsedMilliseconds();
        return actualResult;
    }

    public TimeSpan getTimeout() {
        return timeout;
    }

    public TimeSpan getRetryInterval() {
        return retryInterval;
    }
}


