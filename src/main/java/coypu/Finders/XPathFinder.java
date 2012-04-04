package coypu.Finders;

import coypu.*;

public class XPathFinder extends FindByLocatorElementFinder {
    public XPathFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound find() {
        return Driver.findXPath(locator(), Scope);
    }
}
