//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:12
//

package coypu.Actions;

import coypu.Driver;
import coypu.Finders.DisambiguationStrategy;
import coypu.Finders.ElementFinder;
import coypu.Options;
import coypu.Timing.Waiter;
import CS2JNet.System.TimeSpan;

public class WaitThenClick  extends DriverAction 
{
    private final Waiter waiter;
    private final ElementFinder elementFinder;
    private final DisambiguationStrategy disambiguationStrategy;
    private final TimeSpan waitBeforeClick;
    public WaitThenClick(Driver driver, Options options, Waiter waiter, ElementFinder elementFinder, DisambiguationStrategy disambiguationStrategy) throws Exception {
        super(driver, options);
        waitBeforeClick = options.getWaitBeforeClick();
        this.waiter = waiter;
        this.elementFinder = elementFinder;
        this.disambiguationStrategy = disambiguationStrategy;
    }

    public void act() throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ element = disambiguationStrategy.resolveQuery(elementFinder);
        waiter.Wait(waitBeforeClick);
        Driver.Click(element);
    }

}


