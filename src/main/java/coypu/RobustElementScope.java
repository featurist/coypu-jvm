package coypu;

import coypu.Finders.ElementFinder;
import coypu.Queries.ElementQuery;

public class RobustElementScope extends ElementScope {
    protected final Options options;

    public RobustElementScope(ElementFinder elementFinder, DriverScope outerScope, Options options) {
        super(elementFinder, outerScope);
        this.options = options;
    }

    public ElementFound now() {
        return robustWrapper.robustly(new ElementQuery(this, options));
    }
}
