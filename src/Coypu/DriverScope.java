package Coypu;

import Coypu.Actions.*;
import Coypu.Finders.*;
import Coypu.Queries.*;
import Coypu.Robustness.RobustWrapper;
import Coypu.Robustness.Waiter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Pattern;

public class DriverScope implements Coypu.Scope
{
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

    protected DriverScope(Configuration configuration, ElementFinder elementFinder, Driver driver, RobustWrapper robustWrapper, Waiter waiter, UrlBuilder urlBuilder)
    {
        this.elementFinder = elementFinder == null ?  new DocumentElementFinder(driver) : elementFinder;
        this.configuration = configuration;
        this.driver = driver;
        this.robustWrapper = robustWrapper;
        this.waiter = waiter;
        this.urlBuilder = urlBuilder;
        stateFinder = new StateFinder(robustWrapper);
    }

    protected DriverScope(ElementFinder elementFinder, DriverScope outer)
    {
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

    public String Location() throws URISyntaxException, MissingHtmlException {
        return driver.Location();
    }

    public boolean ConsiderInvisibleElements()
    {
        return Default(options).ConsiderInvisibleElements;
    }

    protected Options SetOptions(Options options)
    {
        return this.options = Default(options);
    }

    private Options Default(Options options)
    {
        return options == null ?  configuration : options;
    }

    public void ClickButton(String locator, Options options) throws MissingHtmlException {
        RetryUntilTimeout(WaitThenClickButton(locator, SetOptions(options)));
    }

    public void ClickLink(String locator, Options options) throws MissingHtmlException {
        RetryUntilTimeout(WaitThenClickLink(locator, SetOptions(options)));
    }

    private WaitThenClick WaitThenClickLink(String locator, Options options)
    {
        return new WaitThenClick(driver, SetOptions(options), waiter, new LinkFinder(driver, locator, this));
    }

    private WaitThenClick WaitThenClickButton(String locator, Options options)
    {
        return new WaitThenClick(driver, SetOptions(options), waiter, new ButtonFinder(driver, locator, this));
    }

    public DriverScope ClickButton(String locator, PredicateQuery until,  TimeSpan waitBeforeRetry, Options options) throws TimeoutException, MissingHtmlException {
        options = SetOptions(options);
        TryUntil(WaitThenClickButton(locator, options), until, waitBeforeRetry, options);
        return this;
    }

    public DriverScope ClickLink(String locator, PredicateQuery until,  TimeSpan waitBeforeRetry, Options options) throws TimeoutException, MissingHtmlException {
        options = SetOptions(options);
        TryUntil(WaitThenClickLink(locator, options), until, waitBeforeRetry, options);
        return this;
    }

    public ElementScope FindButton(String locator, Options options)
    {
        return new RobustElementScope(new ButtonFinder(driver, locator, this), this, SetOptions(options));
    }

    public ElementScope FindLink(String locator, Options options)
    {
        return new RobustElementScope(new LinkFinder(driver, locator, this), this, SetOptions(options));
    }

    public ElementScope FindField(String locator, Options options)
    {
        return new RobustElementScope(new FieldFinder(driver, locator, this), this, SetOptions(options));
    }

    public FillInWith FillIn(String locator, Options options)
    {
        return new FillInWith(locator, driver, robustWrapper, this, SetOptions(options));
    }

    public SelectFrom Select(String option, Options options)
    {
        return new SelectFrom(option, driver, robustWrapper, this, SetOptions(options));
    }

    public boolean HasContent(String text, Options options) throws MissingHtmlException {
        return Query(new HasContentQuery(driver, this, text, SetOptions(options)));
    }

    public boolean HasContentMatch(Pattern pattern, Options options) throws MissingHtmlException {
        return Query(new HasContentMatchQuery(driver, this, pattern, SetOptions(options)));
    }

    public boolean HasNoContent(String text, Options options) throws MissingHtmlException {
        return Query(new HasNoContentQuery(driver, this, text, SetOptions(options)));
    }

    public boolean HasNoContentMatch(Pattern pattern, Options options) throws MissingHtmlException {
        return Query(new HasNoContentMatchQuery(driver, this, pattern, SetOptions(options)));
    }

    public boolean HasCss(String cssSelector, Options options) throws MissingHtmlException {
        return Query(new HasCssQuery(driver, this, cssSelector, SetOptions(options)));
    }

    public boolean HasNoCss(String cssSelector, Options options) throws MissingHtmlException {
        return Query(new HasNoCssQuery(driver, this, cssSelector, SetOptions(options)));
    }

    public boolean HasXPath(String xpath, Options options) throws MissingHtmlException {
        return Query(new HasXPathQuery(driver, this, xpath, SetOptions(options)));
    }

    public boolean HasNoXPath(String xpath, Options options) throws MissingHtmlException {
        return Query(new HasNoXPathQuery(driver, this, xpath, SetOptions(options)));
    }

    public ElementScope FindCss(String cssSelector, Options options)
    {
        return new RobustElementScope(new CssFinder(driver, cssSelector, this), this , SetOptions(options));
    }

    public ElementScope FindXPath(String xpath, Options options)
    {
        return new RobustElementScope(new XPathFinder(driver, xpath, this), this, SetOptions(options));
    }

    public List<ElementFound> FindAllCss(String cssSelector, Options options) throws MissingHtmlException {
        SetOptions(options);
        return driver.FindAllCss(cssSelector, this);
    }

    public List<ElementFound> FindAllXPath(String xpath, Options options) throws MissingHtmlException {
        SetOptions(options);
        return driver.FindAllXPath(xpath, this);
    }

    public ElementScope FindSection(String locator, Options options)
    {
        return new RobustElementScope(new SectionFinder(driver, locator, this), this, SetOptions(options));
    }

    public ElementScope FindFieldset(String locator, Options options)
    {
        return new RobustElementScope(new FieldsetFinder(driver, locator, this), this, SetOptions(options));
    }

    public ElementScope FindId(String id, Options options)
    {
        return new RobustElementScope(new IdFinder(driver, id, this), this, SetOptions(options));
    }

    public void Check(String locator, Options options) throws MissingHtmlException {
        RetryUntilTimeout(new Check(driver, this, locator, SetOptions(options)));
    }

    public void Uncheck(String locator, Options options) throws MissingHtmlException {
        RetryUntilTimeout(new Uncheck(driver, this, locator, SetOptions(options)));
    }

    public void Choose(String locator, Options options) throws MissingHtmlException {
        RetryUntilTimeout(new Choose(driver, this, locator, SetOptions(options)));
    }

    public String ExecuteScript(String javascript) throws MissingHtmlException {
        return driver.ExecuteScript(javascript,this);
    }

    public DriverScope Hover(Options options) throws MissingHtmlException {
        RetryUntilTimeout(new Hover(this, driver, SetOptions(options)));
        return this;
    }

    public boolean Has(ElementScope findElement, Options options) throws MissingHtmlException {
        return findElement.Exists(options);
    }

    public boolean HasNo(ElementScope findElement, Options options) throws MissingHtmlException {
        return findElement.Missing(options);
    }

    public void RetryUntilTimeout(BrowserAction action) throws MissingHtmlException {
        Query(action);
    }

    public IFrameElementScope FindIFrame(String locator, Options options)
    {
        return new IFrameElementScope(new IFrameFinder(driver, locator, this), this, SetOptions(options));
    }

    public void TryUntil(BrowserAction tryThis, PredicateQuery until,  TimeSpan waitBeforeRetry, Options options) throws MissingHtmlException, TimeoutException {
        robustWrapper.TryUntil(tryThis, until, SetOptions(options).Timeout, waitBeforeRetry);
    }

    public State FindState(State[] states, Options options) throws TimeoutException, MissingHtmlException {
        return stateFinder.FindState(SetOptions(options), states);
    }

    /// <summary>
    /// Try and find this scope now
    /// </summary>
    /// <returns></returns>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    public ElementFound Now() throws MissingHtmlException {
        return FindElement();
    }

    public ElementFound FindElement() throws MissingHtmlException {
        if (element == null || element.Stale())
          element = elementFinder.Find();

        return element;
    }

    public <T> T Query(Query<T> query) throws MissingHtmlException {
        return robustWrapper.Robustly(query);
    }
}
