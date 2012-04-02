package coypu.Finders;

import coypu.*;

public class SectionFinder extends ElementFinder {
    public SectionFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound find() {
        return Driver.findSection(locator(), Scope);
    }
}
