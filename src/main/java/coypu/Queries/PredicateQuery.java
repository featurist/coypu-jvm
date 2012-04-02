package coypu.Queries;

import coypu.Options;
import coypu.TimeSpan;

public abstract class PredicateQuery extends Query<Boolean> {

    private TimeSpan timeout;
    private TimeSpan retryInterval;

    protected PredicateQuery() {}

    protected PredicateQuery(Options options) {
        this.timeout = options.Timeout;
        this.retryInterval = options.RetryInterval;
    }

    public Boolean getExpectedResult() {
        return true;
    }

    public TimeSpan getTimeout() {
        return timeout;
    }

    public TimeSpan getRetryInterval() {
        return retryInterval;
    }

    public Boolean run() {
       return this.predicate();
    }

    protected abstract Boolean predicate();
}
