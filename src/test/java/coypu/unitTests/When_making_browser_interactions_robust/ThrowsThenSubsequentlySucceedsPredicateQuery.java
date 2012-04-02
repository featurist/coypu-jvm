package coypu.unitTests.When_making_browser_interactions_robust;

import coypu.Options;
import coypu.Queries.PredicateQuery;
import coypu.Stopwatch;
import coypu.unitTests.TestException;

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
