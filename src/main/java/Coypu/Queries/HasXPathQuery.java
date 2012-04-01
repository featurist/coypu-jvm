package Coypu.Queries;

import Coypu.*;

public class HasXPathQuery extends DriverScopeQuery<Boolean> {
    private final Driver driver;
    private final String xpath;

    public Boolean getExpectedResult() {
        return true;
    }

    public HasXPathQuery(Driver driver, DriverScope scope, String xpath, Options options) {
        super(scope, options);

        this.driver = driver;
        this.xpath = xpath;
    }

    public Boolean run() {
        return driver.hasXPath(xpath, driverScope());
    }
}
