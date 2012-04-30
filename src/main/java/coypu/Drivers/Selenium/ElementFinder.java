package coypu.Drivers.Selenium;

import coypu.Drivers.XPath;
import coypu.Scope;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

class ElementFinder {
    private final XPath xPath;

    public ElementFinder(XPath xPath) {
        this.xPath = xPath;
    }

    public List<WebElement> find(By by, Scope scope) {
        SearchContext context = seleniumScope(scope);
        return context.findElements(by);
    }

    public SearchContext seleniumScope(Scope scope) {
        return (SearchContext) scope.now().getNative();
    }
}
