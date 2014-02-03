//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:14
//

package coypu.Drivers.Selenium;

import coypu.Driver;
import coypu.Drivers.Browser;
import coypu.Drivers.Selenium.WindowHandleFinder;
import coypu.Drivers.XPath;
import coypu.Element;
import coypu.ElementFound;
import coypu.Options;
import coypu.Scope;
import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.InvalidOperationException;
import java.net.URI;
import java.util.regex.Pattern;
import org.openqa.selenium.*;

public class SeleniumWebDriver   implements Driver
{
    private boolean __Disposed;
    public boolean getDisposed() {
        return __Disposed;
    }

    public void setDisposed(boolean value) {
        __Disposed = value;
    }

    private IWebDriver webDriver = new IWebDriver();
    private final ElementFinder elementFinder;
    private final FrameFinder frameFinder;
    private final TextMatcher textMatcher;
    private final Dialogs dialogs;
    private final MouseControl mouseControl;
    private final XPath xPath;
    private final Browser browser;
    private final WindowHandleFinder windowHandleFinder;
    public SeleniumWebDriver(Browser browser) throws Exception {
        this((new DriverFactory()).NewWebDriver(browser), browser);
    }

    protected SeleniumWebDriver(IWebDriver webDriver, Browser browser) throws Exception {
        this.webDriver = webDriver;
        this.browser = browser;
        xPath = new XPath(browser.getUppercaseTagNames());
        elementFinder = new ElementFinder();
        frameFinder = new FrameFinder(this.webDriver,elementFinder,xPath);
        textMatcher = new TextMatcher();
        windowHandleFinder = new WindowHandleFinder(this.webDriver);
        dialogs = new Dialogs(this.webDriver);
        mouseControl = new MouseControl(this.webDriver);
    }

    public URI location(Scope scope) throws Exception {
        elementFinder.SeleniumScope(scope);
        return new URI(webDriver.Url);
    }

    public String title(Scope scope) throws Exception {
        elementFinder.SeleniumScope(scope);
        return webDriver.Title;
    }

    public ElementFound getWindow() throws Exception {
        return new SeleniumWindow(webDriver, webDriver.CurrentWindowHandle);
    }

    protected boolean getNoJavascript() throws Exception {
        return !browser.Javascript;
    }

    private IJavaScriptExecutor getJavaScriptExecutor() throws Exception {
        return webDriver instanceof IJavaScriptExecutor ? (IJavaScriptExecutor)webDriver : (IJavaScriptExecutor)null;
    }

    public Object getNative() throws Exception {
        return webDriver;
    }

    public IEnumerable<ElementFound> findFrames(String locator, Scope scope, Options options) throws Exception {
        return frameFinder.FindFrame(locator, scope, options).Select(BuildElement);
    }

    public IEnumerable<ElementFound> findAllCss(String cssSelector, Scope scope, Options options, Pattern textPattern) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ textMatches = (textPattern == null) ? (Func<IWebElement, Boolean>)null : (/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(e) => {
            return textMatcher.TextMatches(e, textPattern);
        }" */);
        if (textPattern != null && options.getConsiderInvisibleElements())
            throw new NotSupportedException("Cannot inspect the text of invisible elements.");
         
        return FindAll(By.CssSelector(cssSelector), scope, options, textMatches).Select(BuildElement);
    }

    public IEnumerable<ElementFound> findAllXPath(String xpath, Scope scope, Options options) throws Exception {
        return FindAll(By.XPath(xpath), scope, options).Select(BuildElement);
    }

    private IEnumerable<IWebElement> findAll(By by, Scope scope, Options options, Func<IWebElement, Boolean> predicate) throws Exception {
        return elementFinder.FindAll(by, scope, options, predicate);
    }

    private ElementFound buildElement(IWebElement element) throws Exception {
        return ().Contains(element.TagName.ToLower()) ? new SeleniumFrame(element,webDriver) : new SeleniumElement(element,webDriver);
    }

    public boolean hasDialog(String withText, Scope scope) throws Exception {
        elementFinder.SeleniumScope(scope);
        return dialogs.hasDialog(withText);
    }

    public void visit(String url, Scope scope) throws Exception {
        elementFinder.SeleniumScope(scope);
        webDriver.Navigate().GoToUrl(url);
    }

    public void click(Element element) throws Exception {
        SeleniumElement(element).Click();
    }

    public void hover(Element element) throws Exception {
        mouseControl.Hover(element);
    }

