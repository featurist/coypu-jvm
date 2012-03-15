package Coypu.Queries;

import Coypu.DriverScope;
import Coypu.ElementFound;
import Coypu.MissingHtmlException;
import Coypu.Options;

public class ElementQuery extends DriverScopeQuery<ElementFound>
{
    public ElementQuery(DriverScope driverScope, Options options)
    {
        super(driverScope, options);
    }

    public Object ExpectedResult()
    {
        return null;
    }

    public void Run() throws MissingHtmlException
    {
        SetResult(DriverScope().FindElement());
    }
}
