package coypu.Drivers.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class SeleniumFrame extends SeleniumElement {
    private final WebDriver selenium;

    public SeleniumFrame(WebElement seleniumElement, WebDriver selenium) {
        super(seleniumElement);
        this.selenium = selenium;
    }

    public Object getNative() {
        selenium.switchTo().frame(getNativeSeleniumElement());
        return selenium;
    }
}
