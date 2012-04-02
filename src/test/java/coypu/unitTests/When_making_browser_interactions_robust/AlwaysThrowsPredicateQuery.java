package coypu.unitTests.When_making_browser_interactions_robust;

import coypu.Options;
import coypu.Queries.PredicateQuery;
import coypu.Stopwatch;
import coypu.TimeSpan;

public class AlwaysThrowsPredicateQuery extends PredicateQuery {
    private final Stopwatch stopWatch = new Stopwatch();
    private RuntimeException exception;

    public AlwaysThrowsPredicateQuery(Options options, RuntimeException exception) {
        super(options);
        this.exception = exception;
        stopWatch.start();
    }

    public AlwaysThrowsPredicateQuery(TimeSpan timeout, TimeSpan retryInterval, RuntimeException exception) {
        this(buildOptions(timeout, retryInterval),exception);
    }

    private static Options buildOptions(TimeSpan timeout, TimeSpan retryInterval) {
        Options options = new Options();
        options.Timeout = timeout;
        options.RetryInterval = retryInterval;
        return options;
    }

    int tries;
    public int getTries() { return tries; }

    long lastCall;
    public long getLastCall() { return lastCall; }


    @Override
    public Boolean predicate() {
        tries++;
        lastCall = stopWatch.getElapsedMilliseconds();

        throw exception;
    }
}
