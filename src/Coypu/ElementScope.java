package Coypu;

import Coypu.Actions;
import Coypu.Finders;
import Coypu.Queries;
import Coypu.Robustness;

public class ElementScope
{
    public ElementScope(ElementFinder elementFinder, DriverScope outer, RobustWrapper robustWrapper)
    {
        super();
    }

    public String Id()
    {
        return Now().Id;
    }

    public String Text()
    {
        return Now().Text;
    }

    public String Value()
    {
        return Now().Value;
    }

    public String Name()
    {
        return Now().Name;
    }

    public String SelectedOption()
    {
        return Now().SelectedOption;
    }

    public bool Selected()
    {
        return Now().Selected;
    }

    public object Native()
    {
        return Now().Native;
    }

    public String getAttribute(String attributeName)
    {
        return Now().getAttribute(attributeName);
    }

    public ElementScope Click(Options options)
    {
        RetryUntilTimeout(new Click(this, driver, SetOptions(options)));
        return this;
    }

    public boolean Exists(Options options)
    {
        return robustWrapper.Robustly(new ElementExistsQuery(this, SetOptions(options)));
    }

    public boolean Missing(Options options)
    {
        return robustWrapper.Robustly(new ElementMissingQuery(this, SetOptions(options)));
    }
}
