package coypu.Finders;

import coypu.*;

public class FieldsetFinder extends ElementFinder {
    public FieldsetFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound find() {
        return Driver.findFieldset(locator(), Scope);
    }
}
