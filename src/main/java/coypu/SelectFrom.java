//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu;

import coypu.Actions.Select;
import coypu.Driver;
import coypu.DriverScope;
import coypu.Finders.DisambiguationStrategy;
import coypu.Options;
import coypu.Timing.TimingStrategy;

public class SelectFrom   
{
    private final String option;
    private final Driver driver;
    private final TimingStrategy timingStrategy;
    private final DriverScope scope;
    private final Options options;
    private final DisambiguationStrategy disambiguationStrategy;
    public SelectFrom(String option, Driver driver, TimingStrategy timingStrategy, DriverScope scope, Options options, DisambiguationStrategy disambiguationStrategy) throws Exception {
        this.option = option;
        this.driver = driver;
        this.timingStrategy = timingStrategy;
        this.scope = scope;
        this.options = options;
        this.disambiguationStrategy = disambiguationStrategy;
    }

    /**
    * Find the first matching select to appear within the SessionConfiguration.Timeout from which to select this option.
    * 
    *  @param locator The text of the associated label element, the id or name
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    */
    public void from(String locator) throws Exception {
        timingStrategy.Synchronise(new Select(driver, scope, locator, option, options, disambiguationStrategy));
    }

}


