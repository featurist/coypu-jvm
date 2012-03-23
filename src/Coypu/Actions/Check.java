package Coypu.Actions;

import Coypu.*;

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

    public void Act() throws MissingHtmlException, TimeoutException {
        Driver.Check(Driver.FindField(locator, scope));
    }
}
