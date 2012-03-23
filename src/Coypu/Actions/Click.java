package Coypu.Actions;

import Coypu.*;

public class Click extends DriverAction
{
    private DriverScope driverScope;

    public Click(DriverScope driverScope, Driver driver, Options options)
    {
        super(driver, options);
        this.driverScope = driverScope;
    }

    public void Act() throws MissingHtmlException, TimeoutException {
        ElementFound element = driverScope.Now();
        Driver.Click(element);
    }
}
