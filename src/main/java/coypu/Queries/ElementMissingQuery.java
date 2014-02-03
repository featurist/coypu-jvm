//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.DriverScope;
import coypu.MissingHtmlException;
import coypu.MissingWindowException;

public class ElementMissingQuery  extends DriverScopeQuery<Boolean> 
{
    protected public ElementMissingQuery(DriverScope driverScope) throws Exception {
        super(driverScope, driverScope.getElementFinder().getOptions());
    }

    public Boolean getExpectedResult() throws Exception {
        return true;
    }

    public Boolean run() throws Exception {
        try
        {
            getDriverScope().FindElement();
            return false;
        }
        catch (MissingHtmlException __dummyCatchVar0)
        {
            return true;
        }
        catch (MissingWindowException __dummyCatchVar1)
        {
            return true;
        }
    
    }

}


