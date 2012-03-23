package Coypu.Finders;

import Coypu.*;

public class FieldFinder extends ElementFinder
{
    public FieldFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope);
    }

    public ElementFound Find() throws MissingHtmlException {
        return Driver.FindField(Locator(), Scope);
    }
}
