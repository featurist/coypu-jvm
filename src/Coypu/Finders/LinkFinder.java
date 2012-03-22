package Coypu.Finders;
import Coypu.*;

public class LinkFinder extends ElementFinder
{
    public LinkFinder(Driver driver, String locator, DriverScope scope)
    {
        super(driver, locator, scope);
    }

    public ElementFound Find() throws MissingHtmlException, TimeoutException, InterruptedException {
        return Driver.FindLink(Locator(), Scope);
    }
}
