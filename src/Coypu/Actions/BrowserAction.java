package Coypu.Actions;
import Coypu.Options;
import Coypu.Queries.Query;

public abstract class BrowserAction implements Query<Object>
{
    private double timeout;
    private double retryInterval;

    public double Timeout()
    {
        return timeout;
    }

    public double RetryInterval()
    {
        return retryInterval;
    }

    protected BrowserAction(Options options)
    {
        timeout = options.Timeout;
        retryInterval = options.RetryInterval;
    }

    public abstract void Act();

    public void Run()
    {
        Act();
    }

    public Object ExpectedResult()
    {
        return null;
    }

    public Object Result()
    {
        return null;
    }
}
