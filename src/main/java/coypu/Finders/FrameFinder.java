//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:16
//

package coypu.Finders;

import coypu.Driver;
import coypu.DriverScope;
import coypu.ElementFound;
import coypu.Finders.ElementFinder;
import coypu.Options;
import CS2JNet.System.Collections.LCC.IEnumerable;

public class FrameFinder  extends ElementFinder 
{
    public FrameFinder(Driver driver, String locator, DriverScope scope, Options options) throws Exception {
        super(driver, locator, scope, options);
    }

    public boolean getSupportsSubstringTextMatching() throws Exception {
        return true;
    }

    public IEnumerable<ElementFound> find(Options options) throws Exception {
        return Driver.FindFrames(getLocator(), Scope, options);
    }

    public String getQueryDescription() throws Exception {
        return "frame: " + getLocator();
    }

}


