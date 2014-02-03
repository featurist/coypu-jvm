//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu;

import coypu.Element;
import coypu.ElementFound;
import coypu.Options;
import coypu.Scope;
import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.LCC.IDisposable;
import java.net.URI;
import java.util.regex.Pattern;

public interface Driver   extends IDisposable
{
    Object getNative() throws Exception ;

    boolean getDisposed() throws Exception ;

    IEnumerable<ElementFound> findAllCss(String cssSelector, Scope scope, Options options, Pattern textPattern) throws Exception ;

    IEnumerable<ElementFound> findAllXPath(String xpath, Scope scope, Options options) throws Exception ;

    IEnumerable<ElementFound> findWindows(String locator, Scope scope, Options options) throws Exception ;

    IEnumerable<ElementFound> findFrames(String locator, Scope scope, Options options) throws Exception ;

    ElementFound getWindow() throws Exception ;

    URI location(Scope scope) throws Exception ;

    String title(Scope scope) throws Exception ;

    String executeScript(String javascript, Scope scope) throws Exception ;

    IEnumerable<Cookie> getBrowserCookies() throws Exception ;

    void click(Element element) throws Exception ;

    void acceptModalDialog(Scope scope) throws Exception ;

    void cancelModalDialog(Scope scope) throws Exception ;

    boolean hasDialog(String withText, Scope scope) throws Exception ;

    void choose(Element field) throws Exception ;

    void check(Element field) throws Exception ;

    void uncheck(Element field) throws Exception ;

    void set(Element element, String value) throws Exception ;

    void visit(String url, Scope scope) throws Exception ;

    void goBack(Scope scope) throws Exception ;

    void goForward(Scope scope) throws Exception ;

    void hover(Element element) throws Exception ;

    void maximiseWindow(Scope scope) throws Exception ;

    void refresh(Scope scope) throws Exception ;

    void resizeTo(Size size, Scope Scope) throws Exception ;

    void saveScreenshot(String fileName, Scope scope) throws Exception ;

    void sendKeys(Element element, String keys) throws Exception ;

}


