package Coypu;

import Coypu.Actions.Click;
import Coypu.Finders.ElementFinder;
import Coypu.Queries.ElementExistsQuery;
import Coypu.Queries.ElementMissingQuery;
import Coypu.Robustness.RobustWrapper;

public class ElementScope extends DriverScope
{
    public ElementScope(ElementFinder elementFinder, DriverScope outer)
    {
        super(elementFinder,outer);
    }

    public String Id() throws MissingHtmlException {
        return Now().Id();
    }

    public String Text() throws MissingHtmlException {
        return Now().Text();
    }

    public String Value() throws MissingHtmlException {
        return Now().Value();
    }

    public String Name() throws MissingHtmlException {
        return Now().Name();
    }

    public String SelectedOption() throws MissingHtmlException {
        return Now().SelectedOption();
    }

    public boolean Selected() throws MissingHtmlException {
        return Now().Selected();
    }

    public Object Native() throws MissingHtmlException {
        return Now().Native();
    }

    public String Attribute(String attributeName) throws MissingHtmlException {
        return Now().Attribute(attributeName);
    }

    public ElementScope Click(Options options) throws MissingHtmlException {
        RetryUntilTimeout(new Click(this, driver, SetOptions(options)));
        return this;
    }

    public boolean Exists(Options options) throws MissingHtmlException {
        return robustWrapper.Robustly(new ElementExistsQuery(this, SetOptions(options)));
    }

    public boolean Missing(Options options) throws MissingHtmlException {
        return robustWrapper.Robustly(new ElementMissingQuery(this, SetOptions(options)));
    }
}
