package Coypu.Drivers.Selenium;

import Coypu.DriverScope;
import Coypu.Drivers.XPath;

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

    public IWebElement FindButton(String locator, DriverScope scope)
    {
        return FindButtonByText(locator, scope) ??
               FindButtonByIdNameOrValue(locator, scope) ??
               elementFinder.FindByPartialId(locator, scope).FirstOrDefault(IsButton);
    }

    private IWebElement FindButtonByIdNameOrValue(String locator, DriverScope scope)
    {
        String xpathToFind = xPath.Format(".//*[@id = {0} or @name = {0} or @value = {0} or @alt = {0}]", locator);
        return elementFinder.Find(By.XPath(xpathToFind),scope).FirstOrDefault(IsButton);
    }

    private IWebElement FindButtonByText(String locator, DriverScope scope)
    {
        return
            elementFinder.Find(By.TagName("button"), scope).FirstOrDefault(e => textMatcher.TextMatches(e, locator)) ??
            elementFinder.Find(By.ClassName("button"), scope).FirstOrDefault(e => textMatcher.TextMatches(e, locator)) ??
            elementFinder.Find(By.XPath(".//*[@role = 'button']"), scope).FirstOrDefault(e => textMatcher.TextMatches(e, locator));
    }

    private boolean IsButton(IWebElement e)
    {
        return e.TagName == "button" || IsInputButton(e) || e.getAttribute("role") == "button";
    }

    private boolean IsInputButton(IWebElement e)
    {
        return e.TagName == "input" && FieldFinder.InputButtonTypes.Contains(e.getAttribute("type"));
    }
}