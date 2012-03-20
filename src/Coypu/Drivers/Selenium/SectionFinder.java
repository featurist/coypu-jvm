package Coypu.Drivers.Selenium;

import Coypu.DriverScope;
import Coypu.StringJoiner;

class SectionFinder
{
    private final ElementFinder elementFinder;
    private final TextMatcher textMatcher;

    final String[] headerTags = { "h1", "h2", "h3", "h4", "h5", "h6" };

    public SectionFinder(ElementFinder elementFinder, TextMatcher textMatcher)
    {
        this.elementFinder = elementFinder;
        this.textMatcher = textMatcher;
    }

    public WebElement FindSection(String locator, DriverScope scope)
    {
        return FindSectionByHeaderText(locator,scope) ??
               elementFinder.Find(By.Id(locator),scope).FirstDisplayedOrDefault(IsSection);
    }

    private WebElement FindSectionByHeaderText(String locator, DriverScope scope)
    {
        return FindSectionByHeaderText(locator, "section",scope) ??
               FindSectionByHeaderText(locator, "div",scope);
    }

    private WebElement FindSectionByHeaderText(String locator, String sectionTag, DriverScope scope)
    {
        String headersXPath = StringJoiner.join(" or ", headerTags);
        Enumerable<WebElement> withAHeader = elementFinder.Find(By.XPath(String.Format(".//{0}[{1}]", sectionTag, headersXPath)),scope);

        return withAHeader.FirstDisplayedOrDefault(e => WhereAHeaderMatches(e, locator));
    }

    private boolean WhereAHeaderMatches(ISearchContext e, String locator)
    {
        return e.FindElements(By.XPath("./*")).Any(h => headerTags.Contains(h.TagName) && textMatcher.TextMatches(h, locator));
    }

    private static boolean IsSection(WebElement e)
    {
        return e.TagName == "section" || e.TagName == "div";
    }
}