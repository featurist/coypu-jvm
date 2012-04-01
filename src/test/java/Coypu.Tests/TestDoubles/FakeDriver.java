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


    public Coypu.Drivers.Browser browser() {
        return browser;
    }

    public ElementFound findButton(String locator, DriverScope scope)
    {
        FindButtonRequests.add(locator);
        return this.find(stubbedButtons,locator,scope, ElementFound.class);
    }

    private <T> T find(List<ScopedStubResult> stubbed, Object locator, DriverScope scope, Class<T> type)
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

    public ElementFound findLink(String linkText, DriverScope scope)
    {
        FindLinkRequests.add(linkText);

        return find(stubbedLinks, linkText, scope, ElementFound.class);
    }

    public ElementFound findField(String locator, DriverScope scope)
    {
        return find(stubbedTextFields, locator, scope, ElementFound.class);
    }

    public void click(Element element)
    {
        ClickedElements.add(element);
    }

    public void hover(Element element)
    {
        HoveredElements.add(element);
    }

//    public List<Cookie> getBrowserCookies()
//    {
//        return stubbedCookies;
//    }

//    public void setBrowserCookies(Cookie cookie)
//    {
//    }

    public void visit(String url)
    {
        Visits.add(url);
    }

    public void stubButton(String locator, Element element, DriverScope scope)
    {
        stubbedButtons.add(new ScopedStubResult(locator, element, scope));
    }

    public void stubLink(String locator, Element element, DriverScope scope)
    {
        stubbedLinks.add(new ScopedStubResult(locator, element, scope));
    }

    public void stubField(String locator, Element element, DriverScope scope)
    {
        stubbedTextFields.add(new ScopedStubResult(locator, element, scope));
    }

    public void stubHasContent(String text, boolean result, DriverScope scope)
    {
        stubbedHasContentResults.add(new ScopedStubResult(text, result, scope));
    }

    public void stubHasContentMatch(Pattern pattern, boolean result, DriverScope scope)
    {
        stubbedHasContentMatchResults.add(new ScopedStubResult(pattern, result, scope));
    }

    public void stubHasCss(String cssSelector, boolean result, DriverScope scope)
    {
        stubbedHasCssResults.add(new ScopedStubResult(cssSelector, result, scope));
    }

    public void stubHasXPath(String xpath, boolean result, DriverScope scope)
    {
        stubbedHasXPathResults.add(new ScopedStubResult(xpath, result, scope));
    }

    public void stubDialog(String text, boolean result, DriverScope scope)
    {
        stubbedHasDialogResults.add(new ScopedStubResult(text, result, scope));
    }

    public void stubCss(String cssSelector, Element result, DriverScope scope)
    {
        stubbedCssResults.add(new ScopedStubResult(cssSelector, result, scope));
    }

    public void stubXPath(String cssSelector, Element result, DriverScope scope)
    {
        stubbedXPathResults.add(new ScopedStubResult(cssSelector, result, scope));
    }

    public void stubAllCss(String cssSelector, List<ElementFound> result, DriverScope scope)
    {
        stubbedAllCssResults.add(new ScopedStubResult(cssSelector, result, scope));
    }

    public void stubAllXPath(String xpath, List<ElementFound> result, DriverScope scope)
    {
        stubbedAllXPathResults.add(new ScopedStubResult(xpath, result, scope));
    }

    public void dispose()
    {
        this.disposed = true;
    }

    public boolean disposed() {
        return disposed;
    }

    public String location()
    {
        return stubbedLocation;
    }

    public ElementFound window()
    {
        throw new NotImplementedException();
    }

    public void acceptModalDialog(DriverScope scope)
    {
        ModalDialogsAccepted.add(scope);
    }

    public void cancelModalDialog(DriverScope scope)
    {
        ModalDialogsCancelled.add(scope);
    }

    public String executeScript(String javascript, DriverScope scope)
    {
        return find(stubbedExecuteScriptResults, javascript, scope, String.class);
    }

    public ElementFound findFieldset(String locator, DriverScope scope)
    {
        return find(stubbedFieldsets, locator, scope, ElementFound.class);
    }

    public ElementFound findSection(String locator, DriverScope scope)
    {
        return find(stubbedSections, locator, scope, ElementFound.class);
    }

    public ElementFound findId(String id, DriverScope scope)
    {
        return find(stubbedIDs, id, scope, ElementFound.class);
    }

    public ElementFound findIFrame(String locator, DriverScope scope)
    {
        return find(stubbedIFrames, locator, scope, ElementFound.class);
    }

    public void set(Element element, String value)
    {
        SetFields.put(element, value);
    }

    public void select(Element element, String option)
    {
        SelectedOptions.put(element, option);
    }

    public Object getNative()
    {
        return "Native driver on fake driver";
    }

    public boolean hasContent(String text, DriverScope scope)
    {
        HasContentQueries.add(text);
        return find(stubbedHasContentResults, text, scope, boolean.class);
    }

    public boolean hasContentMatch(Pattern pattern, DriverScope scope)
    {
        HasContentMatchQueries.add(pattern);
        return find(stubbedHasContentMatchResults, pattern, scope, boolean.class);
    }

    public boolean hasCss(String cssSelector, DriverScope scope)
    {
        return find(stubbedHasCssResults, cssSelector, scope, boolean.class);
    }

    public boolean hasXPath(String xpath, DriverScope scope)
    {
        return find(stubbedHasXPathResults, xpath, scope, boolean.class);
    }

    public boolean hasDialog(String withText, DriverScope scope)
    {
        return find(stubbedHasDialogResults, withText, scope, boolean.class);
    }

    public ElementFound findCss(String cssSelector, DriverScope scope)
    {
        FindCssRequests.add(cssSelector);
        return find(stubbedCssResults, cssSelector, scope, ElementFound.class);
    }

    public ElementFound findXPath(String xpath, DriverScope scope)
    {
        return find(stubbedXPathResults, xpath, scope, ElementFound.class);
    }

    public List<ElementFound> findAllCss(String cssSelector, DriverScope scope)
    {
        return find(stubbedAllCssResults, cssSelector, scope, new ArrayList<Element>().getClass());
    }

    public List<ElementFound> findAllXPath(String xpath, DriverScope scope)
    {
        return find(stubbedAllXPathResults, xpath, scope, new ArrayList<Element>().getClass());
    }

    public void check(Element field)
    {
        CheckedElements.add(field);
    }

    public void uncheck(Element field)
    {
        UncheckedElements.add(field);
    }

    public void choose(Element field)
    {
        ChosenElements.add(field);
    }

    public void stubExecuteScript(String script, String scriptReturnValue, DriverScope scope)
    {
        stubbedExecuteScriptResults.add(new ScopedStubResult(script, scriptReturnValue, scope));
    }

    public void stubFieldset(String locator, Element fieldset, DriverScope scope)
    {
        stubbedFieldsets.add(new ScopedStubResult(locator, fieldset, scope));
    }

    public void stubSection(String locator, Element section, DriverScope scope)
    {
        stubbedSections.add(new ScopedStubResult(locator, section, scope));
    }

    public void stubIFrame(String locator, Element iframe, DriverScope scope)
    {
        stubbedIFrames.add(new ScopedStubResult(locator, iframe,scope));
    }

    public void stubId(String id, Element element, DriverScope scope)
    {
        stubbedIDs.add(new ScopedStubResult(id, element, scope));
    }

//    public void stubCookies(List<Cookie> cookies)
//    {
//        stubbedCookies = cookies;
//    }

    public void stubLocation(String location)
    {
        stubbedLocation = location;
    }

    public void stubWindow(String locator, Element window, DriverScope scope)
    {
        stubbedWindows.add(new ScopedStubResult(locator, window, scope));
    }

    public ElementFound findWindow(String locator, DriverScope scope)
    {
        return find(stubbedWindows, locator, scope, ElementFound.class);
    }
}
