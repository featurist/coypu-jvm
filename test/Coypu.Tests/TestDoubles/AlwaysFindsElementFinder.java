package Coypu.Tests.TestDoubles;

import Coypu.ElementFound;
import Coypu.Finders.ElementFinder;

public class AlwaysFindsElementFinder extends ElementFinder
{
    private final ElementFound element;

    public AlwaysFindsElementFinder(ElementFound element)
    {
        super(null, null, null);
        this.element = element;
    }

    public ElementFound Find()
    {
        return element;
    }
}
