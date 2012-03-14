package Coypu.Finders;
import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.ElementFound;

public class IFrameFinder extends ElementFinder
{
    public IFrameFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope)       ;
    }

    public ElementFound Find()
    {
        return Driver.FindIFrame(Locator(), Scope);
    }
}