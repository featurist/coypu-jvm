package coypu.Actions;

import coypu.*;

public class AcceptModalDialog extends DriverAction {
    private DriverScope driverScope;

    public AcceptModalDialog(DriverScope driverScope, Driver driver, Options options) {
        super(driver, options);
        this.driverScope = driverScope;
    }

    public void act() {
        Driver.acceptModalDialog(driverScope);
    }
}
