package Coypu.Queries;

import Coypu.*;

public class ElementQuery extends DriverScopeQuery<ElementFound> {
    public ElementQuery(DriverScope driverScope, Options options) {
        super(driverScope, options);
    }

    public ElementFound getExpectedResult() {
        return null;
    }

    public ElementFound run() {
        return driverScope().findElement();
    }
}
