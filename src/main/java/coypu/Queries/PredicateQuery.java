//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.Options;

public abstract class PredicateQuery   implements Query<Boolean>
{
    private Options __Options;
    public Options getOptions() {
        return __Options;
    }

    public void setOptions(Options value) {
        __Options = value;
    }

    protected PredicateQuery(Options options) throws Exception {
        setOptions(options);
    }

    public abstract boolean predicate() throws Exception ;

    public boolean run() throws Exception {
        return predicate();
    }

    public boolean getExpectedResult() throws Exception {
        return true;
    }

}


