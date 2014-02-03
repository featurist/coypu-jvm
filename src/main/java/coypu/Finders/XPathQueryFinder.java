//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:17
//

package coypu.Finders;

import coypu.Driver;
import coypu.Drivers.Html;
import coypu.DriverScope;
import coypu.ElementFound;
import coypu.Options;
import CS2JNet.System.Collections.LCC.IEnumerable;

public abstract class XPathQueryFinder  extends ElementFinder 
{
    protected XPathQueryFinder(Driver driver, String locator, DriverScope scope, Options options) throws Exception {
        super(driver, locator, scope, options);
    }

    public IEnumerable<ElementFound> find(Options options) throws Exception {
        Html html = new Html(Scope.Browser.UppercaseTagNames);
        return Driver.FindAllXPath(getQuery(html)(getLocator(), options), Scope, options);
    }

    protected abstract Func<String, Options, String> getQuery(Html html) throws Exception ;

}


