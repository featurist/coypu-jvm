package Coypu.Drivers;

import Coypu.StringJoiner;

/// <summary>
/// Helper for formatting XPath queries
/// </summary>
public class XPath
{
    /// <summary>
    /// <para>Format an XPath query that uses String values for comparison that may contain single or TimeSpan quotes</para>
    /// <para>Wraps the String in the appropriate quotes or uses concat() to separate them if both are present.</para>
    /// <para>Usage:</para>
    /// <code>  new XPath().Format(".//element[@attribute1 = %1$s and @attribute2 = %2$s]",inputOne,inputTwo) </code>
    /// </summary>
    /// <param name="value"></param>
    /// <param name="args"></param>
    /// <returns></returns>
    public String Format(String value, Object... args)
    {
        Object[] literals = new Object[args.length];
        for(int i = 0; i < args.length; i++) {
            literals[i] = Literal(args[i].toString());
        }
        return String.format(value, literals);
    }

    public String Literal(String value)
    {
        if (HasNoDoubleQuotes(value))
            return WrapInDoubleQuotes(value);

        if (HasNoSingleQuotes(value))
            return WrapInSingleQuote(value);

        return BuildConcatSeparatingSingleAndDoubleQuotes(value);
    }

    private String BuildConcatSeparatingSingleAndDoubleQuotes(String value)
    {
        String[] splitOnDouble = value.split("\"");
        String[] doubleQuotedParts = new String[splitOnDouble.length];
        
        for(int i = 0; i < splitOnDouble.length; i++) {
            doubleQuotedParts[i] = WrapInDoubleQuotes(splitOnDouble[i]);
        }

        String reJoinedWithDoubleQuoteParts = StringJoiner.join(", '\"', ", doubleQuotedParts);

        return String.format("concat(%1$s)", TrimEmptyParts(reJoinedWithDoubleQuoteParts));
    }

    private String WrapInSingleQuote(String value)
    {
        return String.format("'%1$s'", value);
    }

    private String WrapInDoubleQuotes(String value)
    {
        return String.format("\"%1$s\"",value);
    }

    private String TrimEmptyParts(String concatArguments)
    {
        return concatArguments.replace(", \"\"", "")
                              .replace("\"\", ", "");
    }

    private boolean HasNoSingleQuotes(String value)
    {
        return !value.contains("'");
    }

    private boolean HasNoDoubleQuotes(String value)
    {
        return !value.contains("\"");
    }
}
