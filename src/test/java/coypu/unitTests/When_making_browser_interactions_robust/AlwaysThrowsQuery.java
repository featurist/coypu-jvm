package coypu.unitTests.When_making_browser_interactions_robust;

import coypu.Queries.Query;
import coypu.Stopwatch;
import coypu.TimeSpan;

public class AlwaysThrowsQuery<TResult> extends Query<TResult> {
    private final TimeSpan retryInterval;
    private final Stopwatch stopWatch = new Stopwatch();
    private int tries;
    private long lastCall;
    private TimeSpan timeout;
    private RuntimeException exception;

    public AlwaysThrowsQuery(TimeSpan timeout, TimeSpan retryInterval, RuntimeException exception) {
        this.retryInterval = retryInterval;
        setTimeout(timeout);
        stopWatch.start();
        this.exception = exception;
    }

    public TResult run() {
        tries++;
        lastCall = stopWatch.getElapsedMilliseconds();
        throw exception;
    }

    public TResult getExpectedResult() {
        return null;
    }


    public int getTries() {
        return tries;
    }

    public long getLastCall() {
        return lastCall;
    }

    public TimeSpan getTimeout() {
        return timeout;
    }

    public void setTimeout(TimeSpan timeout) {
        this.timeout = timeout;
    }

    public TimeSpan getRetryInterval() {
        return retryInterval;
    }
}

