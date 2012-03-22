package Coypu;

import com.sun.jndi.toolkit.url.Uri;
import org.openqa.selenium.Cookie;

import java.util.regex.Pattern;

public interface Driver
{
    void Dispose();
    ElementFound FindButton(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    ElementFound FindLink(String linkText, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    ElementFound FindField(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    public void Click(Element element);
    void Visit(String url);
    void Set(Element element, String value);
    void Select(Element element, String option) throws MissingHtmlException;
    Object Native();
    boolean HasContent(String text, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    boolean HasContentMatch(Pattern pattern, DriverScope scope) throws MissingHtmlException;
    boolean HasCss(String cssSelector, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    boolean HasXPath(String xpath, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    boolean HasDialog(String withText, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    ElementFound FindCss(String cssSelector, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    ElementFound FindXPath(String xpath, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    Iterable<ElementFound> FindAllCss(String cssSelector, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    Iterable<ElementFound> FindAllXPath(String xpath, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    void Check(Element field);
    void Uncheck(Element field);
    void Choose(Element field);
    boolean Disposed();
    Uri Location();
    ElementFound Window();
    void AcceptModalDialog(DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    void CancelModalDialog(DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    String ExecuteScript(String javascript, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    ElementFound FindFieldset(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    ElementFound FindSection(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    ElementFound FindId(String id, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    ElementFound FindIFrame(String locator, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException;
    void Hover(Element element);
    Iterable<Cookie> GetBrowserCookies();
    ElementFound FindWindow(String locator, DriverScope scope) throws MissingHtmlException;
}
