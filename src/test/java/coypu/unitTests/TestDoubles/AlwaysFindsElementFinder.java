package coypu.unitTests.TestDoubles;

import coypu.ElementFound;
import coypu.Finders.ElementFinder;

public class AlwaysFindsElementFinder extends ElementFinder
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
