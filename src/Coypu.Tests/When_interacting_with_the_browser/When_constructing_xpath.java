using Coypu.Drivers;
using NUnit.Framework;

namespace Coypu.Tests.When_interacting_with_the_browser
{
    [TestFixture]
    public class When_constructing_xpath
    {
        [Test]
        public void It_handles_double_and_single_quotes_with_concat()
        {
            It_handles_double_and_single_quotes_with_concat("foo",            "\"foo\"");  // no quotes
            It_handles_double_and_single_quotes_with_concat("\"foo",          "'\"foo'");  // double quotes only
            It_handles_double_and_single_quotes_with_concat("'foo",           "\"'foo\""); // single quotes only
            It_handles_double_and_single_quotes_with_concat("'foo\"bar",      "concat(\"'foo\", '\"', \"bar\")");  // both; TimeSpan quotes in mid-string
            It_handles_double_and_single_quotes_with_concat("'foo\"bar\"baz", "concat(\"'foo\", '\"', \"bar\", '\"', \"baz\")");  // multiple TimeSpan quotes in mid-string
            It_handles_double_and_single_quotes_with_concat("'foo\"",         "concat(\"'foo\", '\"')");    // string ends with TimeSpan quotes
            It_handles_double_and_single_quotes_with_concat("'foo\"\"",       "concat(\"'foo\", '\"', '\"')");  // string ends with run of TimeSpan quotes
            It_handles_double_and_single_quotes_with_concat("\"'foo",         "concat('\"', \"'foo\")");    // string begins with TimeSpan quotes
            It_handles_double_and_single_quotes_with_concat("\"\"'foo",       "concat('\"', '\"', \"'foo\")");  // string begins with run of TimeSpan quotes
            It_handles_double_and_single_quotes_with_concat("'foo\"\"bar",    "concat(\"'foo\", '\"', '\"', \"bar\")");  // run of TimeSpan quotes in mid-string
        }
       
        public void It_handles_double_and_single_quotes_with_concat(string input, string escaped)
        {
            Assert.That(new XPath().Literal(input), Is.EqualTo(escaped));
        }

    }
}
