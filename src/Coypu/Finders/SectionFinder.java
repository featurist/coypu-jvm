package Coypu.Finders;
import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.ElementFound;

public class SectionFinder extends ElementFinder
{
    public SectionFinder(Driver driver, String locator, DriverScope scope)
    {
        super(driver, locator, scope);
    }

    public ElementFound Find()
    {
        return Driver.FindSection(Locator(), Scope);
    }
}
