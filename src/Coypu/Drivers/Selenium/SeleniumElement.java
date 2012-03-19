package Coypu.Drivers.Selenium;

import Coypu.ElementFound;

class SeleniumElement extends ElementFound
{
    private final IWebElement nativeElement;

    protected IWebElement NativeSeleniumElement()
    {
        return nativeElement;
    }

    public SeleniumElement(IWebElement seleniumElement)
    {
        nativeElement = seleniumElement;
    }

    public String Id()
    {
        return NativeSeleniumElement.GetAttribute("id");
    }

    public String Text()
    {
        return NativeSeleniumElement.Text;
    }

    public String Value()
    {
        return NativeSeleniumElement.GetAttribute("value");
    }

    public String Name()
    {
        return NativeSeleniumElement.GetAttribute("name");
    }

    public String SelectedOption()
    {
        return NativeSeleniumElement.FindElements(By.TagName("option"))
            .Where(e => e.Selected)
            .Select(e => e.Text)
            .FirstOrDefault();
    }

    public boolean Selected()
    {
        return NativeSeleniumElement.Selected;
    }

    public Object Native()
    {
        return nativeElement;
    }

    public boolean Stale()
    {
        try
        {
            NativeSeleniumElement.FindElement(By.XPath("."));
            return !NativeSeleniumElement.Displayed;
        }
        catch(InvalidOperationException)
        {
            return true;
        }
        catch (StaleElementReferenceException)
        {
            return true;
        }
    }

    public String Attribute(String attributeName)
    {
        return NativeSeleniumElement.GetAttribute(attributeName);
    }
}