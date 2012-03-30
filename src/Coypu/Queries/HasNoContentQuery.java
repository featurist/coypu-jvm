package Coypu.Queries;

import Coypu.*;

public class HasNoContentQuery extends DriverScopeQuery<Boolean> {
    private final Driver driver;
    private final String text;

    public Boolean ExpectedResult() {
        return true;
    }

    public HasNoContentQuery(Driver driver, DriverScope scope, String text, Options options) {
        super(scope, options);
        this.driver = driver;
        this.text = text;
    }

    public void Run() {
        SetResult(!driver.HasContent(text, DriverScope()));
    }
}
