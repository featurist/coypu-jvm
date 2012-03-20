package Coypu.Drivers.Selenium;

import Coypu.DriverScope;
import Coypu.Drivers.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    public WebElement FindButton(String locator, DriverScope scope)
    {
        return FindButtonByText(locator, scope) ??
               FindButtonByIdNameOrValue(locator, scope) ??
               elementFinder.FindByPartialId(locator, scope).FirstOrDefault(IsButton);
    }

    private WebElement FindButtonByIdNameOrValue(String locator, DriverScope scope)
    {
        String xpathToFind = xPath.Format(".//*[@id = {0} or @name = {0} or @value = {0} or @alt = {0}]", locator);
        return elementFinder.Find(By.xpath(xpathToFind),scope).FirstOrDefault(IsButton);
    }

    private WebElement FindButtonByText(String locator, DriverScope scope)
    {
        return
            elementFinder.Find(By.tagName("button"), scope).FirstOrDefault(e => textMatcher.TextMatches(e, locator)) ??
            elementFinder.Find(By.className("button"), scope).FirstOrDefault(e => textMatcher.TextMatches(e, locator)) ??
            elementFinder.Find(By.xpath(".//*[@role = 'button']"), scope).FirstOrDefault(e => textMatcher.TextMatches(e, locator));
    }

    private boolean IsButton(WebElement e)
    {
        return e.TagName == "button" || IsInputButton(e) || e.getAttribute("role") == "button";
    }

    private boolean IsInputButton(WebElement e)
    {
        return e.TagName == "input" && FieldFinder.InputButtonTypes.Contains(e.getAttribute("type"));
    }
}