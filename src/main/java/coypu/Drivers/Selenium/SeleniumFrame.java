//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:14
//

package coypu.Drivers.Selenium;

import coypu.Drivers.Selenium.SeleniumElement;

public class SeleniumFrame  extends SeleniumElement 
{
    public SeleniumFrame(IWebElement seleniumElement, IWebDriver selenium) throws Exception {
        super(seleniumElement, selenium);
    }

    public String getText() throws Exception {
        selenium.SwitchTo().Frame(native);
        return selenium.FindElement(By.CssSelector("body")).Text;
    }

    public String getOuterHTML() throws Exception {
        selenium.SwitchTo().Frame(native);
        return selenium.FindElement(By.CssSelector("body")).GetAttribute("outerHTML");
    }

    public String getInnerHTML() throws Exception {
        selenium.SwitchTo().Frame(native);
        return selenium.FindElement(By.CssSelector("body")).GetAttribute("innerHTML");
    }

    public Object getNative() throws Exception {
        selenium.SwitchTo().Frame(native);
        return selenium;
    }

}


