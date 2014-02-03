//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:16
//

package coypu;

import coypu.Actions.FillIn;
import coypu.Driver;
import coypu.ElementScope;
import coypu.Options;
import coypu.Timing.TimingStrategy;

public class FillInWith   
{
    private final Driver driver;
    private final TimingStrategy timingStrategy;
    private final Options options;
    private final ElementScope element;
    public FillInWith(ElementScope element, Driver driver, TimingStrategy timingStrategy, Options options) throws Exception {
        this.element = element;
        this.driver = driver;
        this.timingStrategy = timingStrategy;
        this.options = options;
    }

    /**
    * Supply a value for the text field
    * 
    *  @param value The value to fill in
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    */
    public void with(String value) throws Exception {
        timingStrategy.Synchronise(new FillIn(driver, element, value, options));
    }

}


