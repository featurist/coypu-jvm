//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:14
//

package coypu.Drivers.Selenium;

import CS2JNet.System.StringSupport;
import java.util.regex.Pattern;

public class TextMatcher   
{
    public boolean textMatches(IWebElement e, String locator) throws Exception {
        return StringSupport.equals(e.Text.Trim(), StringSupport.Trim(locator));
    }

    public boolean textMatches(IWebElement e, Pattern pattern) throws Exception {
        return e.Text != null && pattern.IsMatch(e.Text.Trim());
    }

}


