package Coypu.Actions;

import Coypu.*;

public class Hover extends DriverAction
{
    private DriverScope driverScope;

    public Hover(DriverScope driverScope, Driver driver, Options options)
    {
        super(driver, options);
        this.driverScope = driverScope;
    }

    public void Act() throws MissingHtmlException {
        ElementFound element = driverScope.Now();
        Driver.Hover(element);
    }
}
