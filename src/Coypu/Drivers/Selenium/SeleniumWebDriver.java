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

public class SeleniumWebDriver implements Driver
{
    private Browser browser;
    private boolean disposed;

    public boolean Disposed() {
        return disposed;
    }

    public String Location() {
        return selenium.getCurrentUrl();
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

    public ElementFound FindField(String locator, DriverScope scope) throws MissingHtmlException {
        return BuildElement(fieldFinder.FindField(locator,scope), "No such field: " + locator);
    }

    public ElementFound FindButton(String locator, DriverScope scope) throws MissingHtmlException {
        return BuildElement(buttonFinder.FindButton(locator, scope), "No such button: " + locator);
    }

    public ElementFound FindIFrame(String locator, DriverScope scope) throws MissingHtmlException {
        WebElement element = iframeFinder.FindIFrame(locator, scope);

        if (element == null)
            throw new MissingHtmlException("Failed to find frame: " + locator);

        return new SeleniumFrame(element,selenium);
    }

    public ElementFound FindLink(String linkText, DriverScope scope) throws MissingHtmlException {
        return BuildElement(Iterators.FirstOrDefault(Find(By.linkText(linkText), scope), scope), "No such link: " + linkText);
    }

    public ElementFound FindId(String id,DriverScope scope ) throws MissingHtmlException {
        return BuildElement(Iterators.FirstOrDefault(Find(By.id(id), scope), scope), "Failed to find id: " + id);
    }

    public ElementFound FindFieldset(String locator, DriverScope scope) throws MissingHtmlException {
        
        WebElement fieldset = Iterators.FirstOrDefault(Find(By.xpath(xPath.Format(".//fieldset[legend[text() = %1$s]]", locator)),scope),scope);
        
        if (fieldset == null)
            fieldset = Iterators.FirstOrDefault(Find(By.id(locator),scope),tagNameMatches("fieldset"), scope);

        return BuildElement(fieldset, "Failed to find fieldset: " + locator);
    }
    
    public Predicate<WebElement> tagNameMatches(final String tagName){
        return new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return e.getTagName().equals(tagName);
            }
        };
    }

    public ElementFound FindSection(String locator, DriverScope scope) throws MissingHtmlException {
        return BuildElement(sectionFinder.FindSection(locator,scope), "Failed to find section: " + locator);
    }

    public ElementFound FindCss(String cssSelector,DriverScope scope) throws MissingHtmlException {
        return BuildElement(Iterators.FirstOrDefault(Find(By.cssSelector(cssSelector), scope), scope), "No element found by css: " + cssSelector);
    }

    public ElementFound FindXPath(String xpath, DriverScope scope) throws MissingHtmlException {
        return BuildElement(Iterators.FirstOrDefault(Find(By.xpath(xpath), scope), scope),"No element found by xpath: " + xpath);
    }

    public List<ElementFound> FindAllCss(String cssSelector, DriverScope scope) throws MissingHtmlException {
        ArrayList<WebElement> allVisible = Iterators.AllVisible(Find(By.cssSelector(cssSelector), scope), scope);

        return asElementsFound(allVisible);
    }

    public List<ElementFound> FindAllXPath(String xpath, DriverScope scope) throws MissingHtmlException {
        ArrayList<WebElement> allVisible = Iterators.AllVisible(Find(By.xpath(xpath), scope), scope);

        return asElementsFound(allVisible);
    }

    private List<ElementFound> asElementsFound(ArrayList<WebElement> allVisible) {
        ArrayList<ElementFound> elements = new ArrayList<ElementFound>();
        for (WebElement seleniumElement : allVisible) {
            elements.add(BuildElement(seleniumElement));
        }
        return elements;
    }

    private Iterable<WebElement> Find(By by, DriverScope scope) throws MissingHtmlException {
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

    public boolean HasContent(String text, DriverScope scope) throws MissingHtmlException {
        return GetContent(scope).contains(text);
    }

    public boolean HasContentMatch(Pattern pattern, DriverScope scope) throws MissingHtmlException {
        return pattern.matcher(GetContent(scope)).find();
    }

    private String GetContent(DriverScope scope) throws MissingHtmlException {
        SearchContext seleniumScope = elementFinder.SeleniumScope(scope);
        return seleniumScope instanceof RemoteWebDriver
                   ? GetText(By.cssSelector("body"), seleniumScope)
                   : GetText(By.xpath("."), seleniumScope);
    }

    private String GetText(By xpath, SearchContext seleniumScope)
    {   
        String pageText = seleniumScope.findElement(xpath).getText();
        return NormalizeCRLFBetweenBrowserImplementations(pageText);
    }

    public boolean HasCss(String cssSelector, DriverScope scope) throws MissingHtmlException {
        return Find(By.cssSelector(cssSelector), scope).iterator().hasNext();
    }

    public boolean HasXPath(String xpath, DriverScope scope) throws MissingHtmlException {
        return Find(By.xpath(xpath), scope).iterator().hasNext();
    }

    public boolean HasDialog(String withText, DriverScope scope) throws MissingHtmlException {
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

//    public Iterable<Cookie> GetBrowserCookies()
//    {
//        ArrayList<Cookie> elements = new ArrayList<Cookie>();
//        for (Cookie c : selenium.manage().getCookies()) {
//            elements.add(new Cookie(c.getName(), c.getValue(), c.getPath(), c.getDomain()));
//        }
//    }

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
            for (String windowHandle : selenium.getWindowHandles())
            {
                selenium.switchTo().window(windowHandle);
                if (windowHandle.equals(titleOrName) || selenium.getTitle().equals(titleOrName))
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
        catch (Exception ex) // was InvalidOperationException in c#
                
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
//        catch(InvalidOperationException ex)  // Non user-editable elements (file inputs) - firefox
//        {
//        }
        seleniumElement.sendKeys(value);
    }

    public void Select(Element element, String option) throws MissingHtmlException {
        optionSelector.Select(element, option);
    }

    public void AcceptModalDialog(DriverScope scope) throws MissingHtmlException {
        elementFinder.SeleniumScope(scope);
        dialogs.AcceptModalDialog();
    }

    public void CancelModalDialog(DriverScope scope) throws MissingHtmlException {
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

    public String ExecuteScript(String javascript, DriverScope scope) throws MissingHtmlException {
        elementFinder.SeleniumScope(scope);
        Object result = selenium.executeScript(javascript);
        return result == null ? null : result.toString();
    }

    private String NormalizeCRLFBetweenBrowserImplementations(String text)
    {
        if (selenium instanceof ChromeDriver) // Which adds extra whitespace around CRLF
            text = StripWhitespaceAroundCRLFs(text);

        return Pattern.compile("(\r\n)+").matcher(text).replaceAll("\r\n");
    }

    private String StripWhitespaceAroundCRLFs(String pageText)
    {
        return Pattern.compile("\\s*\r\n\\s*").matcher(pageText).replaceAll("\r\n");
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
//        catch (KeyNotFoundException){} // Chrome
//        catch (InvalidOperationException){}
    }
}
