//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:14
//

package coypu.Drivers.Selenium;

import CS2JNet.System.Collections.LCC.IEnumerable;

public class SeleniumExtensions   
{
    public static boolean isDisplayed(/* parameter modifiers are not yet supported this */IWebElement webElement) throws Exception {
        return webElement.Displayed;
    }

    public static IWebElement firstDisplayedOrDefault(/* parameter modifiers are not yet supported this */IEnumerable<IWebElement> elements) throws Exception {
        return elements.FirstOrDefault(IsDisplayed);
    }

    public static boolean anyDisplayed(/* parameter modifiers are not yet supported this */IEnumerable<IWebElement> elements) throws Exception {
        return elements.Any(IsDisplayed);
    }

    public static boolean anyDisplayed(/* parameter modifiers are not yet supported this */IEnumerable<IWebElement> elements, Func<IWebElement, Boolean> predicate) throws Exception {
        return elements.Any(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(e) => {
            return predicate(e) && IsDisplayed(e);
        }" */);
    }

    public static IWebElement firstDisplayedOrDefault(/* parameter modifiers are not yet supported this */IEnumerable<IWebElement> elements, Func<IWebElement, Boolean> predicate) throws Exception {
        return elements.FirstOrDefault(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(e) => {
            return predicate(e) && IsDisplayed(e);
        }" */);
    }

}


