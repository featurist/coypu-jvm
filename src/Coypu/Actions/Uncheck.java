package Coypu.Actions;

import Coypu.DriverScope;
import Coypu.Options;
import Coypu.Driver;

public class Uncheck extends DriverAction
{
    private DriverScope scope;
    private String locator;

    public Uncheck(Driver driver, DriverScope scope, String locator, Options options)
    {
        super(driver, options);
        this.scope = scope;
        this.locator = locator;
    }

    public void Act()
    {
        Driver.Uncheck(Driver.FindField(locator, scope));
    }
}
