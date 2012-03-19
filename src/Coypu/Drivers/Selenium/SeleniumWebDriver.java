package Coypu.Drivers.Selenium;

import Coypu.*;
import Coypu.Drivers.Browser;
import Coypu.Drivers.XPath;
import com.sun.jndi.toolkit.url.Uri;

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
        return new WindowHandle(selenium, selenium.CurrentWindowHandle);
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

    public SeleniumWebDriver(Browser browser)
    {
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
        IWebElement element = iframeFinder.FindIFrame(locator, scope);

        if (element == null)
            throw new MissingHtmlException("Failed to find frame: " + locator);

        return new SeleniumFrame(element,selenium);
    }

    public ElementFound FindLink(String linkText, DriverScope scope)
    {
        return BuildElement(Find(By.LinkText(linkText), scope).FirstOrDefault(), "No such link: " + linkText);
    }

    public ElementFound FindId(String id,DriverScope scope ) 
    {
        return BuildElement(Find(By.Id(id), scope).FirstDisplayedOrDefault(), "Failed to find id: " + id);
    }

    public ElementFound FindFieldset(String locator, DriverScope scope)
    {
        IWebElement fieldset =
            Find(By.XPath(xPath.Format(".//fieldset[legend[text() = {0}]]", locator)),scope).FirstOrDefault() ??
            Find(By.Id(locator),scope).FirstOrDefault(e => e.TagName == "fieldset");

        return BuildElement(fieldset, "Failed to find fieldset: " + locator);
    }

    public ElementFound FindSection(String locator, DriverScope scope)
    {
        return BuildElement(sectionFinder.FindSection(locator,scope), "Failed to find section: " + locator);
    }

    public ElementFound FindCss(String cssSelector,DriverScope scope)
    {
        return BuildElement(Find(By.CssSelector(cssSelector),scope).FirstOrDefault(),"No element found by css: " + cssSelector);
    }

    public ElementFound FindXPath(String xpath, DriverScope scope)
    {
        return BuildElement(Find(By.XPath(xpath),scope).FirstOrDefault(),"No element found by xpath: " + xpath);
    }

    public Enumerable<ElementFound> FindAllCss(String cssSelector, DriverScope scope)
    {
        return Find(By.CssSelector(cssSelector),scope).Select(e => BuildElement(e)).Cast<ElementFound>();
    }

    public Enumerable<ElementFound> FindAllXPath(String xpath, DriverScope scope)
    {
        return Find(By.XPath(xpath), scope).Select(e => BuildElement(e)).Cast<ElementFound>();
    }

    private Enumerable<IWebElement> Find(By by, DriverScope scope)
    {
        return elementFinder.Find(by, scope);
    }

    private ElementFound BuildElement(IWebElement element, String failureMessage)
    {
        if (element == null)
            throw new MissingHtmlException(failureMessage);

        return BuildElement(element);
    }

    private SeleniumElement BuildElement(IWebElement element)
    {
        return new SeleniumElement(element);
    }

    public boolean HasContent(String text, DriverScope scope)
    {
        return GetContent(scope).Contains(text);
    }

    public boolean HasContentMatch(Regex pattern, DriverScope scope)
    {
        return pattern.IsMatch(GetContent(scope));
    }

    private String GetContent(DriverScope scope)
    {
        var seleniumScope = elementFinder.SeleniumScope(scope);
        return seleniumScope is RemoteWebDriver
                   ? GetText(By.CssSelector("body"), seleniumScope)
                   : GetText(By.XPath("."), seleniumScope);
    }

    private String GetText(By xpath, ISearchContext seleniumScope)
    {   
        var pageText = seleniumScope.FindElement(xpath).Text;
        return NormalizeCRLFBetweenBrowserImplementations(pageText);
    }

    public boolean HasCss(String cssSelector, DriverScope scope)
    {
        return Find(By.CssSelector(cssSelector), scope).Any();
    }

    public boolean HasXPath(String xpath, DriverScope scope)
    {
        return Find(By.XPath(xpath), scope).Any();
    }

    public boolean HasDialog(String withText, DriverScope scope)
    {
        elementFinder.SeleniumScope(scope);
        return dialogs.HasDialog(withText);
    }

    public void Visit(String url) 
    {
        selenium.Navigate().GoToUrl(url);
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

    private IWebElement SeleniumElement(Element element)
    {
        return ((IWebElement) element.Native);
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