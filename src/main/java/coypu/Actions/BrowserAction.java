//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:12
//

package coypu.Actions;

import coypu.Options;
import coypu.Queries.Query;

public abstract class BrowserAction   implements Query<Object>
{
    private Options __Options;
    public Options getOptions() {
        return __Options;
    }

    public void setOptions(Options value) {
        __Options = value;
    }

    protected BrowserAction(Options options) throws Exception {
        setOptions(options);
    }

    public abstract void act() throws Exception ;

    public Object run() throws Exception {
        act();
        return null;
    }

    public Object getExpectedResult() throws Exception {
        return null;
    }

}


