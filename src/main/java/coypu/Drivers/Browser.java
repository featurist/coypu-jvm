//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu.Drivers;

import coypu.Drivers.Browser;
import coypu.Drivers.NoSuchBrowserException;
import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.ObjectSupport;
import CS2JNet.System.Reflection.BindingFlags;
import CS2JNet.System.TypeSupport;
import java.lang.reflect.Field;

/**
* The browser that will be used by your chosen driver
*/
public class Browser   
{
    private Browser() throws Exception {
    }

    private boolean __Javascript;
    public boolean getJavascript() {
        return __Javascript;
    }

    public void setJavascript(boolean value) {
        __Javascript = value;
    }

    private boolean __ModalDialogs;
    public boolean getModalDialogs() {
        return __ModalDialogs;
    }

    public void setModalDialogs(boolean value) {
        __ModalDialogs = value;
    }

    private boolean __IFrames;
    public boolean getIFrames() {
        return __IFrames;
    }

    public void setIFrames(boolean value) {
        __IFrames = value;
    }

    private boolean __UppercaseTagNames;
    public boolean getUppercaseTagNames() {
        return __UppercaseTagNames;
    }

    public void setUppercaseTagNames(boolean value) {
        __UppercaseTagNames = value;
    }

    public static Browser Firefox = new Browser();
    public static Browser InternetExplorer = new Browser();
    public static Browser Chrome = new Browser();
    public static Browser Safari = new Browser();
    public static Browser Android = new Browser();
    public static Browser HtmlUnit = new Browser();
    public static Browser HtmlUnitWithJavaScript = new Browser();
    public static Browser PhantomJS = new Browser();
    public static Browser parse(String browserName) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ fieldInfo = browserFields().FirstOrDefault(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(f) => {
            return ObjectSupport.Equals(browserName, StringComparison.InvariantCultureIgnoreCase);
        }" */);
        if (fieldInfo == null)
            throw new NoSuchBrowserException(browserName);
         
        return (Browser)fieldInfo.GetValue(null);
    }

    private static IEnumerable<Field> browserFields() throws Exception {
        return TypeSupport.GetFields(Browser.class,BindingFlags.getPublic() | BindingFlags.getStatic());
    }

}


