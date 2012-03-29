package Coypu;

import Coypu.Actions.*;
import Coypu.Finders.*;
import Coypu.Queries.*;
import Coypu.Robustness.RobustWrapper;
import Coypu.Robustness.Waiter;

import java.util.List;
import java.util.regex.Pattern;

public class DriverScope implements Coypu.Scope {
    protected Configuration configuration;
    private ElementFinder elementFinder;
    protected DriverScope outerScope;
    protected Driver driver;
    protected RobustWrapper robustWrapper;
    protected Waiter waiter;
    protected UrlBuilder urlBuilder;
    protected StateFinder stateFinder;
    private ElementFound element;
    private Options options;

    public DriverScope(Configuration configuration, ElementFinder elementFinder, Driver driver, RobustWrapper robustWrapper, Waiter waiter, UrlBuilder urlBuilder) {
        this.elementFinder = elementFinder == null ? new DocumentElementFinder(driver) : elementFinder;
        this.configuration = configuration;
        this.driver = driver;
        this.robustWrapper = robustWrapper;
        this.waiter = waiter;
        this.urlBuilder = urlBuilder;
        stateFinder = new StateFinder(robustWrapper);
    }

    protected DriverScope(ElementFinder elementFinder, DriverScope outer) {
        this.elementFinder = elementFinder;
        this.outerScope = outer;
        driver = outer.driver;
        robustWrapper = outer.robustWrapper;
        urlBuilder = outer.urlBuilder;
        stateFinder = outer.stateFinder;
        waiter = outer.waiter;
        options = outer.configuration;
        configuration = outer.configuration;
    }

    public String Location() {
        return driver.Location();
    }

    public boolean ConsiderInvisibleElements() {
        return Default(options).ConsiderInvisibleElements;
    }

    protected Options SetOptions(Options options) {
        return this.options = Default(options);
    }

    private Options Default(Options options) {
        return options == null ? configuration : options;
    }

    public void ClickButton(String locator) {
        ClickButton(locator, configuration);
    }

    public void ClickButton(String locator, Options options) {
        RetryUntilTimeout(WaitThenClickButton(locator, SetOptions(options)));
    }

    public void ClickLink(String locator) {
        ClickLink(locator, configuration);
    }

    public void ClickLink(String locator, Options options) {
        RetryUntilTimeout(WaitThenClickLink(locator, SetOptions(options)));
    }


    private WaitThenClick WaitThenClickLink(String locator, Options options) {
        return new WaitThenClick(driver, SetOptions(options), waiter, new LinkFinder(driver, locator, this));
    }

    private WaitThenClick WaitThenClickButton(String locator, Options options) {
        return new WaitThenClick(driver, SetOptions(options), waiter, new ButtonFinder(driver, locator, this));
    }

    public DriverScope ClickButton(String locator, PredicateQuery until, TimeSpan waitBeforeRetry) {
        return ClickButton(locator, until, waitBeforeRetry, configuration);
    }

    public DriverScope ClickButton(String locator, PredicateQuery until, TimeSpan waitBeforeRetry, Options options) {
        options = SetOptions(options);
        TryUntil(WaitThenClickButton(locator, options), until, waitBeforeRetry, options);
        return this;
    }

    public DriverScope ClickLink(String locator, PredicateQuery until, TimeSpan waitBeforeRetry) {
        return ClickLink(locator, until, waitBeforeRetry, configuration);
    }

    public DriverScope ClickLink(String locator, PredicateQuery until, TimeSpan waitBeforeRetry, Options options) {
        options = SetOptions(options);
        TryUntil(WaitThenClickLink(locator, options), until, waitBeforeRetry, options);
        return this;
    }

    public ElementScope FindButton(String locator) {
        return FindButton(locator, configuration);
    }

    public ElementScope FindButton(String locator, Options options) {
        return new RobustElementScope(new ButtonFinder(driver, locator, this), this, SetOptions(options));
    }

    public ElementScope FindLink(String locator) {
        return FindLink(locator, configuration);
    }

    public ElementScope FindLink(String locator, Options options) {
        return new RobustElementScope(new LinkFinder(driver, locator, this), this, SetOptions(options));
    }

    public ElementScope FindField(String locator) {
        return FindField(locator,configuration);
    }

    public ElementScope FindField(String locator, Options options) {
        return new RobustElementScope(new FieldFinder(driver, locator, this), this, SetOptions(options));
    }

    public FillInWith FillIn(String locator) {
        return FillIn(locator,configuration);
    }

    public FillInWith FillIn(String locator, Options options) {
        return new FillInWith(locator, driver, robustWrapper, this, SetOptions(options));
    }

    public SelectFrom Select(String option) {
        return Select(option,configuration);
    }

    public SelectFrom Select(String option, Options options) {
        return new SelectFrom(option, driver, robustWrapper, this, SetOptions(options));
    }

    public boolean HasContent(String text) {
        return HasContent(text,configuration);
    }

    public boolean HasContent(String text, Options options) {
        return Query(new HasContentQuery(driver, this, text, SetOptions(options)));
    }

    public boolean HasContentMatch(Pattern pattern) {
        return HasContentMatch(pattern,configuration);
    }

    public boolean HasContentMatch(Pattern pattern, Options options) {
        return Query(new HasContentMatchQuery(driver, this, pattern, SetOptions(options)));
    }

    public boolean HasNoContent(String text) {
        return HasNoContent(text,configuration);
    }

    public boolean HasNoContent(String text, Options options) {
        return Query(new HasNoContentQuery(driver, this, text, SetOptions(options)));
    }

    public boolean HasNoContentMatch(Pattern pattern) {
        return HasNoContentMatch(pattern, configuration);
    }

