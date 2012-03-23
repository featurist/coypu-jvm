package Coypu.Queries;

import Coypu.*;

public class HasDialogQuery extends DriverScopeQuery<Boolean>
{
    private final Driver driver;
    private final String text;
    public Object ExpectedResult() { return true; }

    public HasDialogQuery(Driver driver, String text, DriverScope driverScope, Options options)
    {
        super(driverScope,options);
        this.driver = driver;
        this.text = text;
    }

    public void Run() throws MissingHtmlException {
        SetResult(driver.HasDialog(text,DriverScope()));
    }
}
