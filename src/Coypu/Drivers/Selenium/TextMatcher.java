using OpenQA.Selenium;

namespace Coypu.Drivers.Selenium
{
    internal class TextMatcher
    {
        public boolean TextMatches(IWebElement e, String locator)
        {
            return e.Text.Trim() == locator.Trim();
        }
    }
}