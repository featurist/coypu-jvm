//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu;

import coypu.Actions.DriverAction;
import coypu.DriverScope;
import coypu.ElementFound;
import coypu.ElementScope;
import coypu.MissingHtmlException;
import coypu.Options;
import coypu.Queries.Query;
import CS2JNet.System.StringSupport;

/**
* The scope of an element already found in the document, therefore not deferred.
* 
* If this element becomes stale then using this scope will not try to refind the element but
* will raise a MissingHtmlException immediately.
*/
public class SnapshotElementScope  extends ElementScope 
{
    private final ElementFound elementFound;
    private final Options options;
    public SnapshotElementScope(ElementFound elementFound, DriverScope scope, Options options) throws Exception {
        super(null, scope);
        this.elementFound = elementFound;
        this.options = options;
    }

    public ElementFound now() throws Exception {
        if (elementFound.stale(options))
            throw new MissingHtmlException(String.format(StringSupport.CSFmtStrToJFmtStr("Snapshot element scope has become stale. {0}"),elementFound));
         
        return elementFound;
    }

    public void try(DriverAction action) throws Exception {
        action.act();
    }

    public boolean try(Query<Boolean> query) throws Exception {
        return query.run();
    }

}


