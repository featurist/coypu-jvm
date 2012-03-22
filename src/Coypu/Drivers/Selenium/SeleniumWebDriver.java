package Coypu.Drivers.Selenium;

import Coypu.*;
import Coypu.Drivers.Browser;
import Coypu.Drivers.BrowserNotSupportedException;
import Coypu.Drivers.XPath;
import Coypu.TimeoutException;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.util.regex.*;

public class SeleniumWebDriver implements Driver
{
    private Browser browser;
    private boolean disposed;

    public boolean Disposed() {
        return disposed;
    }

    public Uri Location()
    {
        return new Uri(selenium.getCurrentUrl());
    }

    public ElementFound Window()
    {
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
        this(new DriverFactory().NewRemoteWebDriver(browser));
        this.browser = browser;
    }

    protected SeleniumWebDriver(RemoteWebDriver webDriver)
    {
        selenium = webDriver;
        xPath = new XPath();
        elementFinder = new ElementFinder(xPath);
        fieldFinder = new FieldFinder(elementFinder, xPath);
        iframeFinder = new IFrameFinder(selenium, elementFinder,xPath);
        textMatcher = new TextMatcher();
        buttonFinder = new ButtonFinder(elementFinder, textMatcher, xPath);
        sectionFinder = new SectionFinder(elementFinder, textMatcher);
        dialogs = new Dialogs(selenium);
        mouseControl = new MouseControl(selenium);
        optionSelector = new OptionSelector();
    }

    public Object Native()
    {
        return Window().Native();
    }

    public ElementFound FindField(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return BuildElement(fieldFinder.FindField(locator,scope), "No such field: " + locator);
    }

    public ElementFound FindButton(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return BuildElement(buttonFinder.FindButton(locator, scope), "No such button: " + locator);
    }

    public ElementFound FindIFrame(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        WebElement element = iframeFinder.FindIFrame(locator, scope);

        if (element == null)
            throw new MissingHtmlException("Failed to find frame: " + locator);

        return new SeleniumFrame(element,selenium);
    }

    public ElementFound FindLink(String linkText, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return BuildElement(Find(By.linkText(linkText), scope).FirstOrDefault(), "No such link: " + linkText);
    }

    public ElementFound FindId(String id,DriverScope scope ) throws MissingHtmlException, TimeoutException, InterruptedException {
        return BuildElement(Find(By.id(id), scope).FirstDisplayedOrDefault(), "Failed to find id: " + id);
    }

    public ElementFound FindFieldset(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        WebElement fieldset =
            Find(By.xpath(xPath.Format(".//fieldset[legend[text() = {0}]]", locator)),scope).FirstOrDefault() ??
            Find(By.id(locator),scope).FirstOrDefault(e => e.TagName == "fieldset");

        return BuildElement(fieldset, "Failed to find fieldset: " + locator);
    }

    public ElementFound FindSection(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return BuildElement(sectionFinder.FindSection(locator,scope), "Failed to find section: " + locator);
    }

    public ElementFound FindCss(String cssSelector,DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return BuildElement(Find(By.cssSelector(cssSelector),scope).FirstOrDefault(),"No element found by css: " + cssSelector);
    }

    public ElementFound FindXPath(String xpath, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return BuildElement(Find(By.xpath(xpath), scope).FirstOrDefault(),"No element found by xpath: " + xpath);
    }

    public Iterable<ElementFound> FindAllCss(String cssSelector, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return Find(By.cssSelector(cssSelector),scope).Select(e => BuildElement(e)).Cast<ElementFound>();
    }

    public Iterable<ElementFound> FindAllXPath(String xpath, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return Find(By.xpath(xpath), scope).Select(e => BuildElement(e)).Cast<ElementFound>();
    }

    private Iterable<WebElement> Find(By by, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return elementFinder.Find(by, scope);
    }

    private ElementFound BuildElement(WebElement element, String failureMessage) throws MissingHtmlException {
        if (element == null)
            throw new MissingHtmlException(failureMessage);

        return BuildElement(element);
    }

    private SeleniumElement BuildElement(WebElement element)
    {
        return new SeleniumElement(element);
    }

    public boolean HasContent(String text, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return GetContent(scope).contains(text);
    }

    public boolean HasContentMatch(Pattern pattern, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return pattern.matcher(GetContent(scope)).matches();
    }

    private String GetContent(DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        SearchContext seleniumScope = elementFinder.SeleniumScope(scope);
        return seleniumScope is RemoteWebDriver
                   ? GetText(By.cssSelector("body"), seleniumScope)
                   : GetText(By.xpath("."), seleniumScope);
    }

