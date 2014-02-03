//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.DriverScope;
import coypu.ElementFound;
import coypu.Options;
import coypu.Queries.DriverScopeQuery;

public class ElementQuery  extends DriverScopeQuery<ElementFound> 
{
    public ElementQuery(DriverScope driverScope, Options options) throws Exception {
        super(driverScope, options);
    }

    public ElementFound getExpectedResult() throws Exception {
        return null;
    }

    public ElementFound run() throws Exception {
        return getDriverScope().FindElement();
    }

}


