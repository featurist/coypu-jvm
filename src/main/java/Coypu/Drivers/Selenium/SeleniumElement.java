package Coypu.Drivers.Selenium;

import Coypu.ElementFound;
import Coypu.Iterators;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

class SeleniumElement implements ElementFound {
    private final WebElement nativeElement;

    protected WebElement getNativeSeleniumElement() {
        return nativeElement;
    }

    public SeleniumElement(WebElement seleniumElement) {
        nativeElement = seleniumElement;
    }

    public String getId() {
        return getNativeSeleniumElement().getAttribute("id");
    }

    public String getText() {
        return getNativeSeleniumElement().getText();
    }

    public String getValue() {
        return getNativeSeleniumElement().getAttribute("value");
    }

    public String getName() {
        return getNativeSeleniumElement().getAttribute("name");
    }

    public String getSelectedOption() {
        WebElement option = Iterators.firstOrDefault(getNativeSeleniumElement().findElements(By.tagName("option")), new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return e.isSelected();
            }
        });
        return option == null ? null : option.getText();
    }

    public boolean getSelected() {
        return getNativeSeleniumElement().isSelected();
    }

    public Object getNative() {
        return nativeElement;
    }

    public boolean stale() {
        try {
            getNativeSeleniumElement().findElement(By.xpath("."));
            return !getNativeSeleniumElement().isDisplayed();
        } catch (StaleElementReferenceException ex) {
            return true;
        } catch (WebDriverException ex) {
            return true;
        }
    }

    public String getAttribute(String attributeName) {
        return getNativeSeleniumElement().getAttribute(attributeName);
    }
}
