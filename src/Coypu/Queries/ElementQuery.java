package Coypu.Queries;

import Coypu.*;

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

    public void Run() throws MissingHtmlException, TimeoutException {
        SetResult(DriverScope().FindElement());
    }
}
