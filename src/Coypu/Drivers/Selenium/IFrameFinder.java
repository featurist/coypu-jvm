package Coypu.Drivers.Selenium;

import Coypu.DriverScope;
import Coypu.Drivers.XPath;
import Coypu.MissingHtmlException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

class IFrameFinder
{
    private final WebDriver selenium;
    private final ElementFinder elementFinder;
    private final XPath xPath;

    public IFrameFinder(RemoteWebDriver selenium, ElementFinder elementFinder, XPath xPath)
    {
        this.selenium = selenium;
        this.elementFinder = elementFinder;
        this.xPath = xPath;
    }

    public WebElement FindIFrame(String locator, DriverScope scope) throws MissingHtmlException {
        return elementFinder.Find(By.tagName("iframe"), scope).FirstOrDefault(e => e.GetAttribute("id") == locator ||
                                                                                        e.GetAttribute("title") == locator ||
                                                                                        FrameContentsMatch(e, locator));
    }

    private boolean FrameContentsMatch(WebElement e, String locator)
    {
        String currentHandle = selenium.getWindowHandle();
        try
        {
            WebDriver frame = selenium.switchTo().frame(e);
            return
                frame.getTitle() == locator ||
                frame.findElements(By.xpath(xPath.Format(".//h1[text() = {0}]", locator))).size() > 0;
        }
        finally
        {
            selenium.switchTo().window(currentHandle);
        }
    }
}