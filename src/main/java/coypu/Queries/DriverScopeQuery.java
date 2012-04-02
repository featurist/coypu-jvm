package coypu.Queries;

import coypu.*;

public abstract class DriverScopeQuery<T> extends Query<T> {
    private DriverScope driverScope;

    protected DriverScope driverScope() {
        return driverScope;
    }

    public DriverScopeQuery(DriverScope driverScope, Options options) {
        super(options);
        this.driverScope = driverScope;
    }

    public abstract T getExpectedResult();

}
