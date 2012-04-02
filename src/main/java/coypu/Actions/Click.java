package coypu.Actions;

import coypu.*;

public class Click extends DriverAction {
    private DriverScope driverScope;

    public Click(DriverScope driverScope, Driver driver, Options options) {
        super(driver, options);
        this.driverScope = driverScope;
    }

    public void act() {
        ElementFound element = driverScope.now();
        Driver.click(element);
    }
}