    public boolean HasNoContentMatch(Pattern pattern, Options options) {
        return Query(new HasNoContentMatchQuery(driver, this, pattern, SetOptions(options)));
    }

    public boolean HasCss(String cssSelector) {
        return HasCss(cssSelector,configuration);
    }

    public boolean HasCss(String cssSelector, Options options) {
        return Query(new HasCssQuery(driver, this, cssSelector, SetOptions(options)));
    }

    public boolean HasNoCss(String cssSelector) {
        return HasNoCss(cssSelector,configuration);
    }

    public boolean HasNoCss(String cssSelector, Options options) {
        return Query(new HasNoCssQuery(driver, this, cssSelector, SetOptions(options)));
    }

    public boolean HasXPath(String xpath) {
        return HasXPath(xpath,configuration);
    }
    
    public boolean HasXPath(String xpath, Options options) {
        return Query(new HasXPathQuery(driver, this, xpath, SetOptions(options)));
    }

    public boolean HasNoXPath(String xpath) {
        return HasNoXPath(xpath, configuration);
    }
    
    public boolean HasNoXPath(String xpath, Options options) {
        return Query(new HasNoXPathQuery(driver, this, xpath, SetOptions(options)));
    }

    public ElementScope FindCss(String cssSelector, Options options) {
        return new RobustElementScope(new CssFinder(driver, cssSelector, this), this, SetOptions(options));
    }

    public ElementScope FindCss(String cssSelector) {
        return FindCss(cssSelector,configuration);
    }

    public ElementScope FindXPath(String xpath) {
        return FindXPath(xpath,configuration);
    }
    
    public ElementScope FindXPath(String xpath, Options options) {
        return new RobustElementScope(new XPathFinder(driver, xpath, this), this, SetOptions(options));
    }

    public List<ElementFound> FindAllCss(String cssSelector) {
        return FindAllCss(cssSelector,configuration);
    }
    
    public List<ElementFound> FindAllCss(String cssSelector, Options options) {
        SetOptions(options);
        return driver.FindAllCss(cssSelector, this);
    }

    public List<ElementFound> FindAllXPath(String xpath) {
        return FindAllXPath(xpath, configuration);
    }
    
    public List<ElementFound> FindAllXPath(String xpath, Options options) {
        SetOptions(options);
        return driver.FindAllXPath(xpath, this);
    }

    public ElementScope FindSection(String locator) {
        return FindSection(locator,configuration);
    }
    
    public ElementScope FindSection(String locator, Options options) {
        return new RobustElementScope(new SectionFinder(driver, locator, this), this, SetOptions(options));
    }

    public ElementScope FindFieldset(String locator) {
        return FindField(locator,configuration);
    }

    public ElementScope FindFieldset(String locator, Options options) {
        return new RobustElementScope(new FieldsetFinder(driver, locator, this), this, SetOptions(options));
    }

    public ElementScope FindId(String id) {
        return FindId(id, configuration);
    }

    public ElementScope FindId(String id, Options options) {
        return new RobustElementScope(new IdFinder(driver, id, this), this, SetOptions(options));
    }

    public void Check(String locator) {
        Check(locator,configuration);
    }    
    
    public void Check(String locator, Options options) {
        RetryUntilTimeout(new Check(driver, this, locator, SetOptions(options)));
    }

    public void Uncheck(String locator) {
        Uncheck(locator,configuration);
    }

    public void Uncheck(String locator, Options options) {
        RetryUntilTimeout(new Uncheck(driver, this, locator, SetOptions(options)));
    }

    public void Choose(String locator) {
        Choose(locator,configuration);
    }

    public void Choose(String locator, Options options) {
        RetryUntilTimeout(new Choose(driver, this, locator, SetOptions(options)));
    }

    public String ExecuteScript(String javascript) {
        return driver.ExecuteScript(javascript, this);
    }

    public DriverScope Hover(Options options) {
        RetryUntilTimeout(new Hover(this, driver, SetOptions(options)));
        return this;
    }

    public DriverScope Hover() {
        return Hover(configuration);
    }

    public boolean Has(ElementScope findElement) {
        return Has(findElement,configuration);
    }

    public boolean Has(ElementScope findElement, Options options) {
        return findElement.Exists(options);
    }

    public boolean HasNo(ElementScope findElement) {
        return HasNo(findElement,configuration);
    }

    public boolean HasNo(ElementScope findElement, Options options) {
        return findElement.Missing(options);
    }

    public void RetryUntilTimeout(BrowserAction action) {
        Query(action);
    }

    public IFrameElementScope FindIFrame(String locator) {
        return FindIFrame(locator,configuration);
    }

    public IFrameElementScope FindIFrame(String locator, Options options) {
        return new IFrameElementScope(new IFrameFinder(driver, locator, this), this, SetOptions(options));
    }

    public void TryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan waitBeforeRetry) {
        TryUntil(tryThis,until,waitBeforeRetry,configuration);
    }

    public void TryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan waitBeforeRetry, Options options) {
        robustWrapper.TryUntil(tryThis, until, SetOptions(options).Timeout, waitBeforeRetry);
    }

    public State FindState(State... states) {
        return FindState(states,options);
    }

    public State FindState(State[] states, Options options) {
        return stateFinder.FindState(SetOptions(options), states);
    }

    /// <summary>
    /// Try and find this scope now
    /// </summary>
    /// <returns></returns>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    public ElementFound Now() {
        return FindElement();
    }

    public ElementFound FindElement() {
        if (element == null || element.Stale())
            element = elementFinder.Find();

        return element;
    }

    public <T> T Query(Query<T> query) {
        return robustWrapper.Robustly(query);
    }
}
