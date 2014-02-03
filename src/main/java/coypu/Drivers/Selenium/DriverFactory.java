//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu.Drivers.Selenium;

import coypu.Drivers.Browser;
import coypu.Drivers.BrowserNotSupportedException;

public class DriverFactory   
{
    public IWebDriver newWebDriver(Browser browser) throws Exception {
        if (browser == Browser.Firefox)
            return new FirefoxDriver();
         
        if (browser == Browser.InternetExplorer)
        {
            /* [UNSUPPORTED] 'var' as type is unsupported "var" */ options = new InternetExplorerOptions();
            return new InternetExplorerDriver(options);
        }
         
        if (browser == Browser.Chrome)
            return new ChromeDriver();
         
        if (browser == Browser.Android)
            return new AndroidDriver();
         
        if (browser == Browser.HtmlUnit)
            return new RemoteWebDriver(DesiredCapabilities.HtmlUnit());
         
        if (browser == Browser.HtmlUnitWithJavaScript)
        {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.HtmlUnit();
            desiredCapabilities.IsJavaScriptEnabled = true;
            return new RemoteWebDriver(desiredCapabilities);
        }
         
        if (browser == Browser.PhantomJS)
            return new PhantomJSDriver();
         
        return browserNotSupported(browser, null);
    }

    private IWebDriver browserNotSupported(Browser browser, Exception inner) throws Exception {
        throw new BrowserNotSupportedException(browser,getClass(),inner);
    }

}


