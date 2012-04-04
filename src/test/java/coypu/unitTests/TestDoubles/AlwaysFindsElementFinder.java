package coypu.unitTests.TestDoubles;

import coypu.ElementFound;
import coypu.Finders.FindByLocatorElementFinder;

public class AlwaysFindsElementFinder extends FindByLocatorElementFinder
{
    private final ElementFound element;

    public AlwaysFindsElementFinder(ElementFound element)
    {
        super(null, null, null);
        this.element = element;
    }

    public ElementFound find()
    {
        return element;
    }
}
