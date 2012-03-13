package Coypu.Actions;

import Coypu.Options;
import Coypu.Driver;

public abstract class DriverAction extends BrowserAction
{
    protected Driver Driver;

    protected DriverAction(Driver driver, Options options)
    {
        super(options);
        Driver = driver;
    }
}
