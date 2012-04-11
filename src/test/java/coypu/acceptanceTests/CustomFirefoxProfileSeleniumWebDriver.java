package coypu.acceptanceTests;

import coypu.Drivers.Browser;
import coypu.Drivers.Selenium.SeleniumWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class CustomFirefoxProfileSeleniumWebDriver extends SeleniumWebDriver
{
    public CustomFirefoxProfileSeleniumWebDriver(Browser browser)
    {
        super(customProfile());
    }

    private static WebDriver customProfile()
    {
        FirefoxProfile yourCustomProfile = new FirefoxProfile();
        return new FirefoxDriver(yourCustomProfile);
    }
}
