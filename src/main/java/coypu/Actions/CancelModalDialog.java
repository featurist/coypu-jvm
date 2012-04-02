package coypu.Actions;

import coypu.*;

public class CancelModalDialog extends DriverAction {
    private DriverScope driverScope;

    public CancelModalDialog(DriverScope driverScope, Driver driver, Options options) {
        super(driver, options);
        this.driverScope = driverScope;
    }

    public void act() {
        Driver.cancelModalDialog(driverScope);
    }
}
