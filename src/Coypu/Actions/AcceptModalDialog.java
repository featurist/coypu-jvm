package Coypu.Actions;

import Coypu.DriverScope;
import Coypu.Options;
import Coypu.Driver;

public class AcceptModalDialog extends DriverAction
{
    private DriverScope driverScope;

    public AcceptModalDialog(DriverScope driverScope, Driver driver, Options options)
    {
        super(driver, options);
        this.driverScope = driverScope;
    }

    public void Act()
    {
        Driver.AcceptModalDialog(driverScope);
    }
}
