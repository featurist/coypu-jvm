package Coypu.Drivers.Selenium;

import Coypu.Element;

class MouseControl
{
    private final IWebDriver selenium;

    public MouseControl(IWebDriver selenium)
    {
        this.selenium = selenium;
    }

    public void Hover(Element element)
    {
        SequenceBuilder sequenceBuilder = new OpenQA.Selenium.Interactions.Actions(selenium);
        ActionSequenceBuilder actionSequenceBuilder = sequenceBuilder.MoveToElement((IWebElement) element.Native());
        Action action = actionSequenceBuilder.Build();
        action.Perform();
    }
}
