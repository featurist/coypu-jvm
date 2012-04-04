package coypu.Finders;

import coypu.Driver;
import coypu.DriverScope;
import coypu.ElementFound;

public class WindowFinder extends FindByLocatorElementFinder {
    public WindowFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound find() {
        return Driver.findWindow(locator(), Scope);
    }
}
