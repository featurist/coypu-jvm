package coypu.Queries;

import coypu.*;

import java.util.regex.Pattern;

public class HasNoContentMatchQuery extends DriverScopeQuery<Boolean> {
    private final Driver driver;
    private final Pattern text;

    public Boolean getExpectedResult() {
        return true;
    }

    public HasNoContentMatchQuery(Driver driver, DriverScope scope, Pattern text, Options options) {
        super(scope, options);
        this.driver = driver;
        this.text = text;
    }

    public Boolean run() {
        return !driver.hasContentMatch(text, driverScope());
    }
}
