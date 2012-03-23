package Coypu;

import Coypu.Actions.FillIn;
import Coypu.Robustness.RobustWrapper;

public class FillInWith
{
    private final String locator;
    private final Driver driver;
    private final RobustWrapper robustWrapper;
    private final DriverScope scope;
    private final Options options;
    private final Element element;

    public FillInWith(String locator, Driver driver, RobustWrapper robustWrapper, DriverScope scope, Options options)
    {
        this.locator = locator;
        this.driver = driver;
        this.robustWrapper = robustWrapper;
        this.scope = scope;
        this.options = options;
        this.element = null;
    }

    public FillInWith(Element element, Driver driver, RobustWrapper robustWrapper, DriverScope scope, Options options)
    {
        this.element = element;
        this.driver = driver;
        this.robustWrapper = robustWrapper;
        this.scope = scope;
        this.options = options;
        this.locator = null;
    }

    /// <summary>
    /// Supply a value for the text field
    /// </summary>
    /// <param name="value">The value to fill in</param>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    public void With(String value) throws MissingHtmlException {
        robustWrapper.Robustly(new FillIn(driver, scope, locator, element, value,options));
    }
}