    private String GetText(By xpath, SearchContext seleniumScope)
    {   
        String pageText = seleniumScope.findElement(xpath).getText();
        return NormalizeCRLFBetweenBrowserImplementations(pageText);
    }

    public boolean HasCss(String cssSelector, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return Find(By.cssSelector(cssSelector), scope).iterator().hasNext();
    }

    public boolean HasXPath(String xpath, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return Find(By.xpath(xpath), scope).iterator().hasNext();
    }

    public boolean HasDialog(String withText, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        elementFinder.SeleniumScope(scope);
        return dialogs.HasDialog(withText);
    }

    public void Visit(String url) 
    {
        selenium.navigate().to(url);
    }

    public void Click(Element element) 
    {
        SeleniumElement(element).click();
    }

    public void Hover(Element element)
    {
        mouseControl.Hover(element);
    }

    public Iterable<Cookie> GetBrowserCookies()
    {
        return selenium.manage().getCookies().iterator().Select(c => new Cookie(c.Name, c.Value, c.Path, c.Domain));
    }

    public ElementFound FindWindow(String titleOrName, DriverScope scope) throws MissingHtmlException {
        return new WindowHandle(selenium, FindWindowHandle(titleOrName));
    }

    private String FindWindowHandle(String titleOrName) throws MissingHtmlException {
        String currentHandle = GetCurrentWindowHandle();
        String matchingWindowHandle = null;

        try
        {
            selenium.switchTo().window(titleOrName);
            matchingWindowHandle = selenium.getWindowHandle();
        }
        catch (NoSuchWindowException ex)
        {
            foreach (String windowHandle in selenium.getWindowHandles())
            {
                selenium.switchTo().window(windowHandle);
                if (windowHandle == titleOrName || selenium.Title == titleOrName)
                {
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

    private String GetCurrentWindowHandle()
    {
        try
        {
            return selenium.getWindowHandle();
        }
        catch (InvalidOperationException)
        {
            return null;
        }
    }

    public void Set(Element element, String value) 
    {
        WebElement seleniumElement = SeleniumElement(element);

        try
        {
            seleniumElement.clear();
        }
        catch (InvalidElementStateException ex) // Non user-editable elements (file inputs) - chrome/IE
        {
        }
        catch(InvalidOperationException ex)  // Non user-editable elements (file inputs) - firefox
        {
        }
        seleniumElement.sendKeys(value);
    }

    public void Select(Element element, String option) throws MissingHtmlException {
        optionSelector.Select(element, option);
    }

    public void AcceptModalDialog(DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        elementFinder.SeleniumScope(scope);
        dialogs.AcceptModalDialog();
    }

    public void CancelModalDialog(DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        elementFinder.SeleniumScope(scope);
        dialogs.CancelModalDialog();
    }

    public void Check(Element field)
    {
        WebElement seleniumElement = SeleniumElement(field);

        if (!seleniumElement.isSelected())
            seleniumElement.click();
    }

    public void Uncheck(Element field)
    {
        WebElement seleniumElement = SeleniumElement(field);

        if (seleniumElement.isSelected())
            seleniumElement.click();
    }

    public void Choose(Element field)
    {
        SeleniumElement(field).click();
    }

    public String ExecuteScript(String javascript, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        elementFinder.SeleniumScope(scope);
        Object result = selenium.executeScript(javascript);
        return result == null ? null : result.toString();
    }

    private String NormalizeCRLFBetweenBrowserImplementations(String text)
    {
        if (selenium is ChromeDriver) // Which adds extra whitespace around CRLF
            text = StripWhitespaceAroundCRLFs(text);

        return Pattern.compile("(\r\n)+").matcher(text).replaceAll("\r\n");
    }

    private String StripWhitespaceAroundCRLFs(String pageText)
    {
        return Pattern.compile("\\s*\r\n\\s*").matcher(pageText).replaceAll("\r\n");§
    }

    private WebElement SeleniumElement(Element element)
    {
        return ((WebElement) element.Native());
    }

    public void Dispose()
    {
        if (selenium == null)
            return;

        AcceptAnyAlert();

        selenium.quit();
        selenium = null;
        disposed = true;
    }

    private void AcceptAnyAlert()
    {
        try
        {
            selenium.switchTo().alert().accept();
        }
        catch (WebDriverException ex){}
        catch (KeyNotFoundException){} // Chrome
        catch (InvalidOperationException){}
    }
}