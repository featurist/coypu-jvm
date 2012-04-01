package Coypu.Tests.When_making_browser_interactions_robust;

import Coypu.Options;
import Coypu.State;
import Coypu.Stopwatch;
import Coypu.TimeSpan;

public class AlwaysSucceedsPredicateQuery extends State
{
    private final Stopwatch stopWatch = new Stopwatch();
    private final boolean actualResult;

    public AlwaysSucceedsPredicateQuery(boolean actualResult, Options options) {
        super(options);
        this.actualResult = actualResult;
        stopWatch.start();
    }

    public AlwaysSucceedsPredicateQuery(boolean actualResult, TimeSpan timeout, TimeSpan retryInterval) {
        this(actualResult, buildOptions(timeout, retryInterval));
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

        return actualResult;
    }
}
