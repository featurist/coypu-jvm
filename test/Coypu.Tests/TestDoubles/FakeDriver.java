package Coypu.Tests.TestDoubles;

import Coypu.*;
import Coypu.Drivers.Browser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

public class FakeDriver implements Driver 
{
    public final List<Element> ClickedElements = new ArrayList<Element>();
    public final List<Element> HoveredElements = new ArrayList<Element>();
    public final List<Element> CheckedElements = new ArrayList<Element>();
    public final List<Element> UncheckedElements = new ArrayList<Element>();
    public final List<Element> ChosenElements = new ArrayList<Element>();
    public final List<String> HasContentQueries = new ArrayList<String>();
    public final List<Pattern> HasContentMatchQueries = new ArrayList<Pattern>();
    public final List<String> HasCssQueries = new ArrayList<String>();
    public final List<String> HasXPathQueries = new ArrayList<String>();
    public final List<String> Visits = new ArrayList<String>();
    public final Dictionary<Element, String> SetFields = new Hashtable<Element, String>();
    public final Dictionary<Element, String> SelectedOptions = new Hashtable<Element, String>();
    private final List<ScopedStubResult> stubbedButtons = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedLinks = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedTextFields = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedCssResults = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedXPathResults = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedAllCssResults = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedAllXPathResults = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedExecuteScriptResults = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedFieldsets = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedSections = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedIFrames = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedIDs = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedHasContentResults = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedHasContentMatchResults = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedHasCssResults = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedHasXPathResults = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedHasDialogResults = new ArrayList<ScopedStubResult>();
    private final List<ScopedStubResult> stubbedWindows = new ArrayList<ScopedStubResult>();
    public final List<String> FindButtonRequests = new ArrayList<String>();
    public final List<String> FindLinkRequests = new ArrayList<String>();
    public final List<String> FindCssRequests = new ArrayList<String>();
    //private List<Cookie> stubbedCookies;
    private String stubbedLocation;

    public List<DriverScope> ModalDialogsAccepted = new ArrayList<DriverScope>();
    public List<DriverScope> ModalDialogsCancelled = new ArrayList<DriverScope>();
    private Browser browser;
    private boolean disposed;


    public FakeDriver() throws NotImplementedException {}
    public FakeDriver(Coypu.Drivers.Browser browser)
    {
        this.browser = browser;
    }

    
    class ScopedStubResult
    {
        ScopedStubResult(Object locator, Object result, DriverScope scope) {
            this.locator = locator;
            this.result = result;
            this.scope = scope;
        }
        public Object locator;
        public Object result;
        public DriverScope scope;
    }


    public Coypu.Drivers.Browser Browser() {
        return browser;
    }

    public ElementFound FindButton(String locator, DriverScope scope)
    {
        FindButtonRequests.add(locator);
        return this.Find(stubbedButtons,locator,scope, ElementFound.class);
    }

    private <T> T Find(Iterable<ScopedStubResult> stubbed, Object locator, DriverScope scope, Class<T> type)
    {
        ScopedStubResult scopedStubResult = null;
        for (ScopedStubResult result : stubbed) {
            if (result.locator == locator && result.scope == scope) {
                scopedStubResult = result;
                break;
            }
        }

        if (scopedStubResult == null)
        {
            throw new MissingHtmlException("Element not found: " + locator);
        }
        return (T) scopedStubResult.result;
    }

    public ElementFound FindLink(String linkText, DriverScope scope)
    {
        FindLinkRequests.add(linkText);

        return Find(stubbedLinks, linkText, scope, ElementFound.class);
    }

    public ElementFound FindField(String locator, DriverScope scope)
    {
        return Find(stubbedTextFields, locator, scope, ElementFound.class);
    }

    public void Click(Element element)
    {
        ClickedElements.add(element);
    }

    public void Hover(Element element)
    {
        HoveredElements.add(element);
    }

//    public Iterable<Cookie> GetBrowserCookies()
//    {
//        return stubbedCookies;
//    }

//    public void SetBrowserCookies(Cookie cookie)
//    {
//    }

    public void Visit(String url)
    {
        Visits.add(url);
    }

    public void StubButton(String locator, ElementFound element, DriverScope scope)
    {
        stubbedButtons.add(new ScopedStubResult(locator, element, scope));
    }

    public void StubLink(String locator, ElementFound element, DriverScope scope)
    {
        stubbedLinks.add(new ScopedStubResult(locator, element, scope));
    }

    public void StubField(String locator, ElementFound element, DriverScope scope)
    {
        stubbedTextFields.add(new ScopedStubResult(locator, element, scope));
    }

    public void StubHasContent(String text, boolean result, DriverScope scope)
    {
        stubbedHasContentResults.add(new ScopedStubResult(text, result, scope));
    }

    public void StubHasContentMatch(Pattern pattern, boolean result, DriverScope scope)
    {
        stubbedHasContentMatchResults.add(new ScopedStubResult(pattern, result, scope));
    }

    public void StubHasCss(String cssSelector, boolean result, DriverScope scope)
    {
        stubbedHasCssResults.add(new ScopedStubResult(cssSelector, result, scope));
    }

    public void StubHasXPath(String xpath, boolean result, DriverScope scope)
    {
        stubbedHasXPathResults.add(new ScopedStubResult(xpath, result, scope));
    }

    public void StubDialog(String text, boolean result, DriverScope scope)
    {
        stubbedHasDialogResults.add(new ScopedStubResult(text, result, scope));
    }

