package Coypu.Queries;

import Coypu.MissingHtmlException;
import Coypu.TimeSpan;

public interface Query<TReturn>
{
    void Run() throws MissingHtmlException;
    Object ExpectedResult();
    TReturn Result();
    TimeSpan Timeout ();
    TimeSpan RetryInterval();
}
