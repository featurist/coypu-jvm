//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.DriverScope;
import coypu.MissingHtmlException;
import coypu.MissingWindowException;

public class ElementExistsQuery  extends DriverScopeQuery<Boolean> 
{
    protected public ElementExistsQuery(DriverScope driverScope) throws Exception {
        super(driverScope, driverScope.getElementFinder().getOptions());
    }

    public Boolean getExpectedResult() throws Exception {
        return true;
    }

    public Boolean run() throws Exception {
        try
        {
            getDriverScope().FindElement();
            return true;
        }
        catch (MissingHtmlException __dummyCatchVar0)
        {
            return false;
        }
        catch (MissingWindowException __dummyCatchVar1)
        {
            return false;
        }
    
    }

}


