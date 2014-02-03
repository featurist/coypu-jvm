//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.DriverScope;
import coypu.Options;
import coypu.Queries.DriverScopeQuery;

public class HasContentQuery  extends DriverScopeQuery<Boolean> 
{
    private final String text;
    public Boolean getExpectedResult() throws Exception {
        return true;
    }

    public HasContentQuery(DriverScope scope, String text, Options options) throws Exception {
        super(scope, options);
        this.text = text;
    }

    public Boolean run() throws Exception {
        return getDriverScope().FindElement().Text.Contains(text);
    }

}


