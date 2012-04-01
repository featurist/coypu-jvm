package Coypu.Tests.TestDoubles;

import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.Drivers.Browser;
import Coypu.Element;
import Coypu.ElementFound;

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

    public ElementFound findButton(String locator,DriverScope scope)
    {
        return null;
    }

    public ElementFound findLink(String linkText, DriverScope scope)
    {
        return null;
    }

    public ElementFound findField(String locator, DriverScope scope)
    {
        return null;
    }

    public void click(Element element)
    {
    }

    public void visit(String url)
    {
    }

    public void set(Element element, String value)
    {
    }

    public void select(Element element, String option)
    {
    }

    public Object getNative()
    {
        return "Native driver on stub driver";
    }

    public boolean hasContent(String text, DriverScope scope)
    {
        return false;
    }

    public boolean hasContentMatch(Pattern pattern, DriverScope scope)
    {
        return false;
    }

    public boolean hasCss(String cssSelector, DriverScope scope)
    {
        return false;
    }

    public boolean hasXPath(String xpath, DriverScope scope)
    {
        return false;
    }

    public boolean hasDialog(String withText, DriverScope scope)
    {
        return false;
    }

    public ElementFound findCss(String cssSelector, DriverScope scope)
    {
        return null;
    }

    public ElementFound findXPath(String xpath, DriverScope scope)
    {
        return null;
    }

    public List<ElementFound> findAllCss(String cssSelector, DriverScope scope)
    {
        return new ArrayList<ElementFound>();
    }

    public List<ElementFound> findAllXPath(String xpath, DriverScope scope)
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

    public String location()
    {
        return null;
    }

    public ElementFound window()
    {
        return null;
    }

    public void acceptModalDialog(DriverScope scope)
    {
    }

    public void cancelModalDialog(DriverScope scope)
    {
    }

    public void setScope(ElementFound findScope)
    {
        
    }

    public void clearScope()
    {
        
    }

    public String executeScript(String javascript, DriverScope scope)
    {
        return null;
    }

    public ElementFound findFieldset(String locator, DriverScope scope)
    {
        return null;
    }

    public ElementFound findSection(String locator, DriverScope scope)
    {
        return null;
    }

    public ElementFound findId(String id, DriverScope scope)
    {
        return null;
    }

    public ElementFound findIFrame(String locator, DriverScope scope)
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

    public ElementFound findWindow(String locator, DriverScope scope)
    {
        return null;
    }

//    public void setBrowserCookies(Cookie cookie)
//    {
//    }
}


