package Coypu.Actions;
import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.Options;

public class Choose extends DriverAction
{
    private DriverScope scope;
    private String locator;

    public Choose(Driver driver, DriverScope scope, String locator, Options options)
    {
        super(driver, options);
        this.scope = scope;
        this.locator = locator;
    }

    public void Act()
    {
        Driver.Choose(Driver.FindField(locator, scope));
    }
}
