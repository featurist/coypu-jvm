package Coypu.Actions;

import Coypu.DriverScope;
import Coypu.ElementFound;
import Coypu.Options;
import Coypu.Driver;

public class Hover extends DriverAction
{
    private DriverScope driverScope;

    public Hover(DriverScope driverScope, Driver driver, Options options)
    {
        super(driver, options);
        this.driverScope = driverScope;
    }

    public void Act()
    {
        ElementFound element = driverScope.Now();
        Driver.Hover(element);
    }
}
