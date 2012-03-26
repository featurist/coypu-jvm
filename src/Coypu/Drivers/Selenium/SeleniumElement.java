package Coypu.Drivers.Selenium;

import Coypu.ElementFound;
import Coypu.Iterators;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

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
        WebElement option = Iterators.FirstOrDefault(NativeSeleniumElement().findElements(By.tagName("option")), new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return e.isSelected();
            }
        });
        return option == null ? null : option.getText();
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
        catch (WebDriverException ex)
        {
            return true;
        }
    }

    public String Attribute(String attributeName)
    {
        return NativeSeleniumElement().getAttribute(attributeName);
    }
}
