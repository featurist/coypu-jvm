package Coypu.Queries;

import Coypu.*;

public abstract class DriverScopeQuery<T> implements Query<T> {
    private DriverScope driverScope;
    private TimeSpan timeout;
    private TimeSpan retryInterval;
    private T result;

    protected DriverScope DriverScope() {
        return driverScope;
    }

    public TimeSpan Timeout() {
        return timeout;
    }

    public TimeSpan RetryInterval() {
        return retryInterval;
    }

    public DriverScopeQuery(DriverScope driverScope, Options options) {
        this.driverScope = driverScope;
        this.timeout = options.Timeout;
        this.retryInterval = options.RetryInterval;
    }

    public abstract void Run();

    public abstract Object ExpectedResult();

    public T Result() {
        return result;
    }

    public void SetResult(T value) {
        result = value;
    }
}
