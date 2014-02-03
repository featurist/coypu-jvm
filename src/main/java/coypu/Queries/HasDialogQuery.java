//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.Driver;
import coypu.DriverScope;
import coypu.Options;
import coypu.Queries.DriverScopeQuery;

public class HasDialogQuery  extends DriverScopeQuery<Boolean> 
{
    private final Driver driver;
    private final String text;
    public Boolean getExpectedResult() throws Exception {
        return true;
    }

    protected public HasDialogQuery(Driver driver, String text, DriverScope driverScope, Options options) throws Exception {
        super(driverScope, options);
        this.driver = driver;
        this.text = text;
    }

    public Boolean run() throws Exception {
        return driver.HasDialog(text, getDriverScope());
    }

}


