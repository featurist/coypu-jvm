package coypu.Queries;

import coypu.*;

public class HasCssQuery extends DriverScopeQuery<Boolean> {
    private final Driver driver;
    private final String cssSelector;

    public Boolean getExpectedResult() {
        return true;
    }

    public HasCssQuery(Driver driver, DriverScope scope, String cssSelector, Options options) {
        super(scope, options);
        this.driver = driver;
        this.cssSelector = cssSelector;
    }

    public Boolean run() {
        return driver.hasCss(cssSelector, driverScope());
    }
}
