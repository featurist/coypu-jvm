package Coypu;

import Coypu.Finders.ElementFinder;
import Coypu.Queries.ElementQuery;
import Coypu.Robustness.RobustWrapper;

public class RobustElementScope extends ElementScope
{
    protected final Options options;

    public RobustElementScope(ElementFinder elementFinder, DriverScope outerScope, Options options)
    {
        super(elementFinder, outerScope);
        this.options = options;
    }

    public ElementFound Now()
    {
        return robustWrapper.Robustly(new ElementQuery(this, options));
    }
}
