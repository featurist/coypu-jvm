//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:12
//

package coypu.Actions;

import coypu.Actions.DriverAction;
import coypu.Driver;
import coypu.ElementScope;
import coypu.Options;
import CS2JNet.System.StringSupport;

public class FillIn  extends DriverAction 
{
    private final String value;
    private final ElementScope element;
    public FillIn(Driver driver, ElementScope element, String value, Options options) throws Exception {
        super(driver, options);
        this.element = element;
        this.value = value;
    }

    private void bringIntoFocus() throws Exception {
        Driver.Click(element);
    }

    public void set() throws Exception {
        Driver.Set(element, value);
    }

    public void focus() throws Exception {
        if (!StringSupport.equals(element["type"], "file"))
            bringIntoFocus();
         
    }

    public void act() throws Exception {
        focus();
        set();
    }

}


