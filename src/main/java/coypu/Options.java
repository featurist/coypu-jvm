//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu;

import coypu.Match;
import coypu.Options;
import coypu.TextPrecision;
import CS2JNet.System.ObjectSupport;
import CS2JNet.System.StringSupport;
import CS2JNet.System.TimeSpan;

/**
* Options for how Coypu interacts with the browser.
*/
public class Options   
{
    private static final boolean DEFAULT_CONSIDER_INVISIBLE_ELEMENTS = false;
    private static final TextPrecision DEFAULT_PRECISION = getTextPrecision().PreferExact;
    private static final Match DEFAULT_MATCH = getMatch().Single;
    private static final TimeSpan DEFAULT_TIMEOUT = TimeSpan.FromSeconds(1);
    private static final TimeSpan DEFAULT_RETRY_INTERVAL = TimeSpan.FromSeconds(0.05);
    private static final TimeSpan DEFAULT_WAIT_BEFORE_CLICK = TimeSpan.Zero;
    protected Boolean considerInvisibleElements;
    private TextPrecision? textPrecision = TextPrecision.Exact;
    private Match? match = Match.First;
    private TimeSpan? retryInterval;
    private TimeSpan? timeout;
    private TimeSpan? waitBeforeClick;
    public boolean equals(Object obj) {
        try
        {
            if (ReferenceEquals(null, obj))
                return false;
             
            if (ReferenceEquals(this, obj))
                return true;
             
            return equals((Options)obj);
        }
        catch (RuntimeException __dummyCatchVar0)
        {
            throw __dummyCatchVar0;
        }
        catch (Exception __dummyCatchVar0)
        {
            throw new RuntimeException(__dummyCatchVar0);
        }
    
    }

    //if (obj.GetType() != this.GetType()) return false;
    /**
    * Will not wait for asynchronous updates to the page
    */
    public static Options NoWait = new Options();
    /**
    * Include invisible elements when finding
    */
    public static Options Invisible = new Options();
    /**
    * Just picks the first element that matches
    */
    public static Options getFirst() throws Exception {
        return new Options();
    }

    /**
    * Raises an error if more than one element match
    */
    public static Options getSingle() throws Exception {
        return new Options();
    }

    /**
    * Match by exact visible text
    */
    public static Options getExact() throws Exception {
        return new Options();
    }

    /**
    * Match by substring in visible text
    */
    public static Options getSubstring() throws Exception {
        return new Options();
    }

    /**
    * If multiple matches are found, some of which are exact, and some of which are not, then the first exactly matching element is returned
    */
    public static Options getPreferExact() throws Exception {
        return new Options();
    }

    /**
    * Match exact visible text; Just picks the first element that matches
    */
    public static Options FirstExact = merge(getFirst(),getExact());
    /**
    * Match substring in visible text; Just picks the first element that matches
    */
    public static Options FirstSubstring = merge(getFirst(),getSubstring());
    /**
    * Prefer exact text matches to substring matches; Just picks the first element that matches
    */
    public static Options FirstPreferExact = merge(getFirst(),getPreferExact());
    /**
    * Match exact visible text; Raises an error if more than one element match
    */
    public static Options SingleExact = merge(getSingle(),getSubstring());
    /**
    * Match by substring in visible text; Raises an error if more than one element match
    */
    public static Options SingleSubstring = merge(getSingle(),getSubstring());
    /**
    * Prefer exact text matches to substring matches; Raises an error if more than one element match
    */
    public static Options SinglePreferExact = merge(getSingle(),getPreferExact());
    /**
    * When retrying, how long to wait for elements to appear or actions to complete without error.Default: 1sec
    */
    public TimeSpan getTimeout() throws Exception {
        return timeout != null ? timeout : DEFAULT_TIMEOUT;
    }

    public void setTimeout(TimeSpan value) throws Exception {
        timeout = value;
    }

    /**
    * How long to wait between retriesDefault: 100ms
    */
    public TimeSpan getRetryInterval() throws Exception {
        return retryInterval != null ? retryInterval : DEFAULT_RETRY_INTERVAL;
    }

    public void setRetryInterval(TimeSpan value) throws Exception {
        retryInterval = value;
    }

    /**
    * How long to wait between finding an element and clicking it.Default: zero
    */
    public TimeSpan getWaitBeforeClick() throws Exception {
        return waitBeforeClick != null ? waitBeforeClick : DEFAULT_WAIT_BEFORE_CLICK;
    }

    public void setWaitBeforeClick(TimeSpan value) throws Exception {
        waitBeforeClick = value;
    }

