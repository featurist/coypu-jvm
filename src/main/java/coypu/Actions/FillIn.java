package coypu.Actions;

import coypu.*;

public class FillIn extends DriverAction {
    private String locator;
    private DriverScope scope;
    private String value;
    private Element element;

    public FillIn(Driver driver, DriverScope scope, String locator, Element elementScope, String value, Options options) {
        super(driver, options);
        this.locator = locator;
        this.element = elementScope;
        this.scope = scope;
        this.value = value;
    }

    public Element field() {
        if (element == null)
            element = Driver.findField(locator, scope);

        return element;
    }

    private void bringIntoFocus() {
        Driver.click(field());
    }

    public void set() {
        Driver.set(field(), value);
    }

    public void focus() {
        if (field().getAttribute("type") != "file")
            bringIntoFocus();
    }

    public void act() {
        focus();
        set();
    }
}
