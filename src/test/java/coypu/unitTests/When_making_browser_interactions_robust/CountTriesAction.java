package coypu.unitTests.When_making_browser_interactions_robust;

import coypu.Actions.BrowserAction;
import coypu.Options;

public class CountTriesAction extends BrowserAction
{
    private int tries;

    public CountTriesAction(Options options){
        super(options);
    }

    public void act()
    {
        tries++;
    }

    public int getTries() {
        return tries;
    }
}
