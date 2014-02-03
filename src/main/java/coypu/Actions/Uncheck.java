//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:12
//

package coypu.Actions;

import coypu.Actions.DriverAction;
import coypu.Driver;
import coypu.ElementScope;
import coypu.Options;

public class Uncheck  extends DriverAction 
{
    private final ElementScope element;
    public Uncheck(Driver driver, ElementScope element, Options options) throws Exception {
        super(driver, options);
        this.element = element;
    }

    public void act() throws Exception {
        Driver.Uncheck(element);
    }

}


