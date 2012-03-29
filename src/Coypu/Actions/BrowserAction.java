package Coypu.Actions;

import Coypu.MissingHtmlException;
import Coypu.Options;
import Coypu.Queries.Query;
import Coypu.TimeSpan;

public abstract class BrowserAction implements Query<Object> {
    private TimeSpan timeout;
    private TimeSpan retryInterval;

    public TimeSpan Timeout() {
        return timeout;
    }

    public TimeSpan RetryInterval() {
        return retryInterval;
    }

    protected BrowserAction(Options options) {
        timeout = options.Timeout;
        retryInterval = options.RetryInterval;
    }

    public abstract void Act();

    public void Run() {
        Act();
    }

    public Object ExpectedResult() {
        return null;
    }

    public Object Result() {
        return null;
    }
}