    public void sendKeys(Element element, String keys) throws Exception {
        SeleniumElement(element).SendKeys(keys);
    }

    public void maximiseWindow(Scope scope) throws Exception {
        elementFinder.SeleniumScope(scope);
        webDriver.Manage().Window.Maximize();
    }

    public void refresh(Scope scope) throws Exception {
        elementFinder.SeleniumScope(scope);
        webDriver.Navigate().Refresh();
    }

    public void resizeTo(Size size, Scope scope) throws Exception {
        elementFinder.SeleniumScope(scope);
        webDriver.Manage().Window.Size = size;
    }

    public void saveScreenshot(String fileName, Scope scope) throws Exception {
        elementFinder.SeleniumScope(scope);
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ format = ImageFormatParser.getImageFormat(fileName);
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ screenshot = ((ITakesScreenshot)webDriver).GetScreenshot();
        screenshot.SaveAsFile(fileName, format);
    }

    public void goBack(Scope scope) throws Exception {
        elementFinder.SeleniumScope(scope);
        webDriver.Navigate().Back();
    }

    public void goForward(Scope scope) throws Exception {
        elementFinder.SeleniumScope(scope);
        webDriver.Navigate().Forward();
    }

    public IEnumerable<Cookie> getBrowserCookies() throws Exception {
        return webDriver.Manage().Cookies.AllCookies.Select(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(c) => {
            return new Cookie(c.Name, c.Value, c.Path, c.Domain);
        }" */);
    }

    public IEnumerable<ElementFound> findWindows(String titleOrName, Scope scope, Options options) throws Exception {
        elementFinder.SeleniumScope(scope);
        return windowHandleFinder.FindWindowHandles(titleOrName, options).Select(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(h) => {
            return new SeleniumWindow(webDriver, h);
        }" */).<ElementFound>Cast();
    }

    public void set(Element element, String value) throws Exception {
        try
        {
            SeleniumElement(element).Clear();
        }
        catch (InvalidElementStateException __dummyCatchVar0)
        {
        }
        catch (InvalidOperationException __dummyCatchVar1)
        {
        }

        // Non user-editable elements (file inputs) - chrome/IE
        // Non user-editable elements (file inputs) - firefox
        SendKeys(element, value);
    }

    public void acceptModalDialog(Scope scope) throws Exception {
        elementFinder.SeleniumScope(scope);
        dialogs.acceptModalDialog();
    }

    public void cancelModalDialog(Scope scope) throws Exception {
        elementFinder.SeleniumScope(scope);
        dialogs.cancelModalDialog();
    }

    public void check(Element field) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ seleniumElement = SeleniumElement(field);
        if (!seleniumElement.Selected)
            seleniumElement.Click();
         
    }

    public void uncheck(Element field) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ seleniumElement = SeleniumElement(field);
        if (seleniumElement.Selected)
            seleniumElement.Click();
         
    }

    public void choose(Element field) throws Exception {
        SeleniumElement(field).Click();
    }

    public String executeScript(String javascript, Scope scope) throws Exception {
        if (getNoJavascript())
            throw new NotSupportedException("Javascript is not supported by " + browser);
         
        elementFinder.SeleniumScope(scope);
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ result = getJavaScriptExecutor().ExecuteScript(javascript);
        return result == null ? null : result.ToString();
    }

    private String normalizeCRLFBetweenBrowserImplementations(String text) throws Exception {
        if (webDriver instanceof ChromeDriver)
            // Which adds extra whitespace around CRLF
            text = stripWhitespaceAroundCRLFs(text);
         
        return Pattern.compile("(\r\n)+").matcher(text).replaceAll("\r\n");
    }

    private String stripWhitespaceAroundCRLFs(String pageText) throws Exception {
        return Pattern.compile("\\s*\\r\\n\\s*").matcher(pageText).replaceAll("\r\n");
    }

    private IWebElement seleniumElement(Element element) throws Exception {
        return ((IWebElement)element.getNative());
    }

    public void dispose() throws Exception {
        if (webDriver == null)
            return ;
         
        acceptAnyAlert();
        webDriver.Quit();
        webDriver = null;
        setDisposed(true);
    }

    private void acceptAnyAlert() throws Exception {
        try
        {
            webDriver.SwitchTo().Alert().Accept();
        }
        catch (WebDriverException __dummyCatchVar2)
        {
        }
        catch (KeyNotFoundException __dummyCatchVar3)
        {
        }
        catch (InvalidOperationException __dummyCatchVar4)
        {
        }
    
    }

}


// Chrome