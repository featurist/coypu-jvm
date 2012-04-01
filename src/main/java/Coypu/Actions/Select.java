package Coypu.Actions;

import Coypu.*;

public class Select extends DriverAction {
    private DriverScope scope;
    private String locator;
    private String option;

    public Select(Driver driver, DriverScope scope, String locator, String option, Options timingOptions) {
        super(driver, timingOptions);
        this.scope = scope;
        this.locator = locator;
        this.option = option;
    }

    public void act() {
        Driver.select(Driver.findField(locator, scope), option);
    }
}
