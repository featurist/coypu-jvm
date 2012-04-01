package Coypu.Drivers.Selenium;

import Coypu.*;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SectionFinder {
    private final ElementFinder elementFinder;
    private final TextMatcher textMatcher;

    final String[] headerTags = new String[]{"h1", "h2", "h3", "h4", "h5", "h6"};
    final List<String> headerTagsList = Arrays.asList(headerTags);

    public SectionFinder(ElementFinder elementFinder, TextMatcher textMatcher) {
        this.elementFinder = elementFinder;
        this.textMatcher = textMatcher;
    }

    public WebElement findSection(String locator, DriverScope scope) {
        WebElement byHeaderText = findSectionByHeaderText(locator, scope);
        if (byHeaderText != null) return byHeaderText;

        return Iterators.firstOrDefault(elementFinder.find(By.id(locator), scope), isSection(), scope);
    }

    private WebElement findSectionByHeaderText(String locator, DriverScope scope) {
        WebElement byHeaderText = findSectionByHeaderText(locator, "section", scope);
        if (byHeaderText != null) return byHeaderText;

        return findSectionByHeaderText(locator, "div", scope);
    }

    private WebElement findSectionByHeaderText(String locator, String sectionTag, DriverScope scope) {
        String headersXPath = StringJoiner.join(" or ", headerTags);
        List<WebElement> withAHeader = elementFinder.find(By.xpath(String.format(".//%1$s[%2$s]", sectionTag, headersXPath)), scope);

        return Iterators.firstOrDefault(withAHeader, whereAHeaderMatches(locator, scope), scope);
    }

    private Predicate<WebElement> whereAHeaderMatches(final String locator, final DriverScope scope) {
        return new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement container) {
                return Iterators.firstOrDefault(container.findElements(By.xpath("./*")), new Predicate<WebElement>() {
                    @Override
                    public boolean apply(@Nullable WebElement h) {
                        return headerTagsList.contains(h.getTagName()) && textMatcher.textMatches(h, locator);
                    }
                }, scope) != null;
            }
        };
    }

    private static Predicate<WebElement> isSection() {
        return new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return e.getTagName().equals("section") || e.getTagName().equals("div");
            }
        };
    }
}
