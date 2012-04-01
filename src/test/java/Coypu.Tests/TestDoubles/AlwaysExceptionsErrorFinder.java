package Coypu.Tests.TestDoubles;

import Coypu.ElementFound;
import Coypu.Finders.ElementFinder;
import Coypu.Tests.TestException;

public class AlwaysExceptionsErrorFinder extends ElementFinder
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

