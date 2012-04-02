package coypu;

import java.util.List;
import java.util.regex.Pattern;

public interface Driver {
    void dispose();

    ElementFound findButton(String locator, DriverScope scope);

    ElementFound findLink(String linkText, DriverScope scope);

    ElementFound findField(String locator, DriverScope scope);

    public void click(Element element);

    void visit(String url);

    void set(Element element, String value);

    void select(Element element, String option);

    Object getNative();

    boolean hasContent(String text, DriverScope scope);

    boolean hasContentMatch(Pattern pattern, DriverScope scope);

    boolean hasCss(String cssSelector, DriverScope scope);

    boolean hasXPath(String xpath, DriverScope scope);

    boolean hasDialog(String withText, DriverScope scope);

    ElementFound findCss(String cssSelector, DriverScope scope);

    ElementFound findXPath(String xpath, DriverScope scope);

    List<ElementFound> findAllCss(String cssSelector, DriverScope scope);

    List<ElementFound> findAllXPath(String xpath, DriverScope scope);

    void check(Element field);

    void uncheck(Element field);

    void choose(Element field);

    boolean disposed();

    String location();

    ElementFound window();

    void acceptModalDialog(DriverScope scope);

    void cancelModalDialog(DriverScope scope);

    String executeScript(String javascript, DriverScope scope);

    ElementFound findFieldset(String locator, DriverScope scope);

    ElementFound findSection(String locator, DriverScope scope);

    ElementFound findId(String id, DriverScope scope);

    ElementFound findIFrame(String locator, DriverScope scope);

    void hover(Element element);

    //List<Cookie> getBrowserCookies();
    ElementFound findWindow(String locator, DriverScope scope);
}
