//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:15
//

package coypu.Drivers;

import coypu.Options;
import CS2JNet.System.StringSupport;

/**
* Helper for formatting XPath queries
*/
public class XPath   
{
    private final boolean uppercaseTagNames;
    public XPath(boolean uppercaseTagNames) throws Exception {
        this.uppercaseTagNames = uppercaseTagNames;
    }

    /**
    * Format an XPath query that uses string values for comparison that may contain single or double quotesWraps the string in the appropriate quotes or uses concat() to separate them if both are present.Usage:
    *  {@code   new XPath().Format(".//element[@attribute1 = {0} and @attribute2 = {1}]",inputOne,inputTwo) }
    * 
    *  @param value 
    *  @param args 
    *  @return
    */
    public String format(String value, Object... args) throws Exception {
        return String.format(StringSupport.CSFmtStrToJFmtStr(value),args.Select(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(a) => {
            return literal(a.toString());
        }" */).ToArray());
    }

    public static final String and = " and ";
    public static final String or = " or ";
    public String group(String expression) throws Exception {
        return "(" + expression + ")";
    }

    public String and(String expression) throws Exception {
        return and + group(expression);
    }

    public String literal(String value) throws Exception {
        if (hasNoDoubleQuotes(value))
            return wrapInDoubleQuotes(value);
         
        if (hasNoSingleQuotes(value))
            return wrapInSingleQuote(value);
         
        return buildConcatSeparatingSingleAndDoubleQuotes(value);
    }

    private String buildConcatSeparatingSingleAndDoubleQuotes(String value) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ doubleQuotedParts = StringSupport.Split(value, '\"').Select(WrapInDoubleQuotes).ToArray();
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ reJoinedWithDoubleQuoteParts = String.Join(", '\"', ", doubleQuotedParts);
        return String.format(StringSupport.CSFmtStrToJFmtStr("concat({0})"),TrimEmptyParts(reJoinedWithDoubleQuoteParts));
    }

    private String wrapInSingleQuote(String value) throws Exception {
        return String.format(StringSupport.CSFmtStrToJFmtStr("'{0}'"),value);
    }

    private String wrapInDoubleQuotes(String value) throws Exception {
        return String.format(StringSupport.CSFmtStrToJFmtStr("\"{0}\""),value);
    }

    private String trimEmptyParts(String concatArguments) throws Exception {
        return concatArguments.replace(", \"\"", "").replace("\"\", ", "");
    }

    private boolean hasNoSingleQuotes(String value) throws Exception {
        return !value.contains("'");
    }

    private boolean hasNoDoubleQuotes(String value) throws Exception {
        return !value.contains("\"");
    }

    public String hasOneOfClasses(String... classNames) throws Exception {
        return Group(String.Join(" or ", classNames.Select(XPathNodeHasClass).ToArray()));
    }

    public String xPathNodeHasClass(String className) throws Exception {
        return String.format(StringSupport.CSFmtStrToJFmtStr("contains(@class,' {0}') " + "or contains(@class,'{0} ') " + "or contains(@class,' {0} ')"),className);
    }

    public String attributeIsOneOfOrMissing(String attributeName, String[] values) throws Exception {
        return group(attributeIsOneOf(attributeName,values) + or + "not(@" + attributeName + ")");
    }

    public String attributeIsOneOf(String attributeName, String[] values) throws Exception {
        return Group(String.Join(" or ", values.Select(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(t) => {
            return format("@" + attributeName + " = {0}",t);
        }" */).ToArray()));
    }

    public String attr(String name, String value, Options options) throws Exception {
        return Is("@" + name, value, options);
    }

    public String tagNamedOneOf(String... fieldTagNames) throws Exception {
        return Group(String.Join(" or ", fieldTagNames.Select(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(t) => {
            return format("name() = {0}",CasedTagName(t));
        }" */).ToArray()));
    }

    public String casedTagName(String tagName) throws Exception {
        return uppercaseTagNames ? tagName.toUpperCase() : tagName;
            ;
    }

    public String attributesMatchLocator(String locator, Options options, String... attributes) throws Exception {
        return Group(String.Join(" or ", attributes.Select(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(a) => {
            return Is(a, locator, options);
        }" */).ToArray()));
    }

    public String isContainerLabeled(String locator, Options options) throws Exception {
        return format("ancestor::label[" + IsTextShallow(locator, options) + "]",locator);
    }

    public String isForLabeled(String locator, Options options) throws Exception {
        return format(" (@id = //label[" + IsText(locator, options) + "]/@for) ",locator);
    }

    public String is(String selector, String locator, Options options) throws Exception {
        return options.getTextPrecisionExact() ? format(selector + " = {0} ",locator) : format("contains(" + selector + ",{0})",locator);
    }

    public String isText(String locator, Options options) throws Exception {
        return Is("normalize-space()", locator, options);
    }

    public String isTextShallow(String locator, Options options) throws Exception {
        return Is("normalize-space(text())", locator, options);
    }

    protected String descendent(String tagName) throws Exception {
        return ".//" + tagName;
    }

    protected String child(String tagName) throws Exception {
        return "./" + tagName;
    }

    protected static String where(String predicate) throws Exception {
        return "[" + predicate + "]";
    }

}


