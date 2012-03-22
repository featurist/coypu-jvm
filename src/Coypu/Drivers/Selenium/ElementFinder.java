package Coypu.Drivers.Selenium;

import Coypu.DriverScope;
import Coypu.Drivers.XPath;
import Coypu.MissingHtmlException;
import Coypu.TimeoutException;
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

    public Iterable<WebElement> FindByPartialId(String id, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        String xpath = String.format(".//*[substring(@id, String-length(@id) - {0} + 1, String-length(@id)) = {1}]",
                id.length(), xPath.Literal(id));
        return Find(By.xpath(xpath),scope);
    }

    public Iterable<WebElement> Find(By by, DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        SearchContext context = SeleniumScope(scope);
        return context.findElements(by).Where(e => IsDisplayed(e, scope));
    }

    public SearchContext SeleniumScope(DriverScope scope) throws MissingHtmlException, TimeoutException, InterruptedException {
        return (SearchContext) scope.Now().Native();
    }

    public boolean IsDisplayed(WebElement e, DriverScope scope)
    {
        return scope.ConsiderInvisibleElements() || e.isDisplayed();
    }
}