package coypu.Drivers.Selenium;

import coypu.DriverScope;
import coypu.Drivers.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

class ElementFinder {
    private final XPath xPath;

    public ElementFinder(XPath xPath) {
        this.xPath = xPath;
    }

    public List<WebElement> find(By by, DriverScope scope) {
        SearchContext context = seleniumScope(scope);
        return context.findElements(by);
    }

    public SearchContext seleniumScope(DriverScope scope) {
        return (SearchContext) scope.now().getNative();
    }
}
