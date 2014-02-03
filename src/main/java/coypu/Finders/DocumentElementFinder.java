//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:16
//

package coypu.Finders;

import coypu.Driver;
import coypu.ElementFound;
import coypu.Options;
import CS2JNet.System.Collections.LCC.IEnumerable;

public class DocumentElementFinder  extends ElementFinder 
{
    public DocumentElementFinder(Driver driver, Options options) throws Exception {
        super(driver, "Window", null, options);
    }

    public boolean getSupportsSubstringTextMatching() throws Exception {
        return false;
    }

    public IEnumerable<ElementFound> find(Options options) throws Exception {
        return ;
    }

    public String getQueryDescription() throws Exception {
        return "Document Element";
    }

}


