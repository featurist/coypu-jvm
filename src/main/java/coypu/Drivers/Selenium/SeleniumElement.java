//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:14
//

package coypu.Drivers.Selenium;

import coypu.ElementFound;
import coypu.Options;
import CS2JNet.System.InvalidOperationException;

public class SeleniumElement   implements ElementFound
{
    protected final IWebElement native = new IWebElement();
    protected final IWebDriver selenium = new IWebDriver();
    public SeleniumElement(IWebElement seleniumElement, IWebDriver selenium) throws Exception {
        native = seleniumElement;
        this.selenium = selenium;
    }

    public String getId() throws Exception {
        return native.GetAttribute("id");
    }

    public String getText() throws Exception {
        return native.Text;
    }

    public String getValue() throws Exception {
        return native.GetAttribute("value");
    }

    public String getName() throws Exception {
        return native.GetAttribute("name");
    }

    public String getOuterHTML() throws Exception {
        return native.GetAttribute("outerHTML");
    }

    public String getInnerHTML() throws Exception {
        return native.GetAttribute("innerHTML");
    }

    public String getTitle() throws Exception {
        return native.GetAttribute("title");
    }

    public String getSelectedOption() throws Exception {
        return native.FindElements(By.TagName("option")).Where(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(e) => {
            return e.Selected;
        }" */).Select(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(e) => {
            return e.Text;
        }" */).FirstOrDefault();
    }

    public boolean getSelected() throws Exception {
        return native.Selected;
    }

    public Object getNative() throws Exception {
        return native;
    }

    public boolean stale(Options options) throws Exception {
        try
        {
            native.FindElement(By.XPath("."));
            return !options.getConsiderInvisibleElements() && !native.Displayed;
        }
        catch (InvalidOperationException __dummyCatchVar0)
        {
            return true;
        }
        catch (NoSuchWindowException __dummyCatchVar1)
        {
            return true;
        }
        catch (StaleElementReferenceException __dummyCatchVar2)
        {
            return true;
        }
    
    }

    public String get___idx(String attributeName) throws Exception {
        return native.GetAttribute(attributeName);
    }

}


