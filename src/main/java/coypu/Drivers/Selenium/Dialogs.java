package coypu.Drivers.Selenium;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.remote.RemoteWebDriver;

class Dialogs {
    private final RemoteWebDriver selenium;

    public Dialogs(RemoteWebDriver selenium) {
        this.selenium = selenium;
    }

    public boolean hasDialog(String withText) {
        try {
            return selenium.switchTo() != null &&
                    selenium.switchTo().alert() != null &&
                    selenium.switchTo().alert().getText().equals(withText);
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public void acceptModalDialog() {
        selenium.switchTo().alert().accept();
    }

    public void cancelModalDialog() {
        selenium.switchTo().alert().dismiss();
    }
}
