package Coypu.Actions;
import Coypu.*;

public class Select extends DriverAction
{
    private DriverScope scope;
    private String locator;
    private String option;

    public Select(Driver driver, DriverScope scope, String locator, String option, Options timingOptions)
    {
        super(driver, timingOptions);
        this.scope = scope;
        this.locator = locator;
        this.option = option;
    }

    public void Act() throws MissingHtmlException {
        Driver.Select(Driver.FindField(locator, scope),option);
    }
}
