package Coypu.Tests.When_making_browser_interactions_robust;

import Coypu.Options;
import Coypu.Queries.PredicateQuery;
import Coypu.Stopwatch;
import Coypu.Tests.TestException;

public class ThrowsThenSubsequentlySucceedsPredicateQuery extends PredicateQuery {
    private final Stopwatch stopWatch = new Stopwatch();

    private final Boolean actualResult;
    private final int throwsHowManyTimes;

    public ThrowsThenSubsequentlySucceedsPredicateQuery(Boolean actualResult, int throwsHowManyTimes, Options options) {
        super(options);
        stopWatch.start();
        this.actualResult = actualResult;
        this.throwsHowManyTimes = throwsHowManyTimes;
    }

    public Boolean predicate() {
        Tries++;
        LastCall = stopWatch.getElapsedMilliseconds();

        if (Tries <= throwsHowManyTimes)
            throw new TestException("Fails first time");

        return actualResult;
    }

    public int Tries;

    public long LastCall;
}
