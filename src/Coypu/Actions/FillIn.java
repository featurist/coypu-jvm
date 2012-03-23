package Coypu.Actions;

import Coypu.*;

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

    public Element Field() throws MissingHtmlException {
        return element == null ?  Driver.FindField(locator, scope) : element;
    }

    private void BringIntoFocus() throws MissingHtmlException {
        Driver.Click(Field());
    }

    public void Set() throws MissingHtmlException {
        Driver.Set(Field(), value);
    }

    public void Focus() throws MissingHtmlException {
        if (Field().Attribute("type") != "file")
            BringIntoFocus();
    }

    public void Act() throws MissingHtmlException {
        Focus();
        Set();
    }
}
