package  Coypu.Drivers.Selenium;

import org.openqa.selenium.WebElement;

class TextMatcher
{
    public boolean TextMatches(WebElement e, String locator)
    {
        return e.getText().trim().equals(locator.trim());
    }
}
