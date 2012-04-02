package coypu.Queries;

import coypu.Options;
import coypu.TimeSpan;

public abstract class Query<TReturn> {
    protected TimeSpan timeout;
    protected TimeSpan retryInterval;

    public Query() {
    }

    public Query(Options options) {
        this.timeout = options.Timeout;
        this.retryInterval = options.RetryInterval;
    }

    public abstract TReturn run();

    public abstract TReturn getExpectedResult();

    public TimeSpan getTimeout() {
        return timeout;
    }

    public TimeSpan getRetryInterval() {
        return retryInterval;
    }
}
