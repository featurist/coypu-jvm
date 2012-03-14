package Coypu.Finders;

import Coypu.DriverScope;
import Coypu.ElementFound;
import Coypu.Driver;

public class FieldFinder extends ElementFinder
{
    public FieldFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound Find()
    {
        return Driver.FindField(Locator(), Scope);
    }
}
