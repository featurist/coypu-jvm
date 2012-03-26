package Coypu.Queries;

import Coypu.*;

public class HasContentQuery extends DriverScopeQuery<Boolean>
{
    private final Driver driver;
    private final String text;
    public Object ExpectedResult() { return true; }

    public HasContentQuery(Driver driver, DriverScope scope, String text, Options options)
    {
        super(scope,options);
        this.driver = driver;
        this.text = text;
    }

    public void Run()  {
        SetResult(driver.HasContent(text, DriverScope()));
    }
}
