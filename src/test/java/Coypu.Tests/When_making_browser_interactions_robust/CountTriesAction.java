package Coypu.Tests.When_making_browser_interactions_robust;

import Coypu.Actions.BrowserAction;
import Coypu.Options;

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
