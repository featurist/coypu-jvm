package Coypu.Drivers.Selenium;

import Coypu.*;
import Coypu.Drivers.Browser;
import Coypu.Drivers.BrowserNotSupportedException;
import Coypu.Drivers.XPath;
import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class SeleniumWebDriver implements Driver {
    private Browser browser;
    private boolean disposed;

    public boolean disposed() {
        return disposed;
    }

    public String location() {
        return selenium.getCurrentUrl();
    }

    public ElementFound window() {
        return new WindowHandle(selenium, selenium.getWindowHandle());
    }

    private RemoteWebDriver selenium;
    private final ElementFinder elementFinder;
    private final FieldFinder fieldFinder;
    private final IFrameFinder iframeFinder;
    private final ButtonFinder buttonFinder;
    private final SectionFinder sectionFinder;
    private final TextMatcher textMatcher;
    private final Dialogs dialogs;
    private final MouseControl mouseControl;
    private final OptionSelector optionSelector;
    private final XPath xPath;

    public SeleniumWebDriver(Browser browser) throws BrowserNotSupportedException {
        this(new DriverFactory().newRemoteWebDriver(browser));
        this.browser = browser;
    }

    protected SeleniumWebDriver(RemoteWebDriver webDriver) {
        selenium = webDriver;
        xPath = new XPath();
        elementFinder = new ElementFinder(xPath);
        fieldFinder = new FieldFinder(elementFinder, xPath);
        iframeFinder = new IFrameFinder(selenium, elementFinder, xPath);
        textMatcher = new TextMatcher();
        buttonFinder = new ButtonFinder(elementFinder, textMatcher, xPath);
        sectionFinder = new SectionFinder(elementFinder, textMatcher);
        dialogs = new Dialogs(selenium);
        mouseControl = new MouseControl(selenium);
        optionSelector = new OptionSelector();
    }

    public Object getNative() {
        return window().getNative();
    }

    public ElementFound findField(String locator, DriverScope scope) {
        return buildElement(fieldFinder.findField(locator, scope), "No such field: " + locator);
    }

    public ElementFound findButton(String locator, DriverScope scope) {
        return buildElement(buttonFinder.findButton(locator, scope), "No such button: " + locator);
    }

    public ElementFound findIFrame(String locator, DriverScope scope) {
        WebElement element = iframeFinder.findIFrame(locator, scope);

        if (element == null)
            throw new MissingHtmlException("Failed to find frame: " + locator);

        return new SeleniumFrame(element, selenium);
    }

    public ElementFound findLink(String linkText, DriverScope scope) {
        return buildElement(Iterators.firstOrDefault(find(By.linkText(linkText), scope), scope), "No such link: " + linkText);
    }

    public ElementFound findId(String id, DriverScope scope) {
        return buildElement(Iterators.firstOrDefault(find(By.id(id), scope), scope), "Failed to find id: " + id);
    }

    public ElementFound findFieldset(String locator, DriverScope scope) {

        WebElement fieldset = Iterators.firstOrDefault(find(By.xpath(xPath.format(".//fieldset[legend[text() = %1$s]]", locator)), scope), scope);

        if (fieldset == null)
            fieldset = Iterators.firstOrDefault(find(By.id(locator), scope), tagNameMatches("fieldset"), scope);

        return buildElement(fieldset, "Failed to find fieldset: " + locator);
    }

    public Predicate<WebElement> tagNameMatches(final String tagName) {
        return new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return e.getTagName().equals(tagName);
            }
        };
    }

    public ElementFound findSection(String locator, DriverScope scope) {
        return buildElement(sectionFinder.findSection(locator, scope), "Failed to find section: " + locator);
    }

    public ElementFound findCss(String cssSelector, DriverScope scope) {
        return buildElement(Iterators.firstOrDefault(find(By.cssSelector(cssSelector), scope), scope), "No element found by css: " + cssSelector);
    }

    public ElementFound findXPath(String xpath, DriverScope scope) {
        return buildElement(Iterators.firstOrDefault(find(By.xpath(xpath), scope), scope), "No element found by xpath: " + xpath);
    }

    public List<ElementFound> findAllCss(String cssSelector, DriverScope scope) {
        ArrayList<WebElement> allVisible = Iterators.allVisible(find(By.cssSelector(cssSelector), scope), scope);

        return asElementsFound(allVisible);
    }

    public List<ElementFound> findAllXPath(String xpath, DriverScope scope) {
        ArrayList<WebElement> allVisible = Iterators.allVisible(find(By.xpath(xpath), scope), scope);

        return asElementsFound(allVisible);
    }

    private List<ElementFound> asElementsFound(ArrayList<WebElement> allVisible) {
        ArrayList<ElementFound> elements = new ArrayList<ElementFound>();
        for (WebElement seleniumElement : allVisible) {
            elements.add(buildElement(seleniumElement));
        }
        return elements;
    }

    private List<WebElement> find(By by, DriverScope scope) {
        return elementFinder.find(by, scope);
    }

    private ElementFound buildElement(WebElement element, String failureMessage) {
        if (element == null)
            throw new MissingHtmlException(failureMessage);

        return buildElement(element);
    }

    private SeleniumElement buildElement(WebElement element) {
        return new SeleniumElement(element);
    }

    public boolean hasContent(String text, DriverScope scope) {
        return getContent(scope).contains(text);
    }

    public boolean hasContentMatch(Pattern pattern, DriverScope scope) {
        return pattern.matcher(getContent(scope)).find();
    }

    private String getContent(DriverScope scope) {
        SearchContext seleniumScope = elementFinder.seleniumScope(scope);
        return seleniumScope instanceof RemoteWebDriver
                ? getText(By.cssSelector("body"), seleniumScope)
                : getText(By.xpath("."), seleniumScope);
    }

    private String getText(By xpath, SearchContext seleniumScope) {
        String pageText = seleniumScope.findElement(xpath).getText();
        return normalizeCRLFBetweenBrowserImplementations(pageText);
    }

    public boolean hasCss(String cssSelector, DriverScope scope) {
        return Iterators.allVisible(find(By.cssSelector(cssSelector), scope), scope).size() > 0;
    }

    public boolean hasXPath(String xpath, DriverScope scope) {
        return Iterators.allVisible(find(By.xpath(xpath), scope), scope).size() > 0;
    }

    public boolean hasDialog(String withText, DriverScope scope) {
        elementFinder.seleniumScope(scope);
        return dialogs.hasDialog(withText);
    }

    public void visit(String url) {
        selenium.navigate().to(url);
    }

    public void click(Element element) {
        seleniumElement(element).click();
    }

    public void hover(Element element) {
        mouseControl.hover(element);
    }

