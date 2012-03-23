package Coypu.Drivers.Selenium;

import Coypu.DriverScope;
import Coypu.Drivers.XPath;
import Coypu.Iterators;
import Coypu.MissingHtmlException;
import Coypu.TimeoutException;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

class FieldFinder
{
    private static final String[] FieldInputTypes = new String[] { "text", "password", "radio", "checkbox", "file" };
    public static final String[] InputButtonTypes = new String[] { "button", "submit", "image" };
    private final ElementFinder elementFinder;
    private final XPath xPath;

    public FieldFinder(ElementFinder elementFinder, XPath xPath)
    {
        this.elementFinder = elementFinder;
        this.xPath = xPath;
    }

    public WebElement FindField(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException {
        WebElement fromLabel = FindFieldFromLabel(locator, scope);
        if (fromLabel != null) return fromLabel;
        
        WebElement byIdOrName = FindFieldByIdOrName(locator, scope);
        if (byIdOrName != null) return byIdOrName;
        
        WebElement byPlaceholder = FindFieldByPlaceholder(locator, scope);
        if (byPlaceholder!= null) return byPlaceholder;
        
        WebElement fromValue = FindRadioButtonFromValue(locator, scope);
        if (fromValue != null) return fromValue;
        
        return Iterators.FirstOrDefault(elementFinder.FindByPartialId(locator, scope),IsField(scope), scope);
    }

    private WebElement FindRadioButtonFromValue(String locator,DriverScope scope) throws MissingHtmlException, TimeoutException {
        return Iterators.FirstOrDefault(elementFinder.Find(By.xpath(".//input[@type = 'radio']"), scope), attributeMatches("value",locator), scope);
    }

    private Predicate<WebElement> attributeMatches(final String attribute, final String locator) {
        return new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return e.getAttribute(attribute) == locator;
            }
        } ;
    }

    private WebElement FindFieldFromLabel(String locator,DriverScope scope) throws MissingHtmlException, TimeoutException {
        WebElement label = FindLabelByText(locator, scope);
        if (label == null)
            return null;

        String id = label.getAttribute("for");

        WebElement field = id != null
                        ? FindFieldById(id, scope)
                        : Iterators.FirstOrDefault(label.findElements(By.xpath("*")),IsField(scope), scope);

        return field;
    }

    private WebElement FindLabelByText(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException {
        WebElement byExactText = Iterators.FirstOrDefault(elementFinder.Find(By.xpath(xPath.Format(".//label[text() = {0}]", locator)), scope), scope);
        if (byExactText == null) return byExactText;

        return Iterators.FirstOrDefault(elementFinder.Find(By.xpath(xPath.Format(".//label[contains(text(),{0})]", locator)), scope), scope);
    }

    private WebElement FindFieldByPlaceholder(String placeholder,DriverScope scope) throws MissingHtmlException, TimeoutException {
        return Iterators.FirstOrDefault(elementFinder.Find(By.xpath(xPath.Format(".//input[@placeholder = {0}]", placeholder)), scope),IsField(scope), scope);
    }

    private WebElement FindFieldByIdOrName(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException {
        String xpathToFind = xPath.Format(".//*[@id = {0} or @name = {0}]", locator);
        return Iterators.FirstOrDefault(elementFinder.Find(By.xpath(xpathToFind), scope),IsField(scope), scope);
    }

    private WebElement FindFieldById(String id, DriverScope scope) throws MissingHtmlException, TimeoutException {
        return Iterators.FirstOrDefault(elementFinder.Find(By.id(id), scope),IsField(scope), scope);
    }

    private Predicate<WebElement> IsField(final DriverScope scope)
    {
        return new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return (e.isDisplayed() || scope.ConsiderInvisibleElements()) &&
                       (IsInputField(e, scope) || e.getTagName() == "select" || e.getTagName() == "textarea");
            }
        };
    }

    private boolean IsInputField(WebElement e, DriverScope scope)
    {
        new ArrayList<String>();

        return e.getTagName() == "input" && (Arrays.asList(FieldInputTypes).contains(e.getAttribute("type")) ||
                scope.ConsiderInvisibleElements() && e.getAttribute("type") == "hidden");
    }
}
