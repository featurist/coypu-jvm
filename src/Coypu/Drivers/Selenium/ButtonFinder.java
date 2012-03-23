package Coypu.Drivers.Selenium;

import Coypu.DriverScope;
import Coypu.Iterators;
import Coypu.Drivers.XPath;
import Coypu.MissingHtmlException;
import Coypu.TimeoutException;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.Arrays;

class ButtonFinder
{
    private final ElementFinder elementFinder;
    private final TextMatcher textMatcher;
    private final XPath xPath;

    public ButtonFinder(ElementFinder elementFinder, TextMatcher textMatcher, XPath xPath)
    {
        this.elementFinder = elementFinder;
        this.textMatcher = textMatcher;
        this.xPath = xPath;
    }

    private Predicate<WebElement> isButton =
            new Predicate<WebElement>() {
                @Override
                public boolean apply(@Nullable WebElement e) {
                    return e.getTagName() == "button" || IsInputButton(e) || e.getAttribute("role") == "button";
                }
            };

    private Predicate<WebElement> textMatches(final String locator)  {
        return new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return textMatcher.TextMatches(e, locator);
            }
        };
    }

    public WebElement FindButton(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException {
        WebElement byText = FindButtonByText(locator, scope);
        if (byText != null) return byText;
        
        WebElement byIdNameOrValue = FindButtonByIdNameOrValue(locator, scope);
        if (byIdNameOrValue != null) return byIdNameOrValue;

        return Iterators.FirstOrDefault(elementFinder.FindByPartialId(locator, scope), isButton, scope);
    }

    
    private WebElement FindButtonByIdNameOrValue(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException {
        String xpathToFind = xPath.Format(".//*[@id = {0} or @name = {0} or @value = {0} or @alt = {0}]", locator);
        return Iterators.FirstOrDefault(elementFinder.Find(By.xpath(xpathToFind), scope), isButton, scope);
    }

    private WebElement FindButtonByText(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException {
        WebElement byTagName = Iterators.FirstOrDefault(elementFinder.Find(By.tagName("button"), scope),textMatches(locator), scope);
        if (byTagName != null) return byTagName;
        
        WebElement byClassName = Iterators.FirstOrDefault(elementFinder.Find(By.className("button"), scope),textMatches(locator), scope);
        if (byClassName != null) return byClassName;
        
        return Iterators.FirstOrDefault(elementFinder.Find(By.xpath(".//*[@role = 'button']"), scope),textMatches(locator), scope);
    }

    private boolean IsInputButton(WebElement e)
    {
        return e.getTagName() == "input" && Arrays.asList(FieldFinder.InputButtonTypes).contains(e.getAttribute("type"));
    }

}
