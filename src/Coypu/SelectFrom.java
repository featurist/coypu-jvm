package Coypu;

import Coypu.Actions.Select;
import Coypu.Robustness.RobustWrapper;

public class SelectFrom
{
    private final String option;
    private final Driver driver;
    private final RobustWrapper robustWrapper;
    private final DriverScope scope;
    private final Options options;

    public SelectFrom(String option, Driver driver, RobustWrapper robustWrapper, DriverScope scope, Options options)
    {
        this.option = option;
        this.driver = driver;
        this.robustWrapper = robustWrapper;
        this.scope = scope;
        this.options = options;
    }

    /// <summary>
    /// Find the first matching select to appear within the Configuration.Timeout from which to select this option.
    /// </summary>
    /// <param name="locator">The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)</param>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    public void From(String locator)
    {
        robustWrapper.Robustly(new Select(driver, scope, locator, option, options));
    }

}
