package Coypu;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public interface Driver
{
    void Dispose();
    ElementFound FindButton(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException;
    ElementFound FindLink(String linkText, DriverScope scope) throws MissingHtmlException, TimeoutException;
    ElementFound FindField(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException;
    public void Click(Element element);
    void Visit(String url);
    void Set(Element element, String value);
    void Select(Element element, String option) throws MissingHtmlException;
    Object Native();
    boolean HasContent(String text, DriverScope scope) throws MissingHtmlException, TimeoutException;
    boolean HasContentMatch(Pattern pattern, DriverScope scope) throws MissingHtmlException, TimeoutException;
    boolean HasCss(String cssSelector, DriverScope scope) throws MissingHtmlException, TimeoutException;
    boolean HasXPath(String xpath, DriverScope scope) throws MissingHtmlException, TimeoutException;
    boolean HasDialog(String withText, DriverScope scope) throws MissingHtmlException, TimeoutException;
    ElementFound FindCss(String cssSelector, DriverScope scope) throws MissingHtmlException, TimeoutException;
    ElementFound FindXPath(String xpath, DriverScope scope) throws MissingHtmlException, TimeoutException;
    Iterable<ElementFound> FindAllCss(String cssSelector, DriverScope scope) throws MissingHtmlException, TimeoutException;
    Iterable<ElementFound> FindAllXPath(String xpath, DriverScope scope) throws MissingHtmlException, TimeoutException;
    void Check(Element field);
    void Uncheck(Element field);
    void Choose(Element field);
    boolean Disposed();
    String Location();
    ElementFound Window();
    void AcceptModalDialog(DriverScope scope) throws MissingHtmlException, TimeoutException;
    void CancelModalDialog(DriverScope scope) throws MissingHtmlException, TimeoutException;
    String ExecuteScript(String javascript, DriverScope scope) throws MissingHtmlException, TimeoutException;
    ElementFound FindFieldset(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException;
    ElementFound FindSection(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException;
    ElementFound FindId(String id, DriverScope scope) throws MissingHtmlException, TimeoutException;
    ElementFound FindIFrame(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException;
    void Hover(Element element);
    //Iterable<Cookie> GetBrowserCookies();
    ElementFound FindWindow(String locator, DriverScope scope) throws MissingHtmlException;
}
