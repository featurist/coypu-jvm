package Coypu.Finders;

import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.ElementFound;

public class CssFinder extends ElementFinder
{
    public CssFinder(Driver driver, String locator, DriverScope scope)
    {
        super(driver, locator, scope);
    }

    public ElementFound Find()
    {
        return Driver.FindCss(Locator(), Scope);
    }
}
