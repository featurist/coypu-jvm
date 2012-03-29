package Coypu.Actions;

import Coypu.*;

public class FillIn extends DriverAction {
    private String locator;
    private DriverScope scope;
    private String value;
    private ElementScope elementScope;

    public FillIn(Driver driver, DriverScope scope, String locator, ElementScope elementScope, String value, Options options) {
        super(driver, options);
        this.locator = locator;
        this.elementScope = elementScope;
        this.scope = scope;
        this.value = value;
    }

    public Element Field() {
        return elementScope == null ? Driver.FindField(locator, scope) : elementScope.Now();
    }

    private void BringIntoFocus() {
        Driver.Click(Field());
    }

    public void Set() {
        Driver.Set(Field(), value);
    }

    public void Focus() {
        if (Field().Attribute("type") != "file")
            BringIntoFocus();
    }

    public void Act() {
        Focus();
        Set();
    }
}
