package Coypu.Drivers.Selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

class SeleniumFrame extends SeleniumElement
{
    private final RemoteWebDriver selenium;

    public SeleniumFrame(WebElement seleniumElement, RemoteWebDriver selenium)
    {
        super(seleniumElement);
        this.selenium = selenium;
    }

    public Object Native()
    {
        selenium.switchTo().frame(NativeSeleniumElement());
        return selenium;
    }
}
