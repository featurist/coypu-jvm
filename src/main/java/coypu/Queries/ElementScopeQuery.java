//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.DriverScope;
import coypu.Options;

public abstract class ElementScopeQuery <T>  implements Query<T>
{
    private Options __Options;
    public Options getOptions() {
        return __Options;
    }

    public void setOptions(Options value) {
        __Options = value;
    }

    private DriverScope __Scope;
    protected DriverScope getScope() {
        return __Scope;
    }

    protected void setScope(DriverScope value) {
        __Scope = value;
    }

    public ElementScopeQuery(DriverScope scope, Options options) throws Exception {
        setOptions(options);
        setScope(scope);
    }

    public abstract T run() throws Exception ;

    public abstract T getExpectedResult() throws Exception ;

}


