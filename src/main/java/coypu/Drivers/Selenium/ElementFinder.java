//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu.Drivers.Selenium;

import coypu.Options;
import coypu.Scope;
import CS2JNet.System.Collections.LCC.IEnumerable;

public class ElementFinder   
{
    public IEnumerable<IWebElement> findAll(By @by, Scope scope, Options options, Func<IWebElement, Boolean> predicate) throws Exception {
        return SeleniumScope(scope).FindElements(@by).Where(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(e) => {
            return matches(predicate, e) && IsDisplayed(e, options);
        }" */);
    }

    public ISearchContext seleniumScope(Scope scope) throws Exception {
        return (ISearchContext)scope.now().getNative();
    }

    private static boolean matches(Func<IWebElement, Boolean> predicate, IWebElement element) throws Exception {
        return (predicate == null || predicate(element));
    }

    public boolean isDisplayed(IWebElement e, Options options) throws Exception {
        return options.getConsiderInvisibleElements() || e.IsDisplayed();
    }

}


