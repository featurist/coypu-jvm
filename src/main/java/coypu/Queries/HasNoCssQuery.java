package coypu.Queries;

import coypu.*;

public class HasNoCssQuery extends DriverScopeQuery<Boolean> {
    private final Driver driver;
    private final String cssSelector;

    public HasNoCssQuery(Driver driver, DriverScope scope, String cssSelector, Options options) {
        super(scope, options);
        this.driver = driver;
        this.cssSelector = cssSelector;
    }

    public Boolean getExpectedResult() {
        return true;
    }

    public Boolean run() {
        return !driver.hasCss(cssSelector, driverScope());
    }
}
