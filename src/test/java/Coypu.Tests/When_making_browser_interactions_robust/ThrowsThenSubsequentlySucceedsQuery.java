package Coypu.Tests.When_making_browser_interactions_robust;

import Coypu.Queries.Query;
import Coypu.Stopwatch;
import Coypu.Tests.TestException;
import Coypu.TimeSpan;

public class ThrowsThenSubsequentlySucceedsQuery<T> extends Query<T> {
    private final Stopwatch stopWatch = new Stopwatch();

    private final T actualResult;
    private final T expectedResult;
    private final int throwsHowManyTimes;
    private final TimeSpan _timeout;
    private final TimeSpan _retryInterval;

    public ThrowsThenSubsequentlySucceedsQuery(T actualResult, T expectedResult, int throwsHowManyTimes, TimeSpan timeout, TimeSpan retryInterval) {
        stopWatch.start();
        this.actualResult = actualResult;
        this.expectedResult = expectedResult;
        this.throwsHowManyTimes = throwsHowManyTimes;
        _timeout = timeout;
        _retryInterval = retryInterval;
    }

    public T run() {
        Tries++;
        LastCall = stopWatch.getElapsedMilliseconds();

        if (Tries <= throwsHowManyTimes)
            throw new TestException("Fails first time");

        return actualResult;
    }

    public T getExpectedResult()
    {
        return expectedResult;
    }

    public int Tries;

    public long LastCall;


    public TimeSpan getTimeout()
    {
        return _timeout;
    }

    public TimeSpan getRetryInterval()
    {
        return _retryInterval;
    }
}


