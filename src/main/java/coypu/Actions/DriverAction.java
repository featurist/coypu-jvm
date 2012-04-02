package coypu.Actions;

import coypu.Options;
import coypu.Driver;

public abstract class DriverAction extends BrowserAction {
    protected Driver Driver;

    protected DriverAction(Driver driver, Options options) {
        super(options);
        Driver = driver;
    }
}
