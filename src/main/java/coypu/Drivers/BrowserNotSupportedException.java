//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu.Drivers;

import CS2JNet.System.StringSupport;

/**
* Thrown when your chosen browser is not supported by your chosen driver
*/
public class BrowserNotSupportedException  extends Exception 
{
    public BrowserNotSupportedException(Browser browser, Class driverType) throws Exception {
        this(browser, driverType, null);
    }

    public BrowserNotSupportedException(Browser browser, Class driverType, Exception inner) throws Exception {
        super(String.format(StringSupport.CSFmtStrToJFmtStr("{0} is not supported by {1}"),browser,driverType.getName()), inner);
    }

}


