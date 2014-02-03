//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.DriverScope;
import coypu.Options;

public abstract class DriverScopeQuery <T>  implements Query<T>
{
    private DriverScope __DriverScope;
    protected DriverScope getDriverScope() {
        return __DriverScope;
    }

    protected void setDriverScope(DriverScope value) {
        __DriverScope = value;
    }

    private Options __Options;
    public Options getOptions() {
        return __Options;
    }

    public void setOptions(Options value) {
        __Options = value;
    }

    public DriverScopeQuery(DriverScope driverScope, Options options) throws Exception {
        setOptions(options);
        setDriverScope(driverScope);
    }

    public abstract T run() throws Exception ;

    public abstract T getExpectedResult() throws Exception ;

}


