package Coypu.Actions;

import Coypu.DriverScope;
import Coypu.Options;
import Coypu.Driver;

public class Check extends DriverAction
{
    private DriverScope scope;
    private String locator;

    public Check(Driver driver, DriverScope scope, String locator, Options options)
    {
        super(driver, options);

        this.scope = scope;
        this.locator = locator;
    }

    public void Act()
    {
        Driver.Check(Driver.FindField(locator, scope));
    }
}
