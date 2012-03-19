using System;
using System.Collections.Generic;
using System.Linq;
using OpenQA.Selenium;

namespace Coypu.Drivers.Selenium
{
    internal static class SeleniumExtensions
    {
        internal static boolean IsDisplayed(this IWebElement webElement)
        {
            return webElement.Displayed;
        }

        internal static IWebElement FirstDisplayedOrDefault(this IEnumerable<IWebElement> elements)
        {
            return elements.FirstOrDefault(IsDisplayed);
        }

        internal static boolean AnyDisplayed(this IEnumerable<IWebElement> elements)
        {
            return elements.Any(IsDisplayed);
        }

        internal static boolean AnyDisplayed(this IEnumerable<IWebElement> elements, Func<IWebElement, boolean> predicate)
        {
            return elements.Any(e => predicate(e) && IsDisplayed(e));
        }

        internal static IWebElement FirstDisplayedOrDefault(this IEnumerable<IWebElement> elements, Func<IWebElement, boolean> predicate)
        {
            return elements.FirstOrDefault(e => predicate(e) && IsDisplayed(e));
        }
    }
}