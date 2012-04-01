package Coypu.Actions;

import Coypu.Options;
import Coypu.Queries.Query;
import Coypu.TimeSpan;

public abstract class BrowserAction extends Query<Object> {
    private TimeSpan timeout;
    private TimeSpan retryInterval;

    public TimeSpan getTimeout() {
        return timeout;
    }

    public TimeSpan getRetryInterval() {
        return retryInterval;
    }

    protected BrowserAction() {}

    protected BrowserAction(Options options) {
        super(options);
        timeout = options.Timeout;
        retryInterval = options.RetryInterval;
    }

    public abstract void act();

    public Object run() {
        act();
        return null;
    }

    public Object getExpectedResult() {
        return null;
    }

    public Object result() {
        return null;
    }
}
