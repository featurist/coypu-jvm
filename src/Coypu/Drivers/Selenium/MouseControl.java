package Coypu.Drivers.Selenium;

import Coypu.Element;
import org.openqa.selenium.remote.RemoteWebDriver;

class MouseControl
{
    private final IWebDriver selenium;

    public MouseControl(RemoteWebDriver selenium)
    {
        this.selenium = selenium;
    }

    public void Hover(Element element)
    {
        SequenceBuilder sequenceBuilder = new OpenQA.Selenium.Interactions.Actions(selenium);
        ActionSequenceBuilder actionSequenceBuilder = sequenceBuilder.MoveToElement((WebElement) element.Native());
        Action action = actionSequenceBuilder.Build();
        action.Perform();
    }
}
