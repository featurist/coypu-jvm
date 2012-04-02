package coypu.Drivers;

import coypu.StringJoiner;

/// <summary>
/// Helper for formatting XPath queries
/// </summary>
public class XPath {
    /// <summary>
    /// <para>Format an XPath query that uses String values for comparison that may contain single or TimeSpan quotes</para>
    /// <para>Wraps the String in the appropriate quotes or uses concat() to separate them if both are present.</para>
    /// <para>Usage:</para>
    /// <code>  new XPath().format(".//element[@attribute1 = %1$s and @attribute2 = %2$s]",inputOne,inputTwo) </code>
    /// </summary>
    /// <param name="Value"></param>
    /// <param name="args"></param>
    /// <returns></returns>
    public String format(String value, Object... args) {
        Object[] literals = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            literals[i] = literal(args[i].toString());
        }
        return String.format(value, literals);
    }

    public String literal(String value) {
        if (hasNoDoubleQuotes(value))
            return wrapInDoubleQuotes(value);

        if (hasNoSingleQuotes(value))
            return wrapInSingleQuote(value);

        return buildConcatSeparatingSingleAndDoubleQuotes(value);
    }

    private String buildConcatSeparatingSingleAndDoubleQuotes(String value) {
        String[] splitOnDouble = value.split("\"",-1);
        String[] doubleQuotedParts = new String[splitOnDouble.length];

        for (int i = 0; i < splitOnDouble.length; i++) {
            doubleQuotedParts[i] = wrapInDoubleQuotes(splitOnDouble[i]);
        }

        String reJoinedWithDoubleQuoteParts = StringJoiner.join(", '\"', ", doubleQuotedParts);

        return String.format("concat(%1$s)", trimEmptyParts(reJoinedWithDoubleQuoteParts));
    }

    private String wrapInSingleQuote(String value) {
        return String.format("'%1$s'", value);
    }

    private String wrapInDoubleQuotes(String value) {
        return String.format("\"%1$s\"", value);
    }

    private String trimEmptyParts(String concatArguments) {
        return concatArguments
                .replace(", \"\"", "")
                .replace("\"\", ", "");
    }

    private boolean hasNoSingleQuotes(String value) {
        return !value.contains("'");
    }

    private boolean hasNoDoubleQuotes(String value) {
        return !value.contains("\"");
    }
}
