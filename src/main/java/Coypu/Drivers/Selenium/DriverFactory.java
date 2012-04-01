package Coypu.Drivers.Selenium;

import Coypu.Drivers.Browser;
import Coypu.Drivers.BrowserNotSupportedException;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static Coypu.Drivers.Browser.*;

class DriverFactory {
    public RemoteWebDriver newRemoteWebDriver(Browser browser) throws BrowserNotSupportedException {
        if (browser == Firefox)
            return new FirefoxDriver();
        if (browser == InternetExplorer) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            return new InternetExplorerDriver(desiredCapabilities);
        }
        if (browser == Chrome)
            return new ChromeDriver();
        if (browser == Android)
            return new AndroidDriver();
        if (browser == HtmlUnit)
            return new RemoteWebDriver(DesiredCapabilities.htmlUnit());
        if (browser == HtmlUnitWithJavaScript) {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.htmlUnit();
            desiredCapabilities.setJavascriptEnabled(true);
            return new RemoteWebDriver(desiredCapabilities);
        }
        throw new BrowserNotSupportedException(browser, getClass());
    }
}
