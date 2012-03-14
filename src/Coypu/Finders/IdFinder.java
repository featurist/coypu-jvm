package Coypu.Finders;
import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.ElementFound;

public class IdFinder extends ElementFinder
{
    public IdFinder(Driver driver, String locator, DriverScope scope)
    {
        super(driver, locator, scope);
    }

    public ElementFound Find()
    {
        return Driver.FindId(Locator(), Scope);
    }
}