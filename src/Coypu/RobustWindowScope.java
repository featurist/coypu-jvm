package Coypu;

import Coypu.Finders.WindowFinder;
import Coypu.Queries.ElementQuery;
import Coypu.Robustness.RobustWrapper;
import Coypu.Robustness.Waiter;

public class RobustWindowScope extends BrowserWindow {
    private final Options options;

    public RobustWindowScope(Driver driver, Configuration configuration, RobustWrapper robustWrapper, Waiter waiter, UrlBuilder urlBuilder, Options options, WindowFinder windowFinder) {
        super(configuration, windowFinder, driver, robustWrapper, waiter, urlBuilder);
        this.options = options;
    }

    public ElementFound Now() {
        return robustWrapper.Robustly(new ElementQuery(this, options));
    }
}
