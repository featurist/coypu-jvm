package Coypu.Queries;

import Coypu.TimeSpan;

public interface Query<TReturn> {
    void Run();

    TReturn ExpectedResult();

    TReturn Result();

    TimeSpan Timeout();

    TimeSpan RetryInterval();

}
