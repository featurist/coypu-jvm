//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu;

import coypu.Driver;
import coypu.Drivers.Browser;

public interface DriverFactory   
{
    Driver newWebDriver(Class driverType, Browser browser) throws Exception ;

}


