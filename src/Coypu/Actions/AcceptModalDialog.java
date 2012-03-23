package Coypu.Actions;

import Coypu.*;

public class AcceptModalDialog extends DriverAction
{
    private DriverScope driverScope;

    public AcceptModalDialog(DriverScope driverScope, Driver driver, Options options)
    {
        super(driver, options);
        this.driverScope = driverScope;
    }

    public void Act() throws MissingHtmlException, TimeoutException {
        Driver.AcceptModalDialog(driverScope);
    }
}
