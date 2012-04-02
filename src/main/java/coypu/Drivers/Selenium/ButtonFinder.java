package coypu.Drivers.Selenium;

import coypu.DriverScope;
import coypu.Drivers.XPath;
import coypu.Iterators;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.Arrays;

class ButtonFinder {
    private final ElementFinder elementFinder;
    private final TextMatcher textMatcher;
    private final XPath xPath;

    public ButtonFinder(ElementFinder elementFinder, TextMatcher textMatcher, XPath xPath) {
        this.elementFinder = elementFinder;
        this.textMatcher = textMatcher;
        this.xPath = xPath;
    }

    private Predicate<WebElement> isButton =
            new Predicate<WebElement>() {
                @Override
                public boolean apply(@Nullable WebElement e) {
                    return e.getTagName().equals("button") ||
                            isInputButton(e) ||
                            (e.getAttribute("role") != null && e.getAttribute("role").equals("button"));
                }
            };

    private Predicate<WebElement> textMatches(final String locator) {
        return new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return textMatcher.textMatches(e, locator);
            }
        };
    }

    public WebElement findButton(String locator, DriverScope scope) {
        WebElement byText = findButtonByText(locator, scope);
        if (byText != null) return byText;

        return findButtonByIdNameOrValue(locator, scope);
    }

    private WebElement findButtonByIdNameOrValue(String locator, DriverScope scope) {
        String xpathToFind = xPath.format(".//*[@id = %1$s or @name = %1$s or @value = %1$s or @alt = %1$s]", locator);
        return Iterators.firstOrDefault(elementFinder.find(By.xpath(xpathToFind), scope), isButton, scope);
    }

    private WebElement findButtonByText(String locator, DriverScope scope) {
        WebElement byTagName = Iterators.firstOrDefault(elementFinder.find(By.tagName("button"), scope), textMatches(locator), scope);
        if (byTagName != null) return byTagName;

        WebElement byClassName = Iterators.firstOrDefault(elementFinder.find(By.className("button"), scope), textMatches(locator), scope);
        if (byClassName != null) return byClassName;

        return Iterators.firstOrDefault(elementFinder.find(By.xpath(".//*[@role = 'button']"), scope), textMatches(locator), scope);
    }

    private boolean isInputButton(WebElement e) {
        return e.getTagName().equals("input") && Arrays.asList(FieldFinder.InputButtonTypes).contains(e.getAttribute("type"));
    }

}
