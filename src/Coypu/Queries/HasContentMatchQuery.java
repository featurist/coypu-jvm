package Coypu.Queries;

import Coypu.*;

import java.util.regex.Pattern;

public class HasContentMatchQuery extends DriverScopeQuery<Boolean> {
    private final Driver driver;
    private final Pattern text;

    public Object ExpectedResult() {
        return true;
    }

    public HasContentMatchQuery(Driver driver, DriverScope scope, Pattern text, Options options) {
        super(scope, options);
        this.driver = driver;
        this.text = text;
    }

    public void Run() {
        SetResult(driver.HasContentMatch(text, DriverScope()));
    }
}
