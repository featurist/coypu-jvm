//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu.Drivers.Selenium;

import coypu.Drivers.Selenium.ElementFinder;
import coypu.Drivers.XPath;
import coypu.Options;
import coypu.Scope;
import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.StringSupport;

public class FrameFinder   
{
    private final IWebDriver selenium = new IWebDriver();
    private final ElementFinder elementFinder;
    private final XPath xPath;
    public FrameFinder(IWebDriver selenium, ElementFinder elementFinder, XPath xPath) throws Exception {
        this.selenium = selenium;
        this.elementFinder = elementFinder;
        this.xPath = xPath;
    }

    public IEnumerable<IWebElement> findFrame(String locator, Scope scope, Options options) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ frames = AllElementsByTag(scope, "iframe", options).Union(AllElementsByTag(scope, "frame", options));
        return WebElement(locator, frames, options);
    }

    private IEnumerable<IWebElement> allElementsByTag(Scope scope, String tagNameToFind, Options options) throws Exception {
        return elementFinder.FindAll(By.TagName(tagNameToFind), scope, options);
    }

    private IEnumerable<IWebElement> webElement(String locator, IEnumerable<IWebElement> webElements, Options options) throws Exception {
        return webElements.Where(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(e) => {
            return StringSupport.equals(e.GetAttribute("id"), locator) || StringSupport.equals(e.GetAttribute("name"), locator) || (options.getTextPrecisionExact() ? StringSupport.equals(e.GetAttribute("title"), locator) : e.GetAttribute("title").Contains(locator)) || FrameContentsMatch(e, locator, options);
        }" */);
    }

    private boolean frameContentsMatch(IWebElement e, String locator, Options options) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ currentHandle = selenium.CurrentWindowHandle;
        try
        {
            /* [UNSUPPORTED] 'var' as type is unsupported "var" */ frame = selenium.SwitchTo().Frame(e);
            return StringSupport.equals(frame.Title, locator) || frame.FindElements(By.XPath(".//h1[" + xPath.IsText(locator, options) + "]")).Any();
        }
        finally
        {
            selenium.SwitchTo().Window(currentHandle);
        }
    }

}


