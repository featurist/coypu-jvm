//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu;

import coypu.BrowserSession;
import coypu.BrowserWindow;
import coypu.ElementFound;
import coypu.Finders.WindowFinder;
import coypu.Options;
import coypu.Queries.ElementQuery;

public class RobustWindowScope  extends BrowserWindow 
{
    private final Options options;
    public RobustWindowScope(WindowFinder windowFinder, BrowserSession scope, Options options) throws Exception {
        super(windowFinder, scope);
        this.options = options;
    }

    public ElementFound now() throws Exception {
        return timingStrategy.Synchronise(new ElementQuery(this, options));
    }

}


