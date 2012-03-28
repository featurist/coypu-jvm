package Coypu;

import java.util.List;
import java.util.regex.Pattern;

public interface Driver
{
    void Dispose();
    ElementFound FindButton(String locator, DriverScope scope) ;
    ElementFound FindLink(String linkText, DriverScope scope) ;
    ElementFound FindField(String locator, DriverScope scope) ;
    public void Click(Element element);
    void Visit(String url);
    void Set(Element element, String value);
    void Select(Element element, String option) ;
    Object Native();
    boolean HasContent(String text, DriverScope scope) ;
    boolean HasContentMatch(Pattern pattern, DriverScope scope) ;
    boolean HasCss(String cssSelector, DriverScope scope) ;
    boolean HasXPath(String xpath, DriverScope scope) ;
    boolean HasDialog(String withText, DriverScope scope) ;
    ElementFound FindCss(String cssSelector, DriverScope scope) ;
    ElementFound FindXPath(String xpath, DriverScope scope) ;
    List<ElementFound> FindAllCss(String cssSelector, DriverScope scope) ;
    List<ElementFound> FindAllXPath(String xpath, DriverScope scope) ;
    void Check(Element field);
    void Uncheck(Element field);
    void Choose(Element field);
    boolean Disposed();
    String Location();
    ElementFound Window();
    void AcceptModalDialog(DriverScope scope) ;
    void CancelModalDialog(DriverScope scope) ;
    String ExecuteScript(String javascript, DriverScope scope) ;
    ElementFound FindFieldset(String locator, DriverScope scope) ;
    ElementFound FindSection(String locator, DriverScope scope) ;
    ElementFound FindId(String id, DriverScope scope) ;
    ElementFound FindIFrame(String locator, DriverScope scope) ;
    void Hover(Element element);
    //Iterable<Cookie> GetBrowserCookies();
    ElementFound FindWindow(String locator, DriverScope scope) ;
}
