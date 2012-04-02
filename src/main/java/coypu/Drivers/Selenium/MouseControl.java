package coypu.Drivers.Selenium;

import coypu.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

class MouseControl {
    private final WebDriver selenium;

    public MouseControl(RemoteWebDriver selenium) {
        this.selenium = selenium;
    }

    public void hover(Element element) {
        Actions sequenceBuilder = new Actions(selenium);
        Actions actionSequenceBuilder = sequenceBuilder.moveToElement((WebElement) element.getNative());
        Action action = actionSequenceBuilder.build();
        action.perform();
    }
}
