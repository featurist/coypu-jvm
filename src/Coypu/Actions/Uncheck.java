package Coypu.Actions;

import Coypu.*;

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

    public void Act() throws MissingHtmlException {
        Driver.Uncheck(Driver.FindField(locator, scope));
    }
}
