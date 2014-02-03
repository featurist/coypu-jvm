//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.DriverScope;
import coypu.Options;

import java.util.regex.Pattern;

public class HasContentMatchQuery  extends DriverScopeQuery<Boolean> 
{
    private final Pattern text;
    public Boolean getExpectedResult() throws Exception {
        return true;
    }

    protected public HasContentMatchQuery(DriverScope scope, Pattern text, Options options) throws Exception {
        super(scope, options);
        this.text = text;
    }

    public Boolean run() throws Exception {
        return text.IsMatch(getDriverScope().FindElement().Text);
    }

}


