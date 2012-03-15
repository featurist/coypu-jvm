package Coypu.Queries;

import Coypu.DriverScope;
import Coypu.MissingHtmlException;
import Coypu.Options;
import Coypu.TimeSpan;

public abstract class DriverScopeQuery<T> implements Query<T>
{
    private DriverScope driverScope;
    private TimeSpan timeout;
    private TimeSpan retryInterval;
    private T result;

    protected DriverScope DriverScope()
    {
        return driverScope;
    }
    public TimeSpan Timeout()
    {
        return timeout;
    }
    public  TimeSpan RetryInterval() 
    {
        return retryInterval;
    }

    public DriverScopeQuery(DriverScope driverScope, Options options)
    {
        this.driverScope = driverScope;
        this.timeout = options.Timeout;
        this.retryInterval = options.RetryInterval;
    }

    public abstract void Run() throws MissingHtmlException;
    public abstract Object ExpectedResult();
    public T Result ()
    {
        return result;
    }

    public void SetResult(T value)
    {
        result = value;
    }
}
