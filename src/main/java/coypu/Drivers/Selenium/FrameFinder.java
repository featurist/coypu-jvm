package coypu.Drivers.Selenium;

import com.google.common.base.Predicate;
import coypu.Drivers.XPath;
import coypu.Iterators;
import coypu.Scope;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

class FrameFinder {
    private final WebDriver selenium;
    private final ElementFinder elementFinder;
    private final XPath xPath;

    public FrameFinder(WebDriver selenium, ElementFinder elementFinder, XPath xPath) {
        this.selenium = selenium;
        this.elementFinder = elementFinder;
        this.xPath = xPath;
    }

    public WebElement findFrame(final String locator, Scope scope) {
        Predicate<WebElement> predicate = new Predicate<WebElement>() {
            @Override
            public boolean apply(@Nullable WebElement e) {
                return e.getAttribute("id").equals(locator) ||
                        e.getAttribute("title").equals(locator) ||
                        e.getAttribute("name").equals(locator) ||
                        frameContentsMatch(e, locator);
            }
        };
        WebElement iFrame = Iterators.firstOrDefault(elementFinder.find(By.tagName("iframe"), scope), predicate, scope);
        if (iFrame != null)
            return iFrame;

        return Iterators.firstOrDefault(elementFinder.find(By.tagName("frame"), scope), predicate, scope);
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
