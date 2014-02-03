//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.DriverScope;
import coypu.Options;
import coypu.Queries.ElementScopeQuery;
import CS2JNet.System.StringSupport;

public class HasValueQuery  extends ElementScopeQuery<Boolean> 
{
    private final String text;
    public Boolean getExpectedResult() throws Exception {
        return true;
    }

    public HasValueQuery(DriverScope scope, String text, Options options) throws Exception {
        super(scope, options);
        this.text = text;
    }

    public Boolean run() throws Exception {
        return StringSupport.equals(getScope().FindElement().Value, text);
    }

}


