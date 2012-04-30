package coypu;

import java.util.List;
import java.util.regex.Pattern;

public interface Driver {
    void dispose();

    ElementFound findButton(String locator, Scope scope);

    ElementFound findLink(String linkText, Scope scope);

    ElementFound findField(String locator, Scope scope);

    public void click(Element element);

    void visit(String url);

    void set(Element element, String value, boolean forceAllEvents);

    void select(Element element, String option);

    Object getNative();

    boolean hasContent(String text, Scope scope);

    boolean hasContentMatch(Pattern pattern, Scope scope);

    boolean hasCss(String cssSelector, Scope scope);

    boolean hasXPath(String xpath, Scope scope);

    boolean hasDialog(String withText, Scope scope);

    ElementFound findCss(String cssSelector, Scope scope);

    ElementFound findXPath(String xpath, Scope scope);

    List<ElementFound> findAllCss(String cssSelector, Scope scope);

    List<ElementFound> findAllXPath(String xpath, Scope scope);

    void check(Element field);

    void uncheck(Element field);

    void choose(Element field);

    boolean disposed();

    String getLocation(Scope scope);

    ElementFound window();

    void acceptModalDialog(Scope scope);

    void cancelModalDialog(Scope scope);

    String executeScript(String javascript, Scope scope);

    ElementFound findFieldset(String locator, Scope scope);

    ElementFound findSection(String locator, Scope scope);

    ElementFound findId(String id, Scope scope);

    ElementFound findFrame(String locator, Scope scope);

    void hover(Element element);

    //List<Cookie> getBrowserCookies();
    ElementFound findWindow(String locator, Scope scope);

    String getTitle(Scope scope);
}
