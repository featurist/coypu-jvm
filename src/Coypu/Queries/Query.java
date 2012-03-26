package Coypu.Queries;

import Coypu.MissingHtmlException;
import Coypu.TimeSpan;

public interface Query<TReturn>
{
    void Run() ;
    Object ExpectedResult();
    TReturn Result();
    TimeSpan Timeout ();
    TimeSpan RetryInterval();
}
