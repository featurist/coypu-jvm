//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:12
//

package coypu.Actions;

import coypu.Options;

public class LambdaBrowserAction  extends BrowserAction 
{
    private final Action action = new Action();
    public LambdaBrowserAction(Action action, Options options) throws Exception {
        super(options);
        this.action = action;
    }

    public void act() throws Exception {
        action();
    }

}


