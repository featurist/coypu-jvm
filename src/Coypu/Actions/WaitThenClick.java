package Coypu.Actions;

import Coypu.ElementFound;
import Coypu.Finders.ElementFinder;
import Coypu.Options;
import Coypu.Robustness.Waiter;
import Coypu.Driver;

public class WaitThenClick extends DriverAction
{
    private Options options;
    private Waiter waiter;
    private ElementFinder elementFinder;

    WaitThenClick(Driver driver, Options options, Waiter waiter, ElementFinder elementFinder)
    {
        super(driver, options);

        this.options = options;
        this.waiter = waiter;
        this.elementFinder = elementFinder;
    }

    public void Act()
    {
        ElementFound element = elementFinder.Find();
        waiter.Wait(options.WaitBeforeClick);
        Driver.Click(element);
    }
}
