package Coypu.Finders;
import Coypu.*;

public class SectionFinder extends ElementFinder
{
    public SectionFinder(Driver driver, String locator, DriverScope scope)
    {
        super(driver, locator, scope);
    }

    public ElementFound Find()  {
        return Driver.FindSection(Locator(), Scope);
    }
}
