package Coypu.Finders;
import Coypu.Driver;
import Coypu.ElementFound;

public class DocumentElementFinder extends  ElementFinder
{
    public DocumentElementFinder(Driver driver)
    {
        super(driver, "Window",null);
    }

    public ElementFound Find()
    {
        return Driver.Window();
    }
}
