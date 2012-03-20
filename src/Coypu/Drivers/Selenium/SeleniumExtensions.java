using System;
using System.Collections.Generic;
using System.Linq;
using OpenQA.Selenium;

namespace Coypu.Drivers.Selenium
{
    internal static class SeleniumExtensions
    {
        internal static boolean IsDisplayed(this WebElement webElement)
        {
            return webElement.Displayed;
        }

        internal static WebElement FirstDisplayedOrDefault(this IEnumerable<WebElement> elements)
        {
            return elements.FirstOrDefault(IsDisplayed);
        }

        internal static boolean AnyDisplayed(this IEnumerable<WebElement> elements)
        {
            return elements.Any(IsDisplayed);
        }

        internal static boolean AnyDisplayed(this IEnumerable<WebElement> elements, Func<WebElement, boolean> predicate)
        {
            return elements.Any(e => predicate(e) && IsDisplayed(e));
        }

        internal static WebElement FirstDisplayedOrDefault(this IEnumerable<WebElement> elements, Func<WebElement, boolean> predicate)
        {
            return elements.FirstOrDefault(e => predicate(e) && IsDisplayed(e));
        }
    }
}