//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:17
//

package coypu.Finders;

import coypu.Driver;
import coypu.Drivers.Html;
import coypu.DriverScope;
import coypu.Finders.XPathQueryFinder;
import coypu.Options;

public class SelectFinder  extends XPathQueryFinder 
{
    public SelectFinder(Driver driver, String locator, DriverScope scope, Options options) throws Exception {
        super(driver, locator, scope, options);
    }

    public boolean getSupportsSubstringTextMatching() throws Exception {
        return true;
    }

    protected Func<String, Options, String> getQuery(Html html) throws Exception {
        return html.Select;
    }

    public String getQueryDescription() throws Exception {
        return "select: " + getLocator();
    }

}

