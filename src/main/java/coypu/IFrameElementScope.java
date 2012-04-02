package coypu;

import coypu.Finders.ElementFinder;

public class IFrameElementScope extends RobustElementScope {
    public IFrameElementScope(ElementFinder elementFinder, DriverScope outerScope, Options options) {
        super(elementFinder, outerScope, options);
    }

    public String location() {
        findXPath("/*", options).now();
        return driver.location();
    }
}
