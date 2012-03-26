package Coypu.Drivers.Selenium;

import Coypu.DriverScope;
import Coypu.Drivers.XPath;
import Coypu.MissingHtmlException;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

class ElementFinder
{
    private final XPath xPath;

    public ElementFinder(XPath xPath)
    {
        this.xPath = xPath;
    }

    public Iterable<WebElement> Find(By by, DriverScope scope)  {
        SearchContext context = SeleniumScope(scope);
        return context.findElements(by);
    }

    public SearchContext SeleniumScope(DriverScope scope)  {
        return (SearchContext) scope.Now().Native();
    }
}
