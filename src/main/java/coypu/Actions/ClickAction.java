//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:12
//

package coypu.Actions;

import coypu.Actions.DriverAction;
import coypu.Driver;
import coypu.ElementScope;
import coypu.Options;

public class ClickAction  extends DriverAction 
{
    private final ElementScope elementScope;
    public ClickAction(ElementScope elementScope, Driver driver, Options options) throws Exception {
        super(driver, options);
        this.elementScope = elementScope;
    }

    public void act() throws Exception {
        Driver.Click(elementScope);
    }

}


