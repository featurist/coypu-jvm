package coypu.Finders;

import coypu.*;

public class XPathFinder extends ElementFinder {
    public XPathFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound find() {
        return Driver.findXPath(locator(), Scope);
    }
}
