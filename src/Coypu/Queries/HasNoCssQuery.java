package Coypu.Queries;

import Coypu.*;

public class HasNoCssQuery extends DriverScopeQuery<Boolean>
{
    private final Driver driver;
    private final String cssSelector;

    public HasNoCssQuery(Driver driver, DriverScope scope, String cssSelector, Options options)
    {
        super(scope,options);
        this.driver = driver;
        this.cssSelector = cssSelector;
    }

    public Object ExpectedResult()
    {
        return true;
    }

    public void Run() throws MissingHtmlException, TimeoutException {
        SetResult(!driver.HasCss(cssSelector, DriverScope()));
    }
}
