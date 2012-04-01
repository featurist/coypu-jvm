package Coypu.Queries;

import Coypu.DriverScope;
import Coypu.MissingHtmlException;
import Coypu.Options;

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
