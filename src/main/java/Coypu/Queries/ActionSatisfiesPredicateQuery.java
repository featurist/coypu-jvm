package Coypu.Queries;

import Coypu.Actions.BrowserAction;
import Coypu.Robustness.RobustWrapper;
import Coypu.TimeSpan;

public class ActionSatisfiesPredicateQuery extends Query<Boolean> {
    private BrowserAction tryThis;
    private PredicateQuery until;
    private TimeSpan waitBeforeRetry;
    private RobustWrapper robustWrapper;
    private TimeSpan retryInterval;
    private TimeSpan timeout;

    public TimeSpan getRetryInterval() {
        return retryInterval;
    }

    public TimeSpan getTimeout() {
        return timeout;
    }

    public ActionSatisfiesPredicateQuery(BrowserAction tryThis, PredicateQuery until, TimeSpan overallTimeout, TimeSpan retryInterval, TimeSpan waitBeforeRetry, RobustWrapper robustWrapper) {
        this.tryThis = tryThis;
        this.until = until;
        this.waitBeforeRetry = waitBeforeRetry;
        this.robustWrapper = robustWrapper;
        this.retryInterval = retryInterval;
        this.timeout = overallTimeout;
    }

    public Boolean run() {
        tryThis.act();

        try {
            robustWrapper.setOverrideTimeout(waitBeforeRetry);
            return until.run();
        } finally {
            robustWrapper.clearOverrideTimeout();
        }
    }

    public Boolean getExpectedResult() {
        return true;
    }
}
