package coypu.Finders;

import coypu.*;

public abstract class FindByLocatorElementFinder implements ElementFinder {
    protected Driver Driver;
    private String locator;
    protected DriverScope Scope;

    protected FindByLocatorElementFinder(Driver driver, String locator, DriverScope scope) {
        Driver = driver;
        this.locator = locator;
        Scope = scope;
    }

    public String locator() {
        return this.locator;
    }

}
