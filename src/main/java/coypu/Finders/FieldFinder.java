package coypu.Finders;

import coypu.*;

public class FieldFinder extends FindByLocatorElementFinder {
    public FieldFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound find() {
        return Driver.findField(locator(), Scope);
    }
}
