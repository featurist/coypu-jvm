package Coypu.Finders;

import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.ElementFound;
import Coypu.MissingHtmlException;

public class WindowFinder extends ElementFinder {
    public WindowFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound find() {
        return Driver.findWindow(locator(), Scope);
    }
}
