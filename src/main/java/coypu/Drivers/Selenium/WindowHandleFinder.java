//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:14
//

package coypu.Drivers.Selenium;

import coypu.MissingWindowException;
import coypu.Options;
import CS2JNet.System.Collections.LCC.CSList;
import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.InvalidOperationException;
import CS2JNet.System.StringSupport;
import java.util.List;

public class WindowHandleFinder   
{
    private final IWebDriver webDriver = new IWebDriver();
    public WindowHandleFinder(IWebDriver webDriver) throws Exception {
        this.webDriver = webDriver;
    }

    public IEnumerable<String> findWindowHandles(String titleOrName, Options options) throws Exception {
        String currentHandle = getCurrentWindowHandle();
        List<String> matchingWindowHandles = new CSList<String>();
        try
        {
            webDriver.SwitchTo().Window(titleOrName);
            matchingWindowHandles.Add(webDriver.CurrentWindowHandle);
        }
        catch (NoSuchWindowException __dummyCatchVar0)
        {
            for (/* [UNSUPPORTED] 'var' as type is unsupported "var" */ windowHandle : webDriver.WindowHandles)
            {
                webDriver.SwitchTo().Window(windowHandle);
                if (options.getTextPrecisionExact())
                {
                    if (ExactMatch(titleOrName, windowHandle))
                        matchingWindowHandles.Add(windowHandle);
                     
                }
                else
                {
                    if (substringMatch(titleOrName))
                        matchingWindowHandles.Add(windowHandle);
                     
                } 
            }
        }

        try
        {
            webDriver.SwitchTo().Window(currentHandle);
        }
        catch (NoSuchWindowException ex)
        {
            throw new MissingWindowException("The active window was closed. Coypu should prevent this by ensuring fresh scope higher up.", ex);
        }

        return matchingWindowHandles;
    }

    private boolean substringMatch(String titleOrName) throws Exception {
        return webDriver.Title.Contains(titleOrName);
    }

    private boolean exactMatch(String titleOrName, String windowHandle) throws Exception {
        return (StringSupport.equals(windowHandle, titleOrName) || StringSupport.equals(webDriver.Title, titleOrName));
    }

    public String getCurrentWindowHandle() throws Exception {
        try
        {
            return webDriver.CurrentWindowHandle;
        }
        catch (NoSuchWindowException __dummyCatchVar1)
        {
        }
        catch (InvalidOperationException __dummyCatchVar2)
        {
        }

        return null;
    }

}


