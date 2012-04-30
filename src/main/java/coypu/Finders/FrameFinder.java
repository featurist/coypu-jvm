package coypu.Finders;

import coypu.*;

public class FrameFinder extends FindByLocatorElementFinder {
    public FrameFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound find() {
        return Driver.findFrame(locator(), Scope);
    }
}
