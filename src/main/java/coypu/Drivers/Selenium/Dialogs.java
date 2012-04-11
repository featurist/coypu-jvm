package coypu.Drivers.Selenium;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

class Dialogs {
    private final WebDriver selenium;

    public Dialogs(WebDriver selenium) {
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
