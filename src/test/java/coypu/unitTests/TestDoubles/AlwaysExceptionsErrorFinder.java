package coypu.unitTests.TestDoubles;

import coypu.ElementFound;
import coypu.Finders.FindByLocatorElementFinder;
import coypu.unitTests.TestException;

public class AlwaysExceptionsErrorFinder extends FindByLocatorElementFinder
{
    public AlwaysExceptionsErrorFinder()
    {
        super(null, null, null);
    }

    public ElementFound find()
    {
        throw new TestException("I always fail");
    }
}

