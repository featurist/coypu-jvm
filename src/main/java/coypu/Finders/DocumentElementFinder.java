package coypu.Finders;

import coypu.Driver;
import coypu.ElementFound;

public class DocumentElementFinder extends ElementFinder {
    public DocumentElementFinder(Driver driver) {
        super(driver, "Window", null);
    }

    public ElementFound find() {
        return Driver.window();
    }
}
