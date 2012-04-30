package coypu.unitTests.TestDoubles;

import coypu.Driver;
import coypu.Drivers.Browser;
import coypu.Element;
import coypu.ElementFound;
import coypu.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StubDriver implements Driver
{
    public StubDriver() {}

    public StubDriver(Browser browser){}

    public void dispose()
    {
    }

    public ElementFound findButton(String locator,Scope scope)
    {
        return null;
    }

    public ElementFound findLink(String linkText, Scope scope)
    {
        return null;
    }

    public ElementFound findField(String locator, Scope scope)
    {
        return null;
    }

    public void click(Element element)
    {
    }

    public void visit(String url)
    {
    }

    public void set(Element element, String value, boolean forceAllEvents)
    {
    }

    public void select(Element element, String option)
    {
    }

    public Object getNative()
    {
        return "Native driver on stub driver";
    }

    public boolean hasContent(String text, Scope scope)
    {
        return false;
    }

    public boolean hasContentMatch(Pattern pattern, Scope scope)
    {
        return false;
    }

    public boolean hasCss(String cssSelector, Scope scope)
    {
        return false;
    }

    public boolean hasXPath(String xpath, Scope scope)
    {
        return false;
    }

    public boolean hasDialog(String withText, Scope scope)
    {
        return false;
    }

    public ElementFound findCss(String cssSelector, Scope scope)
    {
        return null;
    }

    public ElementFound findXPath(String xpath, Scope scope)
    {
        return null;
    }

    public List<ElementFound> findAllCss(String cssSelector, Scope scope)
    {
        return new ArrayList<ElementFound>();
    }

    public List<ElementFound> findAllXPath(String xpath, Scope scope)
    {
        return new ArrayList<ElementFound>();
    }

    public void check(Element field)
    {
        
    }

    public void uncheck(Element field)
    {
        
    }

    public void choose(Element field)
    {
        
    }

    public boolean disposed()
    {
        return false;
    }

    public String getLocation(Scope scope)
    {
        return null;
    }

    public ElementFound window()
    {
        return null;
    }

    public void acceptModalDialog(Scope scope)
    {
    }

    public void cancelModalDialog(Scope scope)
    {
    }

    public void setScope(ElementFound findScope)
    {
        
    }

    public void clearScope()
    {
        
    }

    public String executeScript(String javascript, Scope scope)
    {
        return null;
    }

    public ElementFound findFieldset(String locator, Scope scope)
    {
        return null;
    }

    public ElementFound findSection(String locator, Scope scope)
    {
        return null;
    }

    public ElementFound findId(String id, Scope scope)
    {
        return null;
    }

    public ElementFound findFrame(String locator, Scope scope)
    {
        return null;
    }

    public void hover(Element element)
    {   
    }

//    public List<Cookie> getBrowserCookies()
//    {
//        return new List<Cookie>();
//    }

    public ElementFound findWindow(String locator, Scope scope)
    {
        return null;
    }

    @Override
    public String getTitle(Scope scope) {
        return null;
    }

//    public void setBrowserCookies(Cookie cookie)
//    {
//    }
}


