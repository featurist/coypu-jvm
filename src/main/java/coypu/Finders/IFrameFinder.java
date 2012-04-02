package coypu.Finders;

import coypu.*;

public class IFrameFinder extends ElementFinder {
    public IFrameFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound find() {
        return Driver.findIFrame(locator(), Scope);
    }
}
