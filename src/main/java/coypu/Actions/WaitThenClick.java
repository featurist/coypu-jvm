package coypu.Actions;

import coypu.*;
import coypu.Finders.ElementFinder;
import coypu.Robustness.Waiter;

public class WaitThenClick extends DriverAction {
    private Options options;
    private Waiter waiter;
    private ElementFinder elementFinder;

    public WaitThenClick(Driver driver, Options options, Waiter waiter, ElementFinder elementFinder) {
        super(driver, options);

        this.options = options;
        this.waiter = waiter;
        this.elementFinder = elementFinder;
    }

    public void act() {
        ElementFound element = elementFinder.find();
        waiter.wait(options.WaitBeforeClick);
        Driver.click(element);
    }
}
