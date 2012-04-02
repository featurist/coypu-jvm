package coypu.Queries;

import coypu.DriverScope;
import coypu.MissingHtmlException;
import coypu.Options;

public class ElementMissingQuery extends DriverScopeQuery<Boolean> {
    public Boolean getExpectedResult() {
        return true;
    }

    public ElementMissingQuery(DriverScope driverScope, Options options) {
        super(driverScope, options);
    }

    public Boolean run() {
        try {
            driverScope().findElement();
            return false;
        } catch (MissingHtmlException ex) {
            return true;
        }
    }
}