//    public List<Cookie> getBrowserCookies()
//    {
//        ArrayList<Cookie> elements = new ArrayList<Cookie>();
//        for (Cookie c : selenium.manage().getCookies()) {
//            elements.add(new Cookie(c.Name(), c.Value(), c.getPath(), c.getDomain()));
//        }
//    }

    public ElementFound findWindow(String titleOrName, DriverScope scope) {
        return new WindowHandle(selenium, findWindowHandle(titleOrName));
    }

    private String findWindowHandle(String titleOrName) {
        String currentHandle = getCurrentWindowHandle();
        String matchingWindowHandle = null;

        try {
            selenium.switchTo().window(titleOrName);
            matchingWindowHandle = selenium.getWindowHandle();
        } catch (NoSuchWindowException ex) {
            for (String windowHandle : selenium.getWindowHandles()) {
                selenium.switchTo().window(windowHandle);
                if (windowHandle.equals(titleOrName) || selenium.getTitle().equals(titleOrName)) {
                    matchingWindowHandle = windowHandle;
                    break;
                }
            }
        }

        if (matchingWindowHandle == null)
            throw new MissingHtmlException("No such window found: " + titleOrName);

        selenium.switchTo().window(currentHandle);
        return matchingWindowHandle;
    }

    private String getCurrentWindowHandle() {
        try {
            return selenium.getWindowHandle();
        } catch (Exception ex) // was InvalidOperationException in c#

        {
            return null;
        }
    }

    public void set(Element element, String value) {
        WebElement seleniumElement = seleniumElement(element);

        try {
            seleniumElement.clear();
        } catch (InvalidElementStateException ex) // Non user-editable elements (file inputs) - chrome/IE
        {
        } catch (WebDriverException ex)  // Non user-editable elements (file inputs) - firefox
        {
        }
        seleniumElement.sendKeys(value);
    }

    public void select(Element element, String option) {
        optionSelector.select(element, option);
    }

    public void acceptModalDialog(DriverScope scope) {
        elementFinder.seleniumScope(scope);
        dialogs.acceptModalDialog();
    }

    public void cancelModalDialog(DriverScope scope) {
        elementFinder.seleniumScope(scope);
        dialogs.cancelModalDialog();
    }

    public void check(Element field) {
        WebElement seleniumElement = seleniumElement(field);

        if (!seleniumElement.isSelected())
            seleniumElement.click();
    }

    public void uncheck(Element field) {
        WebElement seleniumElement = seleniumElement(field);

        if (seleniumElement.isSelected())
            seleniumElement.click();
    }

    public void choose(Element field) {
        seleniumElement(field).click();
    }

    public String executeScript(String javascript, DriverScope scope) {
        elementFinder.seleniumScope(scope);
        Object result = selenium.executeScript(javascript);
        return result == null ? null : result.toString();
    }

    private String normalizeCRLFBetweenBrowserImplementations(String text) {
        if (selenium instanceof ChromeDriver) // Which adds extra whitespace around CRLF
            text = stripWhitespaceAroundCRLFs(text);

        return Pattern.compile("(\r\n)+").matcher(text).replaceAll("\r\n");
    }

    private String stripWhitespaceAroundCRLFs(String pageText) {
        return Pattern.compile("\\s*\r\n\\s*").matcher(pageText).replaceAll("\r\n");
    }

    private WebElement seleniumElement(Element element) {
        return ((WebElement) element.getNative());
    }

    public void dispose() {
        if (selenium == null)
            return;

        acceptAnyAlert();

        selenium.quit();
        selenium = null;
        disposed = true;
    }

    private void acceptAnyAlert() {
        try {
            selenium.switchTo().alert().accept();
        } catch (WebDriverException ex) {
        }
//        catch (KeyNotFoundException){} // Chrome
//        catch (InvalidOperationException){}
    }
}
