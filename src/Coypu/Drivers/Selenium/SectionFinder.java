package Coypu.Drivers.Selenium;

import Coypu.DriverScope;
import Coypu.StringJoiner;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

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
               elementFinder.Find(By.id(locator),scope).FirstDisplayedOrDefault(IsSection);
    }

    private WebElement FindSectionByHeaderText(String locator, DriverScope scope)
    {
        return FindSectionByHeaderText(locator, "section",scope) ??
               FindSectionByHeaderText(locator, "div",scope);
    }

    private WebElement FindSectionByHeaderText(String locator, String sectionTag, DriverScope scope)
    {
        String headersXPath = StringJoiner.join(" or ", headerTags);
        Iterable<WebElement> withAHeader = elementFinder.Find(By.xpath(String.format(".//{0}[{1}]", sectionTag, headersXPath)),scope);

        return withAHeader.FirstDisplayedOrDefault(e => WhereAHeaderMatches(e, locator));
    }

    private boolean WhereAHeaderMatches(SearchContext e, String locator)
    {
        return e.findElements(By.xpath("./*")).Any(h => headerTags.Contains(h.TagName) && textMatcher.TextMatches(h, locator));
    }

    private static boolean IsSection(WebElement e)
    {
        return e.getTagName() == "section" || e.getTagName() == "div";
    }
}