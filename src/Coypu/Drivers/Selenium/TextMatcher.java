package  Coypu.Drivers.Selenium;
class TextMatcher
{
    public boolean TextMatches(WebElement e, String locator)
    {
        return e.Text.Trim() == locator.Trim();
    }
}
