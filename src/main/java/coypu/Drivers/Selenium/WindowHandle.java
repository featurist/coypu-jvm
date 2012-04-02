package coypu.Drivers.Selenium;

import coypu.ElementFound;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.remote.RemoteWebDriver;

class WindowHandle implements ElementFound {
    private final RemoteWebDriver selenium;
    private final String windowHandle;

    public WindowHandle(RemoteWebDriver selenium, String windowHandle) {
        this.selenium = selenium;
        this.windowHandle = windowHandle;
    }

    public String getId() {
        throw new UnsupportedOperationException();
    }

    public String getText() {
        String currentWindowHandle = selenium.getWindowHandle();
        try {
            return ((SearchContext) getNative()).findElement(By.cssSelector("body")).getText();
        } finally {
            selenium.switchTo().window(currentWindowHandle);
        }
    }

    public String getValue() {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public String getSelectedOption() {
        throw new UnsupportedOperationException();
    }

    public boolean getSelected() {
        throw new UnsupportedOperationException();
    }

    public Object getNative() {
        selenium.switchTo().window(windowHandle);
        return selenium;
    }

    public boolean stale() {
        return !selenium.getWindowHandles().contains(windowHandle);
    }

    public String getAttribute(String attributeName) {
        throw new UnsupportedOperationException();
    }
}
