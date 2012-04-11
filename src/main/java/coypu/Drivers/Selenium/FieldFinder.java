package coypu.Drivers.Selenium;

import coypu.DriverScope;
import coypu.Drivers.XPath;
import coypu.Iterators;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

class FieldFinder {
    private static final String[] FieldInputTypes = new String[]{"text", "password", "radio", "checkbox", "file"};
    public static final String[] InputButtonTypes = new String[]{"button", "submit", "image"};
    private final ElementFinder elementFinder;
    private final XPath xPath;

    public FieldFinder(ElementFinder elementFinder, XPath xPath) {
        this.elementFinder = elementFinder;
        this.xPath = xPath;
    }

    public WebElement findField(String locator, DriverScope scope) {
        WebElement byId = findFieldById(locator, scope);
        if (byId != null) return byId;

        WebElement fromLabel = findFieldFromLabel(locator, scope);
        if (fromLabel != null) return fromLabel;

        WebElement byName = findFieldByName(locator, scope);
        if (byName != null) return byName;

        WebElement byPlaceholder = findFieldByPlaceholder(locator, scope);
        if (byPlaceholder != null) return byPlaceholder;

        return findRadioButtonFromValue(locator, scope);
    }

    private WebElement findRadioButtonFromValue(String locator, DriverScope scope) {
        return Iterators.firstOrDefault(elementFinder.find(By.xpath(".//input[@type = 'radio']"), scope), attributeMatches("value", locator), scope);
    }

    private Predicate<WebElement> attributeMatches(final String attribute, final String locator) {
        return new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return e.getAttribute(attribute).equals(locator);
            }
        };
    }

    private WebElement findFieldFromLabel(String locator, DriverScope scope) {
        WebElement label = findLabelByText(locator, scope);
        if (label == null)
            return null;

        String id = label.getAttribute("for");

        WebElement field = id != null
                ? findFieldById(id, scope)
                : Iterators.firstOrDefault(label.findElements(By.xpath("*")), isField(scope), scope);

        return field;
    }

    private WebElement findLabelByText(String locator, DriverScope scope) {
        WebElement byExactText = Iterators.firstOrDefault(elementFinder.find(By.xpath(xPath.format(".//label[text() = %1$s]", locator)), scope), scope);
        if (byExactText != null) return byExactText;

        return Iterators.firstOrDefault(elementFinder.find(By.xpath(xPath.format(".//label[contains(text(),%1$s)]", locator)), scope), scope);
    }

    private WebElement findFieldByPlaceholder(String placeholder, DriverScope scope) {
        return Iterators.firstOrDefault(elementFinder.find(By.xpath(xPath.format(".//input[@placeholder = %1$s]", placeholder)), scope), isField(scope), scope);
    }

    private WebElement findFieldByName(String locator, DriverScope scope) {
        return Iterators.firstOrDefault(elementFinder.find(By.name(locator), scope), isField(scope), scope);
    }

    private WebElement findFieldById(String id, DriverScope scope) {
        return Iterators.firstOrDefault(elementFinder.find(By.id(id), scope), isField(scope), scope);
    }

    private Predicate<WebElement> isField(final DriverScope scope) {
        return new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return (e.isDisplayed() || scope.considerInvisibleElements()) &&
                        (isInputField(e, scope) || e.getTagName().equals("select") || e.getTagName().equals("textarea"));
            }
        };
    }

    private boolean isInputField(WebElement e, DriverScope scope) {
        new ArrayList<String>();

        return e.getTagName().equals("input") && (Arrays.asList(FieldInputTypes).contains(e.getAttribute("type")) ||
                scope.considerInvisibleElements() && e.getAttribute("type").equals("hidden"));
    }
}
