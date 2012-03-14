package Coypu.Finders;
import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.ElementFound;

public class LinkFinder extends ElementFinder
{
    public LinkFinder(Driver driver, String locator, DriverScope scope)
    {
        super(driver, locator, scope);
    }

    public ElementFound Find()
    {
        return Driver.FindLink(Locator(), Scope);
    }
}
