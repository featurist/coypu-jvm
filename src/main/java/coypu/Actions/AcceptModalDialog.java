package coypu.Actions;

import coypu.DriverScope;
import coypu.Options;

public class AcceptModalDialog extends DriverAction {
    private DriverScope driverScope;

    public AcceptModalDialog(DriverScope driverScope, coypu.Driver driver, Options options) {
        super(driver, options);
        this.driverScope = driverScope;
    }

    public void act() {
        Driver.acceptModalDialog(driverScope);
    }
}
