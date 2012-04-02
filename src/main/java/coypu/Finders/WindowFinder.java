package coypu.Finders;

import coypu.Driver;
import coypu.DriverScope;
import coypu.ElementFound;
import coypu.MissingHtmlException;

public class WindowFinder extends ElementFinder {
    public WindowFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound find() {
        return Driver.findWindow(locator(), Scope);
    }
}
