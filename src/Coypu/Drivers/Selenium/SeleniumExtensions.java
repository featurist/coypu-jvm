package Coypu.Drivers.Selenium;

import org.openqa.selenium.WebElement;

class SeleniumExtensions
{
    static boolean IsDisplayed(this WebElement webElement)
    {
        return webElement.Displayed;
    }

    static WebElement FirstDisplayedOrDefault(this IEnumerable<WebElement> elements)
    {
        return elements.FirstOrDefault(IsDisplayed);
    }

    static boolean AnyDisplayed(this IEnumerable<WebElement> elements)
    {
        return elements.Any(IsDisplayed);
    }

    static boolean AnyDisplayed(this IEnumerable<WebElement> elements, Func<WebElement, boolean> predicate)
    {
        return elements.Any(e => predicate(e) && IsDisplayed(e));
    }

    static WebElement FirstDisplayedOrDefault(this IEnumerable<WebElement> elements, Func<WebElement, boolean> predicate)
    {
        return elements.FirstOrDefault(e => predicate(e) && IsDisplayed(e));
    }
}