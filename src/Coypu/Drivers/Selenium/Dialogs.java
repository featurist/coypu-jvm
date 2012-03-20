package Coypu.Drivers.Selenium;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.remote.RemoteWebDriver;

class Dialogs
{
    private final RemoteWebDriver selenium;

    public Dialogs(RemoteWebDriver selenium)
    {
        this.selenium = selenium;
    }

    public boolean HasDialog(String withText)
    {
        try
        {
            return selenium.switchTo() != null &&
                   selenium.switchTo().alert() != null &&
                   selenium.switchTo().alert().getText() == withText;
        }
        catch (NoAlertPresentException ex)
        {
            return false;
        }
    }

    public void AcceptModalDialog()
    {
        selenium.switchTo().alert().accept();
    }

    public void CancelModalDialog()
    {
        selenium.switchTo().alert().dismiss();
    }
}