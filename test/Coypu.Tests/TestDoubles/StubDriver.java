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

    public void Dispose()
    {
    }

    public ElementFound FindButton(String locator,DriverScope scope)
    {
        return null;
    }

    public ElementFound FindLink(String linkText, DriverScope scope)
    {
        return null;
    }

    public ElementFound FindField(String locator, DriverScope scope)
    {
        return null;
    }

    public void Click(Element element)
    {
    }

    public void Visit(String url)
    {
    }

    public void Set(Element element, String value)
    {
    }

    public void Select(Element element, String option)
    {
    }

    public Object Native()
    {
        return "Native driver on stub driver";
    }

    public boolean HasContent(String text, DriverScope scope)
    {
        return false;
    }

    public boolean HasContentMatch(Pattern pattern, DriverScope scope)
    {
        return false;
    }

    public boolean HasCss(String cssSelector, DriverScope scope)
    {
        return false;
    }

    public boolean HasXPath(String xpath, DriverScope scope)
    {
        return false;
    }

    public boolean HasDialog(String withText, DriverScope scope)
    {
        return false;
    }

    public ElementFound FindCss(String cssSelector, DriverScope scope)
    {
        return null;
    }

    public ElementFound FindXPath(String xpath, DriverScope scope)
    {
        return null;
    }

    public List<ElementFound> FindAllCss(String cssSelector, DriverScope scope)
    {
        return new ArrayList<ElementFound>();
    }

    public List<ElementFound> FindAllXPath(String xpath, DriverScope scope)
    {
        return new ArrayList<ElementFound>();
    }

    public void Check(Element field)
    {
        
    }

    public void Uncheck(Element field)
    {
        
    }

    public void Choose(Element field)
    {
        
    }

    public boolean Disposed()
    {
        return false;
    }

    public String Location()
    {
        return null;
    }

    public ElementFound Window()
    {
        return null;
    }

    public void AcceptModalDialog(DriverScope scope)
    {
    }

    public void CancelModalDialog(DriverScope scope)
    {
    }

    public void SetScope(ElementFound findScope)
    {
        
    }

    public void ClearScope()
    {
        
    }

    public String ExecuteScript(String javascript, DriverScope scope)
    {
        return null;
    }

    public ElementFound FindFieldset(String locator, DriverScope scope)
    {
        return null;
    }

    public ElementFound FindSection(String locator, DriverScope scope)
    {
        return null;
    }

    public ElementFound FindId(String id, DriverScope scope)
    {
        return null;
    }

    public ElementFound FindIFrame(String locator, DriverScope scope)
    {
        return null;
    }

    public void Hover(Element element)
    {   
    }

//    public List<Cookie> GetBrowserCookies()
//    {
//        return new List<Cookie>();
//    }

    public ElementFound FindWindow(String locator, DriverScope scope)
    {
        return null;
    }

//    public void SetBrowserCookies(Cookie cookie)
//    {
//    }
}
