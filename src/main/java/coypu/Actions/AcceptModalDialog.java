//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:12
//

package coypu.Actions;

import coypu.Driver;
import coypu.DriverScope;
import coypu.Options;

public class AcceptModalDialog  extends DriverAction 
{
    private final DriverScope driverScope;
    public AcceptModalDialog(DriverScope driverScope, Driver driver, Options options) throws Exception {
        super(driver, options);
        this.driverScope = driverScope;
    }

    public void act() throws Exception {
        Driver.AcceptModalDialog(driverScope);
    }

}


