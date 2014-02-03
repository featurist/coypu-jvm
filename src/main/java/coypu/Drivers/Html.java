//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu.Drivers;

import coypu.Drivers.XPath;
import coypu.Options;
import CS2JNet.System.StringSupport;

/**
* Construct XPath queries to find different types of HTML element
*/
public class Html  extends XPath 
{
    private static final String[] InputButtonTypes;
    private static final String[] FieldTagNames;
    private static final String[] FieldInputTypes;
    private static final String[] FieldInputTypeWithHidden = FieldInputTypes.Union().ToArray();
    private static final String[] FindByNameTypes = FieldInputTypes.Except().ToArray();
    private static final String[] FindByValueTypes;
    private final String[] sectionTags = { "section", "div", "li" };
    private final String[] headerTags = { "h1", "h2", "h3", "h4", "h5", "h6" };
    public Html(boolean uppercaseTagNames) throws Exception {
        super(uppercaseTagNames);
    }

    public String id(String locator, Options options) throws Exception {
        return Descendent() + where(hasId(locator));
    }

    public String link(String locator, Options options) throws Exception {
        return descendent("a") + Where(IsText(locator, options));
    }

    public String field(String locator, Options options) throws Exception {
        return Descendent() + where(tagNamedOneOf(FieldTagNames) + And(IsForLabeled(locator, options) + or + IsContainerLabeled(locator, options) + or + HasIdOrPlaceholder(locator, options) + or + hasName(locator) + or + hasValue(locator)));
    }

    public String select(String locator, Options options) throws Exception {
        return descendent("select") + Where(IsForLabeled(locator, options) + or + IsContainerLabeled(locator, options) + or + hasId(locator) + or + hasName(locator));
    }

    public String frameXPath(String locator) throws Exception {
        return Descendent() + where(tagNamedOneOf("iframe","frame")) + or + frameAttributesMatch(locator);
    }

    public String button(String locator, Options options) throws Exception {
        return Descendent() + where(group(isInputButton() + or + tagNamedOneOf("button") + or + hasOneOfClasses("button","btn") + or + Attr("role", "button", Options.getExact())) + And(AttributesMatchLocator(locator, Options.getExact(), "@id", "@name") + or + AttributesMatchLocator(StringSupport.Trim(locator), options, "@value", "@alt", "normalize-space()")));
    }

    public String fieldset(String locator, Options options) throws Exception {
        return descendent("fieldset") + where(child("legend") + Where(IsText(locator, options)) + or + hasId(locator));
    }

    public String section(String locator, Options options) throws Exception {
        return Descendent() + where(tagNamedOneOf(sectionTags) + And(Child() + where(tagNamedOneOf(headerTags) + and + IsText(locator, options)) + or + hasId(locator)));
    }

    public String option(String locator, Options options) throws Exception {
        return child("option") + Where(IsText(locator, options) + or + Is("@value", locator, options));
    }

    private String hasValue(String locator) throws Exception {
        return group(attributeIsOneOf("type",FindByValueTypes) + and + Attr("value", locator, Options.getExact()));
    }

    private String hasName(String locator) throws Exception {
        return group(attributeIsOneOfOrMissing("type",FindByNameTypes) + and + Attr("name", locator, Options.getExact()));
    }

    private String frameAttributesMatch(String locator) throws Exception {
        return AttributesMatchLocator(StringSupport.Trim(locator), Options.getExact(), "@id", "@name", "@title");
    }

    private String isInputButton() throws Exception {
        return group(tagNamedOneOf("input") + and + attributeIsOneOf("type",InputButtonTypes));
    }

    private String isAFieldInputType(Options options) throws Exception {
        String[] fieldInputTypes = options.getConsiderInvisibleElements() ? FieldInputTypeWithHidden : FieldInputTypes;
        return attributeIsOneOfOrMissing("type",fieldInputTypes);
    }

    private String hasIdOrPlaceholder(String locator, Options options) throws Exception {
        return Group(IsAFieldInputType(options) + and(hasId(locator) + or + Is("@placeholder", locator, options)));
    }

    private String hasId(String locator) throws Exception {
        return Attr("id", locator, Options.getExact());
    }

}


