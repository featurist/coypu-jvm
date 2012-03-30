package Coypu.Queries;

import Coypu.*;

public class ElementQuery extends DriverScopeQuery<ElementFound> {
    public ElementQuery(DriverScope driverScope, Options options) {
        super(driverScope, options);
    }

    public ElementFound ExpectedResult() {
        return null;
    }

    public void Run() {
        SetResult(DriverScope().FindElement());
    }
}
