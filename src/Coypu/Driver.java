package Coypu;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Pattern;

public interface Driver
{
    void Dispose();
    ElementFound FindButton(String locator, DriverScope scope) throws MissingHtmlException;
    ElementFound FindLink(String linkText, DriverScope scope) throws MissingHtmlException;
    ElementFound FindField(String locator, DriverScope scope) throws MissingHtmlException;
    public void Click(Element element);
    void Visit(String url);
    void Set(Element element, String value);
    void Select(Element element, String option) throws MissingHtmlException;
    Object Native();
    boolean HasContent(String text, DriverScope scope) throws MissingHtmlException;
    boolean HasContentMatch(Pattern pattern, DriverScope scope) throws MissingHtmlException;
    boolean HasCss(String cssSelector, DriverScope scope) throws MissingHtmlException;
    boolean HasXPath(String xpath, DriverScope scope) throws MissingHtmlException;
    boolean HasDialog(String withText, DriverScope scope) throws MissingHtmlException;
    ElementFound FindCss(String cssSelector, DriverScope scope) throws MissingHtmlException;
    ElementFound FindXPath(String xpath, DriverScope scope) throws MissingHtmlException;
    List<ElementFound> FindAllCss(String cssSelector, DriverScope scope) throws MissingHtmlException;
    List<ElementFound> FindAllXPath(String xpath, DriverScope scope) throws MissingHtmlException;
    void Check(Element field);
    void Uncheck(Element field);
    void Choose(Element field);
    boolean Disposed();
    String Location();
    ElementFound Window();
    void AcceptModalDialog(DriverScope scope) throws MissingHtmlException;
    void CancelModalDialog(DriverScope scope) throws MissingHtmlException;
    String ExecuteScript(String javascript, DriverScope scope) throws MissingHtmlException;
    ElementFound FindFieldset(String locator, DriverScope scope) throws MissingHtmlException;
    ElementFound FindSection(String locator, DriverScope scope) throws MissingHtmlException;
    ElementFound FindId(String id, DriverScope scope) throws MissingHtmlException;
    ElementFound FindIFrame(String locator, DriverScope scope) throws MissingHtmlException;
    void Hover(Element element);
    //Iterable<Cookie> GetBrowserCookies();
    ElementFound FindWindow(String locator, DriverScope scope) throws MissingHtmlException;
}
