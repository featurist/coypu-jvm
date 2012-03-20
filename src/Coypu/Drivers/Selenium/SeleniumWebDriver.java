package Coypu.Drivers.Selenium;

import Coypu.*;
import Coypu.Drivers.Browser;
import Coypu.Drivers.BrowserNotSupportedException;
import Coypu.Drivers.XPath;
import com.sun.jndi.toolkit.url.Uri;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumWebDriver implements Driver
{
    private Browser browser;
    private boolean disposed;

    public boolean Disposed() {
        return disposed;
    }

    public Uri Location()
    {
        return new Uri(selenium.Url);
    }

    public ElementFound Window()
    {
        return new WindowHandle(selenium, selenium.getWindowHandle());
    }

    private final RemoteWebDriver selenium;
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
        return selenium;
    }

    public ElementFound FindField(String locator, DriverScope scope)
    {
        return BuildElement(fieldFinder.FindField(locator,scope), "No such field: " + locator);
    }

    public ElementFound FindButton(String locator, DriverScope scope)
    {
        return BuildElement(buttonFinder.FindButton(locator, scope), "No such button: " + locator);
    }

    public ElementFound FindIFrame(String locator, DriverScope scope) throws MissingHtmlException {
        WebElement element = iframeFinder.FindIFrame(locator, scope);

        if (element == null)
            throw new MissingHtmlException("Failed to find frame: " + locator);

        return new SeleniumFrame(element,selenium);
    }

    public ElementFound FindLink(String linkText, DriverScope scope)
    {
        return BuildElement(Find(By.linkText(linkText), scope).FirstOrDefault(), "No such link: " + linkText);
    }

    public ElementFound FindId(String id,DriverScope scope ) 
    {
        return BuildElement(Find(By.id(id), scope).FirstDisplayedOrDefault(), "Failed to find id: " + id);
    }

    public ElementFound FindFieldset(String locator, DriverScope scope)
    {
        WebElement fieldset =
            Find(By.xpath(xPath.Format(".//fieldset[legend[text() = {0}]]", locator)),scope).FirstOrDefault() ??
            Find(By.id(locator),scope).FirstOrDefault(e => e.TagName == "fieldset");

        return BuildElement(fieldset, "Failed to find fieldset: " + locator);
    }

    public ElementFound FindSection(String locator, DriverScope scope)
    {
        return BuildElement(sectionFinder.FindSection(locator,scope), "Failed to find section: " + locator);
    }

    public ElementFound FindCss(String cssSelector,DriverScope scope)
    {
        return BuildElement(Find(By.cssSelector(cssSelector),scope).FirstOrDefault(),"No element found by css: " + cssSelector);
    }

    public ElementFound FindXPath(String xpath, DriverScope scope)
    {
        return BuildElement(Find(By.xpath(xpath),scope).FirstOrDefault(),"No element found by xpath: " + xpath);
    }

    public Enumerable<ElementFound> FindAllCss(String cssSelector, DriverScope scope)
    {
        return Find(By.cssSelector(cssSelector),scope).Select(e => BuildElement(e)).Cast<ElementFound>();
    }

    public Enumerable<ElementFound> FindAllXPath(String xpath, DriverScope scope)
    {
        return Find(By.xpath(xpath), scope).Select(e => BuildElement(e)).Cast<ElementFound>();
    }

    private Enumerable<WebElement> Find(By by, DriverScope scope)
    {
        return elementFinder.Find(by, scope);
    }

    private ElementFound BuildElement(WebElement element, String failureMessage)
    {
        if (element == null)
            throw new MissingHtmlException(failureMessage);

        return BuildElement(element);
    }

    private SeleniumElement BuildElement(WebElement element)
    {
        return new SeleniumElement(element);
    }

    public boolean HasContent(String text, DriverScope scope)
    {
        return GetContent(scope).contains(text);
    }

    public boolean HasContentMatch(Regex pattern, DriverScope scope)
    {
        return pattern.IsMatch(GetContent(scope));
    }

    private String GetContent(DriverScope scope)
    {
        SearchContext seleniumScope = elementFinder.SeleniumScope(scope);
        return seleniumScope is RemoteWebDriver
                   ? GetText(By.cssSelector("body"), seleniumScope)
                   : GetText(By.xpath("."), seleniumScope);
    }

    private String GetText(By xpath, SearchContext seleniumScope)
    {   
        String pageText = seleniumScope.FindElement(xpath).Text;
        return NormalizeCRLFBetweenBrowserImplementations(pageText);
    }

    public boolean HasCss(String cssSelector, DriverScope scope)
    {
        return Find(By.cssSelector(cssSelector), scope).Any();
    }

    public boolean HasXPath(String xpath, DriverScope scope)
    {
        return Find(By.xpath(xpath), scope).Any();
    }

    public boolean HasDialog(String withText, DriverScope scope)
    {
        elementFinder.SeleniumScope(scope);
        return dialogs.HasDialog(withText);
    }

    public void Visit(String url) 
    {
        selenium.navigate().to(url);
    }

    public void Click(Element element) 
    {
        SeleniumElement(element).Click();
    }

    public void Hover(Element element)
    {
        mouseControl.Hover(element);
    }

    public Enumerable<Cookie> GetBrowserCookies()
    {
        return selenium.Manage().Cookies.AllCookies.Select(c => new Cookie(c.Name, c.Value, c.Path, c.Domain));
    }

    public ElementFound FindWindow(String titleOrName, DriverScope scope)
    {
        return new WindowHandle(selenium, FindWindowHandle(titleOrName));
    }

    private String FindWindowHandle(String titleOrName)
    {
        var currentHandle = GetCurrentWindowHandle();
        String matchingWindowHandle = null;

        try
        {
            selenium.SwitchTo().Window(titleOrName);
            matchingWindowHandle = selenium.CurrentWindowHandle;
        }
        catch (NoSuchWindowException)
        {
            foreach (var windowHandle in selenium.WindowHandles)
            {
                selenium.SwitchTo().Window(windowHandle);
                if (windowHandle == titleOrName || selenium.Title == titleOrName)
                {
                    matchingWindowHandle = windowHandle;
                    break;
                }
            }
        }

        if (matchingWindowHandle == null)
            throw new MissingHtmlException("No such window found: " + titleOrName);

        selenium.SwitchTo().Window(currentHandle);
        return matchingWindowHandle;
    }

    private String GetCurrentWindowHandle()
    {
        try
        {
            return selenium.CurrentWindowHandle;
        }
        catch (InvalidOperationException)
        {
            return null;
        }
    }

    public void Set(Element element, String value) 
    {
        var seleniumElement = SeleniumElement(element);

        try
        {
            seleniumElement.Clear();
        }
        catch (InvalidElementStateException) // Non user-editable elements (file inputs) - chrome/IE
        {
        }
        catch(InvalidOperationException)  // Non user-editable elements (file inputs) - firefox
        {
        }
        seleniumElement.SendKeys(value);
    }

    public void Select(Element element, String option)
    {
        optionSelector.Select(element, option);
    }

    public void AcceptModalDialog(DriverScope scope)
    {
        elementFinder.SeleniumScope(scope);
        dialogs.AcceptModalDialog();
    }

    public void CancelModalDialog(DriverScope scope)
    {
        elementFinder.SeleniumScope(scope);
        dialogs.CancelModalDialog();
    }

    public void Check(Element field)
    {
        var seleniumElement = SeleniumElement(field);

        if (!seleniumElement.Selected)
            seleniumElement.Click();
    }

    public void Uncheck(Element field)
    {
        var seleniumElement = SeleniumElement(field);

        if (seleniumElement.Selected)
            seleniumElement.Click();
    }

    public void Choose(Element field)
    {
        SeleniumElement(field).Click();
    }

    public String ExecuteScript(String javascript, DriverScope scope)
    {
        elementFinder.SeleniumScope(scope);
        var result = selenium.ExecuteScript(javascript);
        return result == null ? null : result.ToString();
    }

    private String NormalizeCRLFBetweenBrowserImplementations(String text)
    {
        if (selenium is ChromeDriver) // Which adds extra whitespace around CRLF
            text = StripWhitespaceAroundCRLFs(text);

        return Regex.Replace(text, "(\r\n)+", "\r\n");
    }

    private String StripWhitespaceAroundCRLFs(String pageText)
    {
        return Regex.Replace(pageText, @"\s*\r\n\s*", "\r\n");
    }

    private WebElement SeleniumElement(Element element)
    {
        return ((WebElement) element.Native);
    }

    public void Dispose()
    {
        if (selenium == null)
            return;

        AcceptAnyAlert();

        selenium.Quit();
        selenium = null;
        Disposed = true;
    }

    private void AcceptAnyAlert()
    {
        try
        {
            selenium.SwitchTo().Alert().Accept();
        }
        catch (WebDriverException){}
        catch (KeyNotFoundException){} // Chrome
        catch (InvalidOperationException){}
    }
}