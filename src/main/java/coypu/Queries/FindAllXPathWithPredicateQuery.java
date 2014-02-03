//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.DriverScope;
import coypu.MissingHtmlException;
import coypu.Options;
import coypu.SnapshotElementScope;
import CS2JNet.System.Collections.LCC.IEnumerable;

public class FindAllXPathWithPredicateQuery  extends DriverScopeQuery<IEnumerable<SnapshotElementScope>> 
{
    private final String locator;
    private final Func<IEnumerable<SnapshotElementScope>, Boolean> predicate = new Func<IEnumerable<SnapshotElementScope>, Boolean>();
    public FindAllXPathWithPredicateQuery(String locator, Func<IEnumerable<SnapshotElementScope>, Boolean> predicate, DriverScope driverScope, Options options) throws Exception {
        super(driverScope, options);
        this.locator = locator;
        this.predicate = predicate;
    }

    public IEnumerable<SnapshotElementScope> run() throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ allElements = getDriverScope().FindAllXPathNoPredicate(locator, getOptions()).ToArray();
        if (!predicate(allElements))
            throw new MissingHtmlException("FindAllXPath did not find elements matching your predicate");
         
        return allElements;
    }

    public IEnumerable<SnapshotElementScope> getExpectedResult() throws Exception {
        return null;
    }

}


