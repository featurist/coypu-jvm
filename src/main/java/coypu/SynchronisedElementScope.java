//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:19
//

package coypu;

import coypu.Actions.DriverAction;
import coypu.DriverScope;
import coypu.ElementFound;
import coypu.ElementScope;
import coypu.Finders.ElementFinder;
import coypu.Options;
import coypu.Queries.ElementQuery;
import coypu.Queries.Query;

public class SynchronisedElementScope  extends ElementScope 
{
    private final Options options;
    public SynchronisedElementScope(ElementFinder elementFinder, DriverScope outerScope, Options options) throws Exception {
        super(elementFinder, outerScope);
        this.options = options;
    }

    public ElementFound now() throws Exception {
        return timingStrategy.Synchronise(new ElementQuery(this, options));
    }

    public void try(DriverAction action) throws Exception {
        retryUntilTimeout(action);
    }

    public boolean try(Query<Boolean> query) throws Exception {
        return Query(query);
    }

}


