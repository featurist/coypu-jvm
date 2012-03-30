package Coypu.Queries;

import Coypu.Options;
import Coypu.TimeSpan;

public abstract class PredicateQuery implements Query<Boolean> {

    private TimeSpan timeout;
    private TimeSpan retryInterval;
    protected Boolean result;

    protected PredicateQuery() {
    }

    protected PredicateQuery(Options options) {
        this.timeout = options.Timeout;
        this.retryInterval = options.RetryInterval;
    }

    public Boolean Result() {
        return result;
    }

    public abstract boolean Predicate();

    public void Run() {
        this.result = Predicate();
    }

    public Boolean ExpectedResult() {
        return true;
    }

    public TimeSpan Timeout() {
        return timeout;
    }

    public TimeSpan RetryInterval() {
        return retryInterval;
    }
}
