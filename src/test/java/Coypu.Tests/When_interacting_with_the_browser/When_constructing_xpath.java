package Coypu.Tests.When_interacting_with_the_browser;

import Coypu.Drivers.XPath;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_constructing_xpath
{
    @Test
    public void it_handles_double_and_single_quotes_with_concat()
    {
        it_handles_double_and_single_quotes_with_concat("foo",            "\"foo\"");  // no quotes
        it_handles_double_and_single_quotes_with_concat("\"foo",          "'\"foo'");  // double quotes only
        it_handles_double_and_single_quotes_with_concat("'foo",           "\"'foo\""); // single quotes only
        it_handles_double_and_single_quotes_with_concat("'foo\"bar",      "concat(\"'foo\", '\"', \"bar\")");  // both; double quotes in mid-string
        it_handles_double_and_single_quotes_with_concat("'foo\"bar\"baz", "concat(\"'foo\", '\"', \"bar\", '\"', \"baz\")");  // multiple double quotes in mid-string
        it_handles_double_and_single_quotes_with_concat("'foo\"",         "concat(\"'foo\", '\"')");    // string ends with double quotes
        it_handles_double_and_single_quotes_with_concat("'foo\"\"",       "concat(\"'foo\", '\"', '\"')");  // string ends with run of double quotes
        it_handles_double_and_single_quotes_with_concat("\"'foo",         "concat('\"', \"'foo\")");    // string begins with double quotes
        it_handles_double_and_single_quotes_with_concat("\"\"'foo",       "concat('\"', '\"', \"'foo\")");  // string begins with run of double quotes
        it_handles_double_and_single_quotes_with_concat("'foo\"\"bar",    "concat(\"'foo\", '\"', '\"', \"bar\")");  // run of double quotes in mid-string
    }

    public void it_handles_double_and_single_quotes_with_concat(String input, String escaped)
    {
        assertThat(new XPath().literal(input), is(equalTo(escaped)));
    }

}
