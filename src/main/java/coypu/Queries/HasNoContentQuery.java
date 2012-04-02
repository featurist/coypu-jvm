package coypu.Queries;

import coypu.*;

public class HasNoContentQuery extends DriverScopeQuery<Boolean> {
    private final Driver driver;
    private final String text;

    public Boolean getExpectedResult() {
        return true;
    }

    public HasNoContentQuery(Driver driver, DriverScope scope, String text, Options options) {
        super(scope, options);
        this.driver = driver;
        this.text = text;
    }

    public Boolean run() {
        return !driver.hasContent(text, driverScope());
    }
}
