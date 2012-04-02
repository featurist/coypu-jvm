package coypu.Finders;

import coypu.*;

public abstract class ElementFinder {
    protected Driver Driver;
    private String locator;
    protected DriverScope Scope;

    protected ElementFinder(Driver driver, String locator, DriverScope scope) {
        Driver = driver;
        this.locator = locator;
        Scope = scope;
    }

    public String locator() {
        return this.locator;
    }

    public abstract ElementFound find();
}
