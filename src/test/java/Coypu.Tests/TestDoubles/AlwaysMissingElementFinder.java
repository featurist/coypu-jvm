package Coypu.Tests.TestDoubles;

import Coypu.ElementFound;
import Coypu.Finders.ElementFinder;
import Coypu.MissingHtmlException;

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
