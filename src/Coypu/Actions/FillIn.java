package Coypu.Actions;

import Coypu.DriverScope;
import Coypu.Driver;
import Coypu.Element;
import Coypu.Options;

public class FillIn extends DriverAction
{
    private String locator;
    private DriverScope scope;
    private String value;
    private Element element;

    public FillIn(Driver driver, DriverScope scope, String locator, Element element, String value, Options options)
    {
        super(driver, options);
        this.locator = locator;
        this.element = element;
        this.scope = scope;
        this.value = value;
    }

    public Element Field()
    {
        return element == null ?  Driver.FindField(locator, scope) : element;
    }

    private void BringIntoFocus()
    {
        Driver.Click(Field());
    }

    public void Set()
    {
        Driver.Set(Field(), value);
    }

    public void Focus()
    {
        if (Field().Attribute("type") != "file")
            BringIntoFocus();
    }

    public void Act()
    {
        Focus();
        Set();
    }
}