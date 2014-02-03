//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:12
//

package coypu;

import coypu.Driver;
import coypu.DriverFactory;
import coypu.Drivers.Browser;

public class ActivatorDriverFactory   implements DriverFactory
{
    private static int __OpenDrivers;
    public static int getOpenDrivers() {
        return __OpenDrivers;
    }

    public static void setOpenDrivers(int value) {
        __OpenDrivers = value;
    }

    public Driver newWebDriver(Class driverType, Browser browser) throws Exception {
        try
        {
            Driver driver = (Driver)Activator.CreateInstance(driverType, browser);
            setOpenDrivers(getOpenDrivers() + 1);
            return driver;
        }
        catch (TargetInvocationException e)
        {
            throw e.InnerException;
        }
    
    }

}


