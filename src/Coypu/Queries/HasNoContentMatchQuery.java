package Coypu.Queries;

import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.Options;

public class HasNoContentMatchQuery extends DriverScopeQuery<Boolean>
{
    private final Driver driver;
    private final Regex text;
    public Object ExpectedResult() { return true; }

    public HasNoContentMatchQuery(Driver driver, DriverScope scope, Regex text, Options options)
    {
        super(scope, options);
        this.driver = driver;
        this.text = text;
    }

    public void Run()
    {
        SetResult(!driver.HasContentMatch(text, DriverScope()));
    }    
}
