//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu.Drivers.Selenium;

import coypu.Element;

public class MouseControl   
{
    private final IWebDriver selenium = new IWebDriver();
    public MouseControl(IWebDriver selenium) throws Exception {
        this.selenium = selenium;
    }

    public void hover(Element element) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ sequenceBuilder = new OpenQA.Selenium.Interactions.Actions(selenium);
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ actionSequenceBuilder = sequenceBuilder.MoveToElement((IWebElement)element.getNative());
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ action = actionSequenceBuilder.Build();
        action.Perform();
    }

}


