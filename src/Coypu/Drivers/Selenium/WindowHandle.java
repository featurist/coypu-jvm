package Coypu.Drivers.Selenium;

import Coypu.ElementFound;
import org.apache.commons.lang.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.remote.RemoteWebDriver;

class WindowHandle implements ElementFound
{
    private final RemoteWebDriver selenium;
    private final String windowHandle;

    public WindowHandle(RemoteWebDriver selenium, String windowHandle)
    {
        this.selenium = selenium;
        this.windowHandle = windowHandle;
    }

    public String Id()
    {
        throw new UnsupportedOperationException();
    }

    public String Text()
    {
            String currentWindowHandle = selenium.getWindowHandle();
            try
            {
                return ((SearchContext)Native()).findElement(By.cssSelector("body")).getText();
            }
            finally
            {
                selenium.switchTo().window(currentWindowHandle);
            }
    }

    public String Value()
    {
        throw new UnsupportedOperationException();
    }

    public String Name()
    {
        throw new UnsupportedOperationException();
    }

    public String SelectedOption()
    {
        throw new UnsupportedOperationException();
    }

    public boolean Selected()
    {
        throw new UnsupportedOperationException();
    }

    public Object Native()
    {
        selenium.switchTo().window(windowHandle);
        return selenium;
    }

    public boolean Stale()
    {
        return !selenium.getWindowHandles().contains(windowHandle);
    }

    public String Attribute(String attributeName)
    {
        throw new UnsupportedOperationException();
    }
}
