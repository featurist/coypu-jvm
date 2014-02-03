//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:17
//

package coypu.Finders;

import coypu.Driver;
import coypu.DriverScope;
import coypu.ElementFound;
import coypu.Finders.ElementFinder;
import coypu.Options;
import CS2JNet.System.Collections.LCC.IEnumerable;

public class XPathFinder  extends ElementFinder 
{
    public XPathFinder(Driver driver, String locator, DriverScope scope, Options options) throws Exception {
        super(driver, locator, scope, options);
    }

    public boolean getSupportsSubstringTextMatching() throws Exception {
        return false;
    }

    public IEnumerable<ElementFound> find(Options options) throws Exception {
        return Driver.FindAllXPath(getLocator(), Scope, options);
    }

    public String getQueryDescription() throws Exception {
        return "xpath: " + getLocator();
    }

}


