package Coypu.Queries;

import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.Options;

public class HasNoXPathQuery extends DriverScopeQuery<Boolean>
{
    private final Driver driver;
    private final String xpath;
    public Object ExpectedResult(){ return true; }


    public HasNoXPathQuery(Driver driver, DriverScope scope, String xpath, Options options)
    {
        super(scope, options);

        this.driver = driver;
        this.xpath = xpath;
    }

    public void Run()
    {
        SetResult(!driver.HasXPath(xpath, DriverScope()));
    }
}
