package Coypu.Tests.When_making_browser_interactions_robust;

import Coypu.Queries.Query;
import Coypu.Tests.TestException;
import Coypu.TimeSpan;

public class ThrowsSecondTimeQuery<T> extends Query<T> {
    private final T result;
    private int tries;

    public ThrowsSecondTimeQuery(T result, TimeSpan timeout, TimeSpan retryInterval) {
        super();
        this.result = result;
        this.retryInterval = retryInterval;
        this.timeout = timeout;
    }

    public T run() {
        tries++;
        if (tries == 1)
            throw new TestException("Fails first time");
        return result;
    }

    public T getExpectedResult() {
        return null;
    }

    public int getTries() {
        return tries;
    }

    public TimeSpan getRetryInterval() {
        return retryInterval;
    }
}
