package coypu.Finders;

import coypu.*;

public class LinkFinder extends FindByLocatorElementFinder {
    public LinkFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound find() {
        return Driver.findLink(locator(), Scope);
    }
}
