package Coypu.Drivers.Selenium;

import Coypu.DriverScope;
import Coypu.Drivers.XPath;
import Coypu.MissingHtmlException;

class ElementFinder
{
    private final XPath xPath;

    public ElementFinder(XPath xPath)
    {
        this.xPath = xPath;
    }

    public Enumerable<IWebElement> FindByPartialId(String id, DriverScope scope)
    {
        String xpath = String.format(".//*[substring(@id, String-length(@id) - {0} + 1, String-length(@id)) = {1}]",
                id.length(), xPath.Literal(id));
        return Find(By.XPath(xpath),scope);
    }

    public Enumerable<IWebElement> Find(By by, DriverScope scope)
    {
        ISearchContext context = SeleniumScope(scope);
        return context.FindElements(by).Where(e => IsDisplayed(e, scope));
    }

    public ISearchContext SeleniumScope(DriverScope scope) throws MissingHtmlException
    {
        return (ISearchContext) scope.Now().Native;
    }

    public boolean IsDisplayed(IWebElement e, DriverScope scope)
    {
        return scope.ConsiderInvisibleElements || e.IsDisplayed();
    }
}