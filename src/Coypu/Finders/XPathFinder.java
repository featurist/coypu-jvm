package Coypu.Finders;

import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.ElementFound;

public class XPathFinder extends ElementFinder
{
    public XPathFinder(Driver driver, String locator, DriverScope scope)
    {
        super(driver, locator, scope);
    }

    public ElementFound Find()
    {
        return Driver.FindXPath(Locator(), Scope);
    }
}