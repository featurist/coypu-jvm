package Coypu.Drivers.Selenium;

import Coypu.DriverScope;
import Coypu.Drivers.XPath;
import Coypu.MissingHtmlException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    public WebElement FindField(String locator, DriverScope scope)
    {
        return FindFieldFromLabel(locator, scope) ??
               FindFieldByIdOrName(locator, scope) ??
               FindFieldByPlaceholder(locator, scope) ??
               FindRadioButtonFromValue(locator, scope) ??
               elementFinder.FindByPartialId(locator, scope).FirstOrDefault(e => IsField(e, scope));
    }

    private WebElement FindRadioButtonFromValue(String locator,DriverScope scope) throws MissingHtmlException {
        return elementFinder.Find(By.xpath(".//input[@type = 'radio']"), scope).FirstOrDefault(e => e.GetAttribute("value") == locator);
    }

    private WebElement FindFieldFromLabel(String locator,DriverScope scope) throws MissingHtmlException {
        WebElement label = FindLabelByText(locator, scope);
        if (label == null)
            return null;

        String id = label.getAttribute("for");

        WebElement field = id != null
                        ? FindFieldById(id, scope)
                        : label.findElements(By.xpath("*")).FirstDisplayedOrDefault(e => IsField(e, scope));

        return field;
    }

    private WebElement FindLabelByText(String locator, DriverScope scope) throws MissingHtmlException {
        return
            elementFinder.Find(By.xpath(xPath.Format(".//label[text() = {0}]", locator)), scope).FirstOrDefault() ??
            elementFinder.Find(By.xpath(xPath.Format(".//label[contains(text(),{0})]", locator)), scope).FirstOrDefault();
    }

    private WebElement FindFieldByPlaceholder(String placeholder,DriverScope scope) throws MissingHtmlException {
        return elementFinder.Find(By.xpath(xPath.Format(".//input[@placeholder = {0}]", placeholder)), scope).FirstOrDefault(e => IsField(e, scope));
    }

    private WebElement FindFieldByIdOrName(String locator, DriverScope scope) throws MissingHtmlException {
        String xpathToFind = xPath.Format(".//*[@id = {0} or @name = {0}]", locator);
        return elementFinder.Find(By.xpath(xpathToFind), scope).FirstOrDefault(e => IsField(e,scope));
    }

    private WebElement FindFieldById(String id, DriverScope scope) throws MissingHtmlException {
        return elementFinder.Find(By.id(id), scope).FirstOrDefault(e => IsField(e,scope));
    }

    private boolean IsField(WebElement e, DriverScope scope)
    {
        return IsInputField(e, scope) || e.getTagName() == "select" || e.getTagName() == "textarea";
    }

    private boolean IsInputField(WebElement e, DriverScope scope)
    {
        new ArrayList<String>();

        return e.getTagName() == "input" && (Arrays.asList(FieldInputTypes).contains(e.getAttribute("type")) ||
                scope.ConsiderInvisibleElements() && e.getAttribute("type") == "hidden");
    }
}