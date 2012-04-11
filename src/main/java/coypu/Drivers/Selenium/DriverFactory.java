package coypu.Drivers.Selenium;

import coypu.Drivers.Browser;
import coypu.Drivers.BrowserNotSupportedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.iphone.IPhoneDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static coypu.Drivers.Browser.*;

class DriverFactory {
    public WebDriver newRemoteWebDriver(Browser browser) throws BrowserNotSupportedException {
        if (browser == Firefox)
            return new FirefoxDriver();
        if (browser == InternetExplorer) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            return new InternetExplorerDriver(desiredCapabilities);
        }
        if (browser == Chrome)
            return new ChromeDriver();
        if (browser == IPhone)
            try {
                return new IPhoneDriver();
            } catch (Exception e) {
                browserNotSupported(browser,e);
            }
        if (browser == Android)
            return new AndroidDriver();
        if (browser == HtmlUnit)
            return new RemoteWebDriver(DesiredCapabilities.htmlUnit());
        if (browser == HtmlUnitWithJavaScript) {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.htmlUnit();
            desiredCapabilities.setJavascriptEnabled(true);
            return new RemoteWebDriver(desiredCapabilities);
        }
        return browserNotSupported(browser,null);
    }

    private WebDriver browserNotSupported(Browser browser, Throwable inner) throws BrowserNotSupportedException {
        throw new BrowserNotSupportedException(browser, getClass(),inner);
    }
}
