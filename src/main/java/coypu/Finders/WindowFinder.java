//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:17
//

package coypu.Finders;

import coypu.Driver;
import coypu.DriverScope;
import coypu.ElementFound;
import coypu.Finders.ElementFinder;
import coypu.MissingWindowException;
import coypu.Options;
import CS2JNet.System.Collections.LCC.IEnumerable;

public class WindowFinder  extends ElementFinder 
{
    public WindowFinder(Driver driver, String locator, DriverScope scope, Options options) throws Exception {
        super(driver, locator, scope, options);
    }

    public boolean getSupportsSubstringTextMatching() throws Exception {
        return true;
    }

    public IEnumerable<ElementFound> find(Options options) throws Exception {
        return Driver.FindWindows(getLocator(), Scope, options);
    }

    public String getQueryDescription() throws Exception {
        return "window: " + getLocator();
    }

    protected public Exception getMissingException() throws Exception {
        return new MissingWindowException("Unable to find " + getQueryDescription());
    }

}


