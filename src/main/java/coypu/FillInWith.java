package coypu;

import coypu.Actions.FillIn;
import coypu.Robustness.RobustWrapper;

public class FillInWith {
    private final String locator;
    private final Driver driver;
    private final RobustWrapper robustWrapper;
    private final DriverScope scope;
    private final Options options;
    private final Element element;

    public FillInWith(String locator, Driver driver, RobustWrapper robustWrapper, DriverScope scope, Options options) {
        this.locator = locator;
        this.driver = driver;
        this.robustWrapper = robustWrapper;
        this.scope = scope;
        this.options = options;
        this.element = null;
    }

    public FillInWith(Element element, Driver driver, RobustWrapper robustWrapper, DriverScope scope, Options options) {
        this.element = element;
        this.driver = driver;
        this.robustWrapper = robustWrapper;
        this.scope = scope;
        this.options = options;
        this.locator = null;
    }

    /**
     *  Supply a value for the text field
     *
     *  @param   value    The value to fill in
     */
    public void with(String value) {
        with(value,false);
    }

    /**
     *  Supply a value for the text field
     *
     *  @param   value          The value to fill in
     *  @param   forceAllEvents Use native driver implementation to ensure all keyboard events are fired, rather than the default of setting with JavaScript
     */
    public void with(String value, boolean forceAllEvents) {
        robustWrapper.robustly(new FillIn(driver, scope, locator, element, value, forceAllEvents, options));
    }
}
