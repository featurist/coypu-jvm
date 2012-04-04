package coypu.Finders;

import coypu.Driver;
import coypu.ElementFound;

public class DocumentElementFinder extends FindByLocatorElementFinder {
    public DocumentElementFinder(Driver driver) {
        super(driver, "Window", null);
    }

    public ElementFound find() {
        return Driver.window();
    }
}
