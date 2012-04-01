package Coypu.AcceptanceTests;

import Coypu.Drivers.Browser;
import Coypu.Drivers.Selenium.SeleniumWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CustomFirefoxProfileSeleniumWebDriver extends SeleniumWebDriver
{
    public CustomFirefoxProfileSeleniumWebDriver(Browser browser)
    {
        super(customProfile());
    }

    private static RemoteWebDriver customProfile()
    {
        FirefoxProfile yourCustomProfile = new FirefoxProfile();
        return new FirefoxDriver(yourCustomProfile);
    }
}
