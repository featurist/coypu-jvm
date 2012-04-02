package coypu.Queries;

import coypu.DriverScope;
import coypu.MissingHtmlException;
import coypu.Options;

public class ElementExistsQuery extends DriverScopeQuery<Boolean> {
    public Boolean getExpectedResult() {
        return true;
    }

    public ElementExistsQuery(DriverScope driverScope, Options options) {
        super(driverScope, options);
    }

    public Boolean run() {
        try {
            driverScope().findElement();
            return true;
        } catch (MissingHtmlException ex) {
            return false;
        }
    }
}
