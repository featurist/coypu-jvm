package Coypu.Queries;

import Coypu.DriverScope;
import Coypu.MissingHtmlException;
import Coypu.Options;

public class ElementExistsQuery extends DriverScopeQuery<Boolean>
{
    public Object ExpectedResult()
    {
        return true;
    }

    public ElementExistsQuery(DriverScope driverScope, Options options)
    {
        super(driverScope, options);
    }

    public void Run()
    {
        try
        {
            DriverScope().FindElement();
            SetResult(true);
        }
        catch (MissingHtmlException ex)
        {
            SetResult(false);
        }
    }
}
