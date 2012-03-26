package Coypu.Queries;

import Coypu.*;

public class HasCssQuery extends DriverScopeQuery<Boolean>
{
    private final Driver driver;
    private final String cssSelector;
    public Object ExpectedResult() { return true; }

    public HasCssQuery(Driver driver, DriverScope scope, String cssSelector, Options options)
    {
        super(scope,options);
        this.driver = driver;
        this.cssSelector = cssSelector;
    }

    public void Run()  {
        SetResult(driver.HasCss(cssSelector, DriverScope()));
    }
}
