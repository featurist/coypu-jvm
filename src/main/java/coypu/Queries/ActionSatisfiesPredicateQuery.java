//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.Actions.BrowserAction;
import coypu.Options;
import coypu.Timing.TimingStrategy;

public class ActionSatisfiesPredicateQuery   implements Query<Boolean>
{
    private final BrowserAction tryThis;
    private final PredicateQuery until;
    private final TimingStrategy timingStrategy;
    private Options __Options;
    public Options getOptions() {
        return __Options;
    }

    public void setOptions(Options value) {
        __Options = value;
    }

    public ActionSatisfiesPredicateQuery(BrowserAction tryThis, PredicateQuery until, Options options, TimingStrategy timingStrategy) throws Exception {
        this.tryThis = tryThis;
        this.until = until;
        this.timingStrategy = timingStrategy;
        setOptions(options);
    }

    public boolean run() throws Exception {
        tryThis.act();
        return timingStrategy.Synchronise(until);
    }

    public boolean getExpectedResult() throws Exception {
        return true;
    }

}


