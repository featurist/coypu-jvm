package Coypu.Finders;
import Coypu.*;

public class IFrameFinder extends ElementFinder
{
    public IFrameFinder(Driver driver, String locator, DriverScope scope) {
        super(driver, locator, scope)       ;
    }

    public ElementFound Find() throws MissingHtmlException {
        return Driver.FindIFrame(Locator(), Scope);
    }
}
