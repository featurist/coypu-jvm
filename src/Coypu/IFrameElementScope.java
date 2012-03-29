package Coypu;

import Coypu.Finders.ElementFinder;

public class IFrameElementScope extends RobustElementScope {
    public IFrameElementScope(ElementFinder elementFinder, DriverScope outerScope, Options options) {
        super(elementFinder, outerScope, options);
    }

    public String Location() {
        FindXPath("/*", options).Now();
        return driver.Location();
    }
}
