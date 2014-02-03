//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:16
//

package coypu.Finders;

import coypu.Driver;
import coypu.DriverScope;
import coypu.ElementFound;
import coypu.ElementScope;
import coypu.MissingHtmlException;
import coypu.Options;
import coypu.SynchronisedElementScope;
import CS2JNet.System.Collections.LCC.IEnumerable;

public abstract class ElementFinder   
{
    protected public final Driver Driver;
    private final String locator;
    protected final DriverScope Scope;
    protected final Options options;
    protected ElementFinder(Driver driver, String locator, DriverScope scope, Options options) throws Exception {
        Driver = driver;
        this.locator = locator;
        Scope = scope;
        this.options = options;
    }

    public Options getOptions() throws Exception {
        return options;
    }

    public abstract boolean getSupportsSubstringTextMatching() throws Exception ;

    public String getLocator() throws Exception {
        return locator;
    }

    public abstract IEnumerable<ElementFound> find(Options options) throws Exception ;

    public abstract String getQueryDescription() throws Exception ;

    protected public Exception getMissingException() throws Exception {
        return new MissingHtmlException("Unable to find " + getQueryDescription());
    }

    public ElementScope asScope() throws Exception {
        return new SynchronisedElementScope(this, Scope, options);
    }

}


