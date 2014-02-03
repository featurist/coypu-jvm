//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:12
//

package coypu.Actions;

import coypu.Actions.BrowserAction;
import coypu.Driver;
import coypu.Options;

public abstract class DriverAction  extends BrowserAction 
{
    protected final Driver Driver;
    protected DriverAction(Driver driver, Options options) throws Exception {
        super(options);
        Driver = driver;
    }

}


