package Coypu.Finders;

import Coypu.DriverScope;
import Coypu.Driver;
import Coypu.ElementFound;

public class ButtonFinder extends ElementFinder
{
    public ButtonFinder(Driver driver, String locator, DriverScope scope)
    {
        super(driver, locator, scope);
    }

    public ElementFound Find()
    {
        return Driver.FindButton(Locator(), Scope);
    }
}
