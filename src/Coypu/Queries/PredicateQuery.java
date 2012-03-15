package Coypu.Queries;

import Coypu.Options;
import Coypu.TimeSpan;

public abstract class PredicateQuery implements Query<Boolean>
{

    private TimeSpan timeout;
    private TimeSpan retryInterval;

    protected PredicateQuery()
    {
    }

    protected PredicateQuery(Options options)
    {
        this.timeout = options.Timeout;
        this.retryInterval = options.RetryInterval;
    }

    public abstract Boolean Result();

    public abstract void Run();

    public Object ExpectedResult()
    {
        return true;
    }

    public TimeSpan Timeout()
    {
        return timeout;
    }

    public TimeSpan RetryInterval()
    {
        return retryInterval;
    }
}