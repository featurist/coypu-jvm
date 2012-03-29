package Coypu.Queries;

import Coypu.DriverScope;
import Coypu.MissingHtmlException;
import Coypu.Options;

public class ElementMissingQuery extends DriverScopeQuery<Boolean> {
    public Object ExpectedResult() {
        return true;
    }

    public ElementMissingQuery(DriverScope driverScope, Options options) {
        super(driverScope, options);
    }

    public void Run() {
        try {
            DriverScope().FindElement();
            SetResult(false);
        } catch (MissingHtmlException ex) {
            SetResult(true);
        }
    }
}
