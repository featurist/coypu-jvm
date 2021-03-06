package coypu;

import coypu.Actions.Select;
import coypu.Robustness.RobustWrapper;

public class SelectFrom {
    private final String option;
    private final Driver driver;
    private final RobustWrapper robustWrapper;
    private final DriverScope scope;
    private final Options options;

    public SelectFrom(String option, Driver driver, RobustWrapper robustWrapper, DriverScope scope, Options options) {
        this.option = option;
        this.driver = driver;
        this.robustWrapper = robustWrapper;
        this.scope = scope;
        this.options = options;
    }

   /**
    *  Find the first matching select to appear within the SessionConfiguration.Timeout from which to select this option.
    *
    *  @param   locator    The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)
    */
    public void from(String locator) {
        robustWrapper.robustly(new Select(driver, scope, locator, option, options));
    }

}
