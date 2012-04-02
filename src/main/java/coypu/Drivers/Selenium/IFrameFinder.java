package coypu.Drivers.Selenium;

import coypu.DriverScope;
import coypu.Drivers.XPath;
import coypu.Iterators;
import coypu.MissingHtmlException;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nullable;

class IFrameFinder {
    private final WebDriver selenium;
    private final ElementFinder elementFinder;
    private final XPath xPath;

    public IFrameFinder(RemoteWebDriver selenium, ElementFinder elementFinder, XPath xPath) {
        this.selenium = selenium;
        this.elementFinder = elementFinder;
        this.xPath = xPath;
    }

    public WebElement findIFrame(final String locator, DriverScope scope) {
        return Iterators.firstOrDefault(elementFinder.find(By.tagName("iframe"), scope), new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return e.getAttribute("id").equals(locator) ||
                        e.getAttribute("title").equals(locator) ||
                        frameContentsMatch(e, locator);
            }
        }, scope);
    }

    private boolean frameContentsMatch(WebElement e, String locator) {
        String currentHandle = selenium.getWindowHandle();
        try {
            WebDriver frame = selenium.switchTo().frame(e);
            return
                    frame.getTitle().equals(locator) ||
                            frame.findElements(By.xpath(xPath.format(".//h1[text() = %1$s]", locator))).size() > 0;
        } finally {
            selenium.switchTo().window(currentHandle);
        }
    }
}
