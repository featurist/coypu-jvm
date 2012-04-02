package coypu.Drivers.Selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

class SeleniumFrame extends SeleniumElement {
    private final RemoteWebDriver selenium;

    public SeleniumFrame(WebElement seleniumElement, RemoteWebDriver selenium) {
        super(seleniumElement);
        this.selenium = selenium;
    }

    public Object getNative() {
        selenium.switchTo().frame(getNativeSeleniumElement());
        return selenium;
    }
}