    public void StubCss(String cssSelector, ElementFound result, DriverScope scope)
    {
        stubbedCssResults.add(new ScopedStubResult(cssSelector, result, scope));
    }

    public void StubXPath(String cssSelector, ElementFound result, DriverScope scope)
    {
        stubbedXPathResults.add(new ScopedStubResult(cssSelector, result, scope));
    }

    public void StubAllCss(String cssSelector, Iterable<ElementFound> result, DriverScope scope)
    {
        stubbedAllCssResults.add(new ScopedStubResult(cssSelector, result, scope));
    }

    public void StubAllXPath(String xpath, Iterable<ElementFound> result, DriverScope scope)
    {
        stubbedAllXPathResults.add(new ScopedStubResult(xpath, result, scope));
    }

    public void Dispose()
    {
        this.disposed = true;
    }

    public boolean Disposed() {
        return disposed;
    }

    public String Location()
    {
        return stubbedLocation;
    }

    public ElementFound Window()
    {
        throw new NotImplementedException();
    }

    public void AcceptModalDialog(DriverScope scope)
    {
        ModalDialogsAccepted.add(scope);
    }

    public void CancelModalDialog(DriverScope scope)
    {
        ModalDialogsCancelled.add(scope);
    }

    public String ExecuteScript(String javascript, DriverScope scope)
    {
        return Find(stubbedExecuteScriptResults, javascript, scope, String.class);
    }

    public ElementFound FindFieldset(String locator, DriverScope scope)
    {
        return Find(stubbedFieldsets, locator, scope, ElementFound.class);
    }

    public ElementFound FindSection(String locator, DriverScope scope)
    {
        return Find(stubbedSections, locator, scope, ElementFound.class);
    }

    public ElementFound FindId(String id, DriverScope scope)
    {
        return Find(stubbedIDs, id, scope, ElementFound.class);
    }

    public ElementFound FindIFrame(String locator, DriverScope scope)
    {
        return Find(stubbedIFrames, locator, scope, ElementFound.class);
    }

    public void Set(Element element, String value)
    {
        SetFields.put(element, value);
    }

    public void Select(Element element, String option)
    {
        SelectedOptions.put(element, option);
    }

    public Object Native()
    {
        return "Native driver on fake driver";
    }

    public boolean HasContent(String text, DriverScope scope)
    {
        HasContentQueries.add(text);
        return Find(stubbedHasContentResults, text, scope, boolean.class);
    }

    public boolean HasContentMatch(Pattern pattern, DriverScope scope)
    {
        HasContentMatchQueries.add(pattern);
        return Find(stubbedHasContentMatchResults, pattern, scope, boolean.class);
    }

    public boolean HasCss(String cssSelector, DriverScope scope)
    {
        return Find(stubbedHasCssResults, cssSelector, scope, boolean.class);
    }

    public boolean HasXPath(String xpath, DriverScope scope)
    {
        return Find(stubbedHasXPathResults, xpath, scope, boolean.class);
    }

    public boolean HasDialog(String withText, DriverScope scope)
    {
        return Find(stubbedHasDialogResults, withText, scope, boolean.class);
    }

    public ElementFound FindCss(String cssSelector, DriverScope scope)
    {
        FindCssRequests.add(cssSelector);
        return Find(stubbedCssResults, cssSelector, scope, ElementFound.class);
    }

    public ElementFound FindXPath(String xpath, DriverScope scope)
    {
        return Find(stubbedXPathResults, xpath, scope, ElementFound.class);
    }

    public List<ElementFound> FindAllCss(String cssSelector, DriverScope scope)
    {
        return Find(stubbedAllCssResults, cssSelector, scope, new ArrayList<ElementFound>().getClass());
    }

    public List<ElementFound> FindAllXPath(String xpath, DriverScope scope)
    {
        return Find(stubbedAllXPathResults, xpath, scope, new ArrayList<ElementFound>().getClass());
    }

    public void Check(Element field)
    {
        CheckedElements.add(field);
    }

    public void Uncheck(Element field)
    {
        UncheckedElements.add(field);
    }

    public void Choose(Element field)
    {
        ChosenElements.add(field);
    }

    public void StubExecuteScript(String script, String scriptReturnValue, DriverScope scope)
    {
        stubbedExecuteScriptResults.add(new ScopedStubResult(script, scriptReturnValue, scope));
    }

    public void StubFieldset(String locator, ElementFound fieldset, DriverScope scope)
    {
        stubbedFieldsets.add(new ScopedStubResult(locator, fieldset, scope));
    }

    public void StubSection(String locator, ElementFound section, DriverScope scope)
    {
        stubbedSections.add(new ScopedStubResult(locator, section, scope));
    }

    public void StubIFrame(String locator, ElementFound iframe, DriverScope scope)
    {
        stubbedIFrames.add(new ScopedStubResult(locator, iframe,scope));
    }

    public void StubId(String id, ElementFound element, DriverScope scope)
    {
        stubbedIDs.add(new ScopedStubResult(id, element, scope));
    }

//    public void StubCookies(List<Cookie> cookies)
//    {
//        stubbedCookies = cookies;
//    }

    public void StubLocation(String location)
    {
        stubbedLocation = location;
    }

    public void StubWindow(String locator, ElementFound window, DriverScope scope)
    {
        stubbedWindows.add(new ScopedStubResult(locator, window, scope));
    }

    public ElementFound FindWindow(String locator, DriverScope scope)
    {
        return Find(stubbedWindows, locator, scope, ElementFound.class);
    }
}
