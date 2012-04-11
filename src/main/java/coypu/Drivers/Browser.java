package coypu.Drivers;

/*
* The browser that will be used by your chosen driver
*/
public class Browser {

    private boolean javascript;

    public Browser(boolean javascript) {
        this.javascript = javascript;
    }

    public boolean javascript() {
        return javascript;
    }

    public static Browser Firefox                = new Browser(true);
    public static Browser InternetExplorer       = new Browser(true);
    public static Browser Chrome                 = new Browser(true);
    public static Browser IPhone                 = new Browser(true);
    public static Browser Android                = new Browser(true);
    public static Browser HtmlUnit               = new Browser(false);
    public static Browser HtmlUnitWithJavaScript = new Browser(true);
}