    /**
    * By default Coypu will exclude any invisible elements, this allows you to override that behaviourDefault: true
    */
    public boolean getConsiderInvisibleElements() throws Exception {
        return considerInvisibleElements != null ? considerInvisibleElements : DEFAULT_CONSIDER_INVISIBLE_ELEMENTS;
    }

    public void setConsiderInvisibleElements(boolean value) throws Exception {
        considerInvisibleElements = value;
    }

    /**
    * Whether to consider substrings when finding elements by text, or just an exact match.
    */
    public TextPrecision getTextPrecision() throws Exception {
        return textPrecision != null ? textPrecision : DEFAULT_PRECISION;
    }

    public void setTextPrecision(TextPrecision value) throws Exception {
        textPrecision = value;
    }

    public boolean getTextPrecisionExact() throws Exception {
        return textPrecision == getTextPrecision().Exact;
    }

    /**
    * With PreventAmbiguousMatches you can control whether Coypu should throw an exception when multiple elements match a query.
    */
    public Match getMatch() throws Exception {
        return match != null ? match : DEFAULT_MATCH;
    }

    public void setMatch(Match value) throws Exception {
        match = value;
    }

    public String buildAmbiguousMessage(String queryDescription, int count) throws Exception {
        String message = String.format(StringSupport.CSFmtStrToJFmtStr("Ambiguous match, found {0} elements matching {1}\r\n" + 
        "\r\n" + 
        "Coypu does this by default from v2.0. Your options:\r\n" + 
        "\r\n" + 
        " * Look for something more specific"),count,queryDescription);
        if (getTextPrecision() != getTextPrecision().Exact)
            message += System.getProperty("line.separator") + " * Set the Options.TextPrecision option to Exact to exclude substring text matches";
         
        if (getMatch() != getMatch().First)
            message += System.getProperty("line.separator") + " * Set the Options.Match option to Match.First to just take the first matching element";
         
        return message;
    }

    /**
    * Merge any unset Options from another set of Options.
    * 
    *  @param preferredOptions The preferred set of options
    *  @param defaultOptions Any unset preferred options will be copied from this
    *  @return The new merged Options
    */
    public static Options merge(Options preferredOptions, Options defaultOptions) throws Exception {
        preferredOptions = preferredOptions != null ? preferredOptions : new Options();
        defaultOptions = defaultOptions != null ? defaultOptions : new Options();
        return new Options();
    }

    protected static <T>T? default(T? value, T? defaultValue) throws Exception {
        return value.HasValue ? value : defaultValue;
    }

    public String toString() {
        try
        {
            return String.Join(System.getProperty("line.separator"), getClass().GetProperties().Select(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(p) => {
                return p.Name + ": " + p.GetValue(this, null);
            }" */).ToArray());
        }
        catch (RuntimeException __dummyCatchVar1)
        {
            throw __dummyCatchVar1;
        }
        catch (Exception __dummyCatchVar1)
        {
            throw new RuntimeException(__dummyCatchVar1);
        }
    
    }

    protected boolean equals(Options other) {
        try
        {
            return considerInvisibleElements.equals(other.considerInvisibleElements) && textPrecision.equals(other.textPrecision) && match == other.match && retryInterval.equals(other.retryInterval) && timeout.equals(other.timeout) && waitBeforeClick.equals(other.waitBeforeClick);
        }
        catch (RuntimeException __dummyCatchVar2)
        {
            throw __dummyCatchVar2;
        }
        catch (Exception __dummyCatchVar2)
        {
            throw new RuntimeException(__dummyCatchVar2);
        }
    
    }

    public int hashCode() {
        try
        {
            /* [UNSUPPORTED] checked statements are not supported "unchecked
            {
                /* [UNSUPPORTED] 'var' as type is unsupported "var" */ hashCode = considerInvisibleElements.hashCode();
                hashCode = (hashCode * 397) ^ textPrecision.hashCode();
                hashCode = (hashCode * 397) ^ match.hashCode();
                hashCode = (hashCode * 397) ^ retryInterval.hashCode();
                hashCode = (hashCode * 397) ^ timeout.hashCode();
                hashCode = (hashCode * 397) ^ waitBeforeClick.hashCode();
                return hashCode;
            }" */
        }
        catch (RuntimeException __dummyCatchVar3)
        {
            throw __dummyCatchVar3;
        }
        catch (Exception __dummyCatchVar3)
        {
            throw new RuntimeException(__dummyCatchVar3);
        }
    
    }



}


