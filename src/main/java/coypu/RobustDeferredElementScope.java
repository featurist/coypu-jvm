package coypu;

import coypu.Finders.ElementFinder;
import coypu.Queries.ElementQuery;

public class RobustDeferredElementScope extends DeferredElementScope {
    protected final Options options;

    public RobustDeferredElementScope(ElementFinder elementFinder, DriverScope outerScope, Options options) {
        super(elementFinder, outerScope);
        this.options = options;
    }

    public ElementFound now() {
        return robustWrapper.robustly(new ElementQuery(this, options));
    }
}
