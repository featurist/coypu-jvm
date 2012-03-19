package Coypu.Drivers.Selenium;
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
            return selenium.SwitchTo() != null &&
                   selenium.SwitchTo().Alert() != null &&
                   selenium.SwitchTo().Alert().Text == withText;
        }
        catch (NoAlertPresentException ex)
        {
            return false;
        }
    }

    public void AcceptModalDialog()
    {
        selenium.SwitchTo().Alert().Accept();
    }

    public void CancelModalDialog()
    {
        selenium.SwitchTo().Alert().Dismiss();
    }
}