//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:12
//

package coypu.Actions;

import coypu.Actions.DriverAction;
import coypu.Driver;
import coypu.DriverScope;
import coypu.Finders.DisambiguationStrategy;
import coypu.Finders.OptionFinder;
import coypu.Finders.SelectFinder;
import coypu.Options;
import coypu.SnapshotElementScope;

public class Select  extends DriverAction 
{
    private final DriverScope scope;
    private final String locator;
    private final String optionToSelect;
    private final Options options;
    private final DisambiguationStrategy disambiguationStrategy;
    public Select(Driver driver, DriverScope scope, String locator, String optionToSelect, Options options, DisambiguationStrategy disambiguationStrategy) throws Exception {
        super(driver, options);
        this.scope = scope;
        this.locator = locator;
        this.optionToSelect = optionToSelect;
        this.options = options;
        this.disambiguationStrategy = disambiguationStrategy;
    }

    public void act() throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ select = disambiguationStrategy.resolveQuery(new SelectFinder(Driver,locator,scope,options));
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ option = disambiguationStrategy.resolveQuery(new OptionFinder(Driver, optionToSelect, new SnapshotElementScope(select, scope, options), options));
        Driver.Click(option);
    }

}


