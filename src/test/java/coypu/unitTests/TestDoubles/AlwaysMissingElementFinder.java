package coypu.unitTests.TestDoubles;

import coypu.ElementFound;
import coypu.Finders.FindByLocatorElementFinder;
import coypu.MissingHtmlException;

public class AlwaysMissingElementFinder extends FindByLocatorElementFinder
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
