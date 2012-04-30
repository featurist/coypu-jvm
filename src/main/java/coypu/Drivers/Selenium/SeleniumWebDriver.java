package coypu.Drivers.Selenium;

import com.google.common.base.Predicate;
import com.google.gson.Gson;
import coypu.*;
import coypu.Drivers.Browser;
import coypu.Drivers.BrowserNotSupportedException;
import coypu.Drivers.XPath;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SeleniumWebDriver implements Driver {
    private Browser browser;
    private boolean disposed;

    public boolean disposed() {
        return disposed;
    }

    public String getLocation(Scope scope) {
        elementFinder.seleniumScope(scope);
        return webDriver.getCurrentUrl();
    }

    public ElementFound window() {
        return new WindowHandle(webDriver, webDriver.getWindowHandle());
    }

    private WebDriver webDriver;
    private final ElementFinder elementFinder;
    private final FieldFinder fieldFinder;
    private final FrameFinder frameFinder;
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

    protected SeleniumWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
        xPath = new XPath();
        elementFinder = new ElementFinder(xPath);
        fieldFinder = new FieldFinder(elementFinder, xPath);
        frameFinder = new FrameFinder(this.webDriver, elementFinder, xPath);
        textMatcher = new TextMatcher();
        buttonFinder = new ButtonFinder(elementFinder, textMatcher, xPath);
        sectionFinder = new SectionFinder(elementFinder, textMatcher);
        dialogs = new Dialogs(this.webDriver);
        mouseControl = new MouseControl(this.webDriver);
        optionSelector = new OptionSelector();
    }

    private boolean javascript() {
        return browser.javascript();
    }
    private boolean noJavascript() {
        return !javascript();
    }

    private JavascriptExecutor javaScriptExecutor()
    {
        return (JavascriptExecutor) webDriver;
    }
    public Object getNative() {
        return window().getNative();
    }

    public ElementFound findField(String locator, Scope scope) {
        return buildElement(fieldFinder.findField(locator, scope), "No such field: " + locator);
    }

    public ElementFound findButton(String locator, Scope scope) {
        return buildElement(buttonFinder.findButton(locator, scope), "No such button: " + locator);
    }

    public ElementFound findFrame(String locator, Scope scope) {
        WebElement element = frameFinder.findFrame(locator, scope);

        if (element == null)
            throw new MissingHtmlException("Failed to find frame: " + locator);

        return new SeleniumFrame(element, webDriver);
    }

    public ElementFound findLink(String linkText, Scope scope) {
        return buildElement(Iterators.firstOrDefault(find(By.linkText(linkText), scope), scope), "No such link: " + linkText);
    }

    public ElementFound findId(String id, Scope scope) {
        return buildElement(Iterators.firstOrDefault(find(By.id(id), scope), scope), "Failed to find id: " + id);
    }

    public ElementFound findFieldset(String locator, Scope scope) {

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

    public ElementFound findSection(String locator, Scope scope) {
        return buildElement(sectionFinder.findSection(locator, scope), "Failed to find section: " + locator);
    }

    public ElementFound findCss(String cssSelector, Scope scope) {
        return buildElement(Iterators.firstOrDefault(find(By.cssSelector(cssSelector), scope), scope), "No element found by css: " + cssSelector);
    }

    public ElementFound findXPath(String xpath, Scope scope) {
        return buildElement(Iterators.firstOrDefault(find(By.xpath(xpath), scope), scope), "No element found by xpath: " + xpath);
    }

    public List<ElementFound> findAllCss(String cssSelector, Scope scope) {
        ArrayList<WebElement> allVisible = Iterators.allVisible(find(By.cssSelector(cssSelector), scope), scope);

        return asElementsFound(allVisible);
    }

    public List<ElementFound> findAllXPath(String xpath, Scope scope) {
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

    private List<WebElement> find(By by, Scope scope) {
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

    public boolean hasContent(String text, Scope scope) {
        return getContent(scope).contains(text);
    }

    public boolean hasContentMatch(Pattern pattern, Scope scope) {
        return pattern.matcher(getContent(scope)).find();
    }

    private String getContent(Scope scope) {
        SearchContext seleniumScope = elementFinder.seleniumScope(scope);
        return seleniumScope instanceof WebDriver
                ? getText(By.cssSelector("body"), seleniumScope)
                : getText(By.xpath("."), seleniumScope);
    }

    private String getText(By xpath, SearchContext seleniumScope) {
        String pageText = seleniumScope.findElement(xpath).getText();
        return normalizeCRLFBetweenBrowserImplementations(pageText);
    }

    public boolean hasCss(String cssSelector, Scope scope) {
        return Iterators.allVisible(find(By.cssSelector(cssSelector), scope), scope).size() > 0;
    }

    public boolean hasXPath(String xpath, Scope scope) {
        return Iterators.allVisible(find(By.xpath(xpath), scope), scope).size() > 0;
    }

    public boolean hasDialog(String withText, Scope scope) {
        elementFinder.seleniumScope(scope);
        return dialogs.hasDialog(withText);
    }

    public void visit(String url) {
        webDriver.navigate().to(url);
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
//        for (Cookie c : webDriver.manage().getCookies()) {
//            elements.add(new Cookie(c.Name(), c.Value(), c.getPath(), c.getDomain()));
//        }
//    }

    public ElementFound findWindow(String titleOrName, Scope scope) {
        return new WindowHandle(webDriver, findWindowHandle(titleOrName));
    }

    public String getTitle(Scope scope) {
        return  webDriver.getTitle();
    }

    private String findWindowHandle(String titleOrName) {
        String currentHandle = getCurrentWindowHandle();
        String matchingWindowHandle = null;

        try {
            webDriver.switchTo().window(titleOrName);
            matchingWindowHandle = webDriver.getWindowHandle();
        } catch (NoSuchWindowException ex) {
            for (String windowHandle : webDriver.getWindowHandles()) {
                webDriver.switchTo().window(windowHandle);
                if (windowHandle.equals(titleOrName) || webDriver.getTitle().equals(titleOrName)) {
                    matchingWindowHandle = windowHandle;
                    break;
                }
            }
        }

        if (matchingWindowHandle == null)
            throw new MissingHtmlException("No such window found: " + titleOrName);

        webDriver.switchTo().window(currentHandle);
        return matchingWindowHandle;
    }

    private String getCurrentWindowHandle() {
        try {
            return webDriver.getWindowHandle();
        } catch (Exception ex) // was InvalidOperationException in c#

        {
            return null;
        }
    }

    public void set(Element element, String value, boolean forceAllEvents) {
        WebElement seleniumElement = seleniumElement(element);

        try {
            seleniumElement.clear();
        }
        catch (InvalidElementStateException ex) // Non user-editable elements (file inputs) - chrome/IE
        {
            seleniumElement.sendKeys(value);
            return;
        }
        catch (WebDriverException ex)  // Non user-editable elements (file inputs) - firefox
        {
            seleniumElement.sendKeys(value);
            return;
        }
        SetByIdOrSendKeys(value, seleniumElement, forceAllEvents);
    }

    private void SetByIdOrSendKeys(String value, WebElement seleniumElement, boolean forceAllEvents)
    {
        String id = seleniumElement.getAttribute("id");
        if (id.isEmpty() || forceAllEvents || !noJavascript())
            seleniumElement.sendKeys(value);
        else
            javaScriptExecutor().executeScript(String.format("document.getElementById('%1$s').value = {%2$s}", id, toJson(value)));
    }

    private String toJson(Object value) {
        return new Gson().toJson(value);
    }

    public void select(Element element, String option) {
        optionSelector.select(element, option);
    }

    public void acceptModalDialog(Scope scope) {
        elementFinder.seleniumScope(scope);
        dialogs.acceptModalDialog();
    }

    public void cancelModalDialog(Scope scope) {
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

    public String executeScript(String javascript, Scope scope) {
        if (noJavascript())
            throw new UnsupportedOperationException("Javascript is not supported by " + browser);

        elementFinder.seleniumScope(scope);
        Object result = javaScriptExecutor().executeScript(javascript);
        return result == null ? null : result.toString();
    }

    private String normalizeCRLFBetweenBrowserImplementations(String text) {
        if (webDriver instanceof ChromeDriver) // Which adds extra whitespace around CRLF
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
        if (webDriver == null)
            return;

        acceptAnyAlert();

        webDriver.quit();
        webDriver = null;
        disposed = true;
    }

    private void acceptAnyAlert() {
        try {
            webDriver.switchTo().alert().accept();
        } catch (WebDriverException ex) {
        }
//        catch (KeyNotFoundException){} // Chrome
//        catch (InvalidOperationException){}
    }
}
