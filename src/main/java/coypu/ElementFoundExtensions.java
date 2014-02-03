//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:16
//

package coypu;

import CS2JNet.System.Collections.LCC.CSList;
import CS2JNet.System.Collections.LCC.IEnumerable;


public class ElementFoundExtensions   
{
    public static IEnumerable<SnapshotElementScope> asSnapshotElementScopes(/* parameter modifiers are not yet supported this */IEnumerable<ElementFound> elements, DriverScope driverScope, Options options) throws Exception {
        CSList<SnapshotElementScope> scopes = new CSList<SnapshotElementScope>();

        for (ElementFound elementFound : elements) {
            scopes.add(new SnapshotElementScope(elementFound, driverScope, options));
        }
        return scopes;
    }

}


