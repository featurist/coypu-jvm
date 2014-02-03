//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:14
//

package coypu.Drivers.Selenium;

import coypu.ElementFound;
import coypu.Options;
import CS2JNet.System.NotImplementedException;

public class SeleniumWindow   implements ElementFound
{
    private final IWebDriver webDriver = new IWebDriver();
    private final String windowHandle;
    public SeleniumWindow(IWebDriver webDriver, String windowHandle) throws Exception {
        this.webDriver = webDriver;
        this.windowHandle = windowHandle;
    }

    public String getId() throws Exception {
        throw new System.NotSupportedException();
    }

    public String getText() throws Exception {
        return RetainingCurrentWindow(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "() => {
            return ((ISearchContext)getNative()).FindElement(By.CssSelector("body")).Text;
        }" */);
    }

    public String getInnerHTML() throws Exception {
        return RetainingCurrentWindow(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "() => {
            return ((ISearchContext)getNative()).FindElement(By.XPath("./*")).GetAttribute("innerHTML");
        }" */);
    }

    public String getTitle() throws Exception {
        return RetainingCurrentWindow(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "() => {
            return webDriver.Title;
        }" */);
    }

    public String getOuterHTML() throws Exception {
        return RetainingCurrentWindow(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "() => {
            return ((ISearchContext)getNative()).FindElement(By.XPath("./*")).GetAttribute("outerHTML");
        }" */);
    }

    private <T>T retainingCurrentWindow(Func<T> function) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ currentWindowHandle = webDriver.CurrentWindowHandle;
        try
        {
            return function();
        }
        finally
        {
            SwitchTo(currentWindowHandle);
        }
    }

    public String getValue() throws Exception {
        throw new System.NotSupportedException();
    }

    public String getName() throws Exception {
        throw new System.NotSupportedException();
    }

    public String getSelectedOption() throws Exception {
        throw new System.NotSupportedException();
    }

    public boolean getSelected() throws Exception {
        throw new System.NotSupportedException();
    }

    public Object getNative() throws Exception {
        switchTo(windowHandle);
        return webDriver;
    }

    private void switchTo(String windowName) throws Exception {
        webDriver.SwitchTo().Window(windowName);
    }

    public boolean stale(Options options) throws Exception {
        return !webDriver.WindowHandles.Contains(windowHandle);
    }

    public String get___idx(String attributeName) throws Exception {
        throw new NotImplementedException();
    }

}


