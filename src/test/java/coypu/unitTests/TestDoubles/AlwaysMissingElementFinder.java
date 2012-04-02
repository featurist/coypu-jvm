package coypu.unitTests.TestDoubles;

import coypu.ElementFound;
import coypu.Finders.ElementFinder;
import coypu.MissingHtmlException;

public class AlwaysMissingElementFinder extends ElementFinder
{
    public AlwaysMissingElementFinder()
    {
        super(null, null, null);
    }

    public ElementFound find()
    {
        throw new MissingHtmlException("From AlwaysMissingElementFinder");
    }
}
