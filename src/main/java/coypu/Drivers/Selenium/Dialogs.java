//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu.Drivers.Selenium;

import CS2JNet.System.StringSupport;

public class Dialogs   
{
    private final IWebDriver selenium = new IWebDriver();
    public Dialogs(IWebDriver selenium) throws Exception {
        this.selenium = selenium;
    }

    public boolean hasDialog(String withText) throws Exception {
        try
        {
            return selenium.SwitchTo() != null && selenium.SwitchTo().Alert() != null && StringSupport.equals(selenium.SwitchTo().Alert().Text, withText);
        }
        catch (NoAlertPresentException __dummyCatchVar0)
        {
            return false;
        }
    
    }

    public void acceptModalDialog() throws Exception {
        selenium.SwitchTo().Alert().Accept();
    }

    public void cancelModalDialog() throws Exception {
        selenium.SwitchTo().Alert().Dismiss();
    }

}


