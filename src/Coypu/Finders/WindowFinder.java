package Coypu.Finders;

import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.ElementFound;

public class WindowFinder extends ElementFinder
{
    public WindowFinder(Driver driver, String locator, DriverScope scope)
    {
        super(driver, locator, scope);
    }

    public ElementFound Find()
    {
        return Driver.FindWindow(Locator(), Scope);
    }
}