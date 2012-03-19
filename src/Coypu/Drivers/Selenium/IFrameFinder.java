using System.Linq;
using OpenQA.Selenium;

namespace Coypu.Drivers.Selenium
{
    internal class IFrameFinder
    {
        private final IWebDriver selenium;
        private final ElementFinder elementFinder;
        private final XPath xPath;

        public IFrameFinder(IWebDriver selenium, ElementFinder elementFinder, XPath xPath)
        {
            this.selenium = selenium;
            this.elementFinder = elementFinder;
            this.xPath = xPath;
        }

        public IWebElement FindIFrame(String locator, DriverScope scope)
        {
            var frame = elementFinder.Find(By.TagName("iframe"), scope).FirstOrDefault(e => e.GetAttribute("id") == locator ||
                                                                                            e.GetAttribute("title") == locator ||
                                                                                            FrameContentsMatch(e, locator));
            return frame;
        }

        private boolean FrameContentsMatch(IWebElement e, String locator)
        {
            var currentHandle = selenium.CurrentWindowHandle;
            try
            {
                var frame = selenium.SwitchTo().Frame(e);
                return
                    frame.Title == locator ||
                    frame.FindElements(By.XPath(xPath.Format(".//h1[text() = {0}]", locator))).Any();
            }
            finally
            {
                selenium.SwitchTo().Window(currentHandle);
            }
        }
    }
}