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

    public String Id()  {
        return Now().Id();
    }

    public String Text()  {
        return Now().Text();
    }

    public String Value()  {
        return Now().Value();
    }

    public String Name()  {
        return Now().Name();
    }

    public String SelectedOption()  {
        return Now().SelectedOption();
    }

    public boolean Selected()  {
        return Now().Selected();
    }

    public Object Native()  {
        return Now().Native();
    }

    public String Attribute(String attributeName)  {
        return Now().Attribute(attributeName);
    }

    public ElementScope Click(Options options)  {
        RetryUntilTimeout(new Click(this, driver, SetOptions(options)));
        return this;
    }

    public boolean Exists(Options options)  {
        return robustWrapper.Robustly(new ElementExistsQuery(this, SetOptions(options)));
    }

    public boolean Missing(Options options)  {
        return robustWrapper.Robustly(new ElementMissingQuery(this, SetOptions(options)));
    }
}
