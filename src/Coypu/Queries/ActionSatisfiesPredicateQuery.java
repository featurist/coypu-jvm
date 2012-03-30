package Coypu.Queries;

import Coypu.Actions.BrowserAction;
import Coypu.Robustness.RobustWrapper;
import Coypu.TimeSpan;

public class ActionSatisfiesPredicateQuery implements Query<Boolean> {
    private BrowserAction tryThis;
    private PredicateQuery until;
    private TimeSpan waitBeforeRetry;
    private RobustWrapper robustWrapper;
    private TimeSpan retryInterval;
    private TimeSpan timeout;
    private Boolean result;

    public TimeSpan RetryInterval() {
        return retryInterval;
    }

    public TimeSpan Timeout() {
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

    public void Run() {
        tryThis.Act();

        try {
            robustWrapper.SetOverrideTimeout(waitBeforeRetry);
            until.Run();
        } finally {
            robustWrapper.ClearOverrideTimeout();
        }
        this.result = until.Result();
    }

    public Boolean ExpectedResult() {
        return true;
    }

    public Boolean Result() {
        return result;
    }
}
