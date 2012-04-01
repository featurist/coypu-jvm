package Coypu.Queries;

import Coypu.DriverScope;
import Coypu.MissingHtmlException;
import Coypu.Options;

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
