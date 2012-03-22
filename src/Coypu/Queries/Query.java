package Coypu.Queries;

import Coypu.MissingHtmlException;
import Coypu.TimeSpan;
import Coypu.TimeoutException;

public interface Query<TReturn>
{
    void Run() throws MissingHtmlException, TimeoutException, InterruptedException;
    Object ExpectedResult();
    TReturn Result();
    TimeSpan Timeout ();
    TimeSpan RetryInterval();
}
