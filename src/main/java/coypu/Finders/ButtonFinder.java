package coypu.Finders;

import coypu.*;

public class ButtonFinder extends FindByLocatorElementFinder {
    public ButtonFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound find() {
        return Driver.findButton(locator(), Scope);
    }
}
