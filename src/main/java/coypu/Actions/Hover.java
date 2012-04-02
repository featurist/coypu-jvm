package coypu.Actions;

import coypu.*;

public class Hover extends DriverAction {
    private DriverScope driverScope;

    public Hover(DriverScope driverScope, Driver driver, Options options) {
        super(driver, options);
        this.driverScope = driverScope;
    }

    public void act() {
        ElementFound element = driverScope.now();
        Driver.hover(element);
    }
}
