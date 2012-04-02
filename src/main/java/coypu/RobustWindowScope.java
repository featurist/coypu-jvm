package coypu;

import coypu.Finders.WindowFinder;
import coypu.Queries.ElementQuery;
import coypu.Robustness.RobustWrapper;
import coypu.Robustness.Waiter;

public class RobustWindowScope extends BrowserWindow {
    private final Options options;

    public RobustWindowScope(Driver driver, SessionConfiguration sessionConfiguration, RobustWrapper robustWrapper, Waiter waiter, UrlBuilder urlBuilder, Options options, WindowFinder windowFinder) {
        super(sessionConfiguration, windowFinder, driver, robustWrapper, waiter, urlBuilder);
        this.options = options;
    }

    public ElementFound now() {
        return robustWrapper.robustly(new ElementQuery(this, options));
    }
}
