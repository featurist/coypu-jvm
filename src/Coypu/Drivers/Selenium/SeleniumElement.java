package Coypu.Drivers.Selenium;

import Coypu.ElementFound;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

class SeleniumElement implements ElementFound
{
    private final WebElement nativeElement;

    protected WebElement NativeSeleniumElement()
    {
        return nativeElement;
    }

    public SeleniumElement(WebElement seleniumElement)
    {
        nativeElement = seleniumElement;
    }

    public String Id()
    {
        return NativeSeleniumElement().getAttribute("id");
    }

    public String Text()
    {
        return NativeSeleniumElement().getText();
    }

    public String Value()
    {
        return NativeSeleniumElement().getAttribute("value");
    }

    public String Name()
    {
        return NativeSeleniumElement().getAttribute("name");
    }

    public String SelectedOption()
    {
        return NativeSeleniumElement().findElements(By.tagName("option"))
            .Where(e => e.Selected)
            .Select(e => e.Text)
            .FirstOrDefault();
    }

    public boolean Selected()
    {
        return NativeSeleniumElement().isSelected();
    }

    public Object Native()
    {
        return nativeElement;
    }

    public boolean Stale()
    {
        try
        {
            NativeSeleniumElement().findElement(By.xpath("."));
            return !NativeSeleniumElement().isDisplayed();
        }
        catch (StaleElementReferenceException ex)
        {
            return true;
        }
    }

    public String Attribute(String attributeName)
    {
        return NativeSeleniumElement().getAttribute(attributeName);
    }
}