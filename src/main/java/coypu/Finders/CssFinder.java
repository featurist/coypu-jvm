//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:16
//

package coypu.Finders;

import coypu.Driver;
import coypu.DriverScope;
import coypu.ElementFound;
import coypu.Finders.ElementFinder;
import coypu.Options;
import coypu.TextPrecision;
import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.StringSupport;
import CS2JNet.System.Text.RegularExpressions.RegexOptions;
import java.util.regex.Pattern;

public class CssFinder  extends ElementFinder 
{
    private final Pattern textPattern;
    private final String text;
    public CssFinder(Driver driver, String locator, DriverScope scope, Options options) throws Exception {
        super(driver, locator, scope, options);
    }

    public CssFinder(Driver driver, String locator, DriverScope scope, Options options, Pattern textPattern) throws Exception {
        this(driver, locator, scope, options);
        this.textPattern = textPattern;
    }

    public CssFinder(Driver driver, String locator, DriverScope scope, Options options, String text) throws Exception {
        this(driver, locator, scope, options);
        this.text = text;
    }

    public boolean getSupportsSubstringTextMatching() throws Exception {
        return true;
    }

    public IEnumerable<ElementFound> find(Options options) throws Exception {
        return Driver.FindAllCss(getLocator(), Scope, options, textPattern(options.getTextPrecision() == TextPrecision.Exact));
    }

    private Pattern textPattern(boolean exact) throws Exception {
        if (text != null)
            return textAsRegex(text,exact);
         
        if (textPattern != null)
            return textPattern;
         
        return null;
    }

    public String getQueryDescription() throws Exception {
        String queryDesciption = "css: " + getLocator();
        if (textPattern != null)
            queryDesciption += " with text matching /" + text != null ? " with text matching /" + text : textPattern + "/";
         
        return queryDesciption;
    }

    public static Pattern textAsRegex(String textEquals, boolean exact) throws Exception {
        Pattern textMatches = null;
        if (textEquals != null)
        {
            /* [UNSUPPORTED] 'var' as type is unsupported "var" */ escapedText = Pattern.Escape(textEquals);
            if (exact)
                escapedText = String.format(StringSupport.CSFmtStrToJFmtStr("^{0}$"),escapedText);
             
            textMatches = new Pattern(escapedText, Pattern.MULTILINE);
        }
         
        return textMatches;
    }

}


