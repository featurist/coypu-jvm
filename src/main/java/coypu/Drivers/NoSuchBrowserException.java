//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu.Drivers;


/**
* There is no such browser
*/
public class NoSuchBrowserException  extends Exception 
{
    public NoSuchBrowserException(String browserName) throws Exception {
        super("No such browser: " + browserName);
    }

}


