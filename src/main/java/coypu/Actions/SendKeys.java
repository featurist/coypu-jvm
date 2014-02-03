//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:12
//

package coypu.Actions;

import coypu.Actions.DriverAction;
import coypu.Driver;
import coypu.DriverScope;
import coypu.Options;

public class SendKeys  extends DriverAction 
{
    private final String keys;
    private final DriverScope driverScope;
    public SendKeys(String keys, DriverScope driverScope, Driver driver, Options options) throws Exception {
        super(driver, options);
        this.keys = keys;
        this.driverScope = driverScope;
    }

    public void act() throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ element = driverScope.Now();
        Driver.SendKeys(element, keys);
    }

}


