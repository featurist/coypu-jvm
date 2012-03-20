package Coypu.Drivers.Selenium;

import Coypu.ElementFound;
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

    public String Id
    {
        get { throw new System.NotSupportedException(); }
    }

    public String Text
    {
        get
        {
            var currentWindowHandle = selenium.CurrentWindowHandle;
            try
            {
                return ((ISearchContext)Native).FindElement(By.CssSelector("body")).Text;
            }
            finally
            {
                selenium.SwitchTo().Window(currentWindowHandle);
            }
        }
    }

    public String Value
    {
        get { throw new System.NotSupportedException(); }
    }

    public String Name
    {
        get { throw new System.NotSupportedException(); }
    }

    public String SelectedOption
    {
        get { throw new System.NotSupportedException(); }
    }

    public boolean Selected
    {
        get { throw new System.NotSupportedException(); }
    }

    public object Native
    {
        get
        {
            selenium.SwitchTo().Window(windowHandle);
            return selenium;
        }
    }

    public boolean Stale
    {
        get
        {
            return !selenium.WindowHandles.Contains(windowHandle);
        }
    }

    public String this[String attributeName]
    {
        get { throw new System.NotImplementedException(); }
    }
}
