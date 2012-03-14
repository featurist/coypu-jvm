package Coypu;

import Coypu.Actions.*;
import Coypu.Finders.ElementFinder;
import Coypu.Robustness.Waiter;
import com.sun.jndi.toolkit.url.Uri;

public class DriverScope implements Coypu.Scope
{
    protected Configuration configuration;
    private ElementFinder elementFinder;
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
        driver = outer.driver;
        robustWrapper = outer.robustWrapper;
        urlBuilder = outer.urlBuilder;
        stateFinder = outer.stateFinder;
        waiter = outer.waiter;
        options = outer.configuration;
        configuration = outer.configuration;
    }

    public Uri Location()
    {
        return driver.Location();
    }

    public boolean SetConsiderInvisibleElements()
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

    public void ClickButton(String locator, Options options)
    {
        RetryUntilTimeout(WaitThenClickButton(locator, SetOptions(options)));
    }

    public void ClickLink(String locator, Options options)
    {
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

    public DriverScope ClickButton(String locator, PredicateQuery until,  TimeSpan waitBeforeRetry, Options options)
    {
        options = SetOptions(options);
        TryUntil(WaitThenClickButton(locator, options), until, waitBeforeRetry, options);
        return this;
    }

    public DriverScope ClickLink(String locator, PredicateQuery until,  TimeSpan waitBeforeRetry, Options options)
    {
        options = SetOptions(options);
        TryUntil(WaitThenClickLink(locator, options), until, waitBeforeRetry, options);
        return this;
    }

    public ElementScope FindButton(String locator, Options options)
    {
        return new RobustElementScope(new ButtonFinder(driver, locator, this), this, robustWrapper, SetOptions(options));
    }

    public ElementScope FindLink(String locator, Options options)
    {
        return new RobustElementScope(new LinkFinder(driver, locator, this), this, robustWrapper, SetOptions(options));
    }

    public ElementScope FindField(String locator, Options options)
    {
        return new RobustElementScope(new FieldFinder(driver, locator, this), this, robustWrapper, SetOptions(options));
    }

    public FillInWith FillIn(String locator, Options options)
    {
        return new FillInWith(locator, driver, robustWrapper, this, SetOptions(options));
    }

    public SelectFrom Select(String option, Options options)
    {
        return new SelectFrom(option, driver, robustWrapper, this, SetOptions(options));
    }

    public boolean HasContent(String text, Options options)
    {
        return Query(new HasContentQuery(driver, this, text, SetOptions(options)));
    }

    public boolean HasContentMatch(Regex pattern, Options options)
    {
        return Query(new HasContentMatchQuery(driver, this, pattern, SetOptions(options)));
    }

    public boolean HasNoContent(String text, Options options)
    {
        return Query(new HasNoContentQuery(driver, this, text, SetOptions(options)));
    }

    public boolean HasNoContentMatch(Regex pattern, Options options)
    {
        return Query(new HasNoContentMatchQuery(driver, this, pattern, SetOptions(options)));
    }

    public boolean HasCss(String cssSelector, Options options)
    {
        return Query(new HasCssQuery(driver, this, cssSelector, SetOptions(options)));
    }

    public boolean HasNoCss(String cssSelector, Options options)
    {
        return Query(new HasNoCssQuery(driver, this, cssSelector, SetOptions(options)));
    }

    public boolean HasXPath(String xpath, Options options)
    {
        return Query(new HasXPathQuery(driver, this, xpath, SetOptions(options)));
    }

    public boolean HasNoXPath(String xpath, Options options)
    {
        return Query(new HasNoXPathQuery(driver, this, xpath, SetOptions(options)));
    }

    public ElementScope FindCss(String cssSelector, Options options)
    {
        return new RobustElementScope(new CssFinder(driver, cssSelector, this), this, robustWrapper, SetOptions(options));
    }

    public ElementScope FindXPath(String xpath, Options options)
    {
        return new RobustElementScope(new XPathFinder(driver, xpath, this), this, robustWrapper, SetOptions(options));
    }

    public IEnumerable<ElementFound> FindAllCss(String cssSelector, Options options)
    {
        SetOptions(options);
        return driver.FindAllCss(cssSelector, this);
    }

    public IEnumerable<ElementFound> FindAllXPath(String xpath, Options options)
    {
        SetOptions(options);
        return driver.FindAllXPath(xpath, this);
    }

    public ElementScope FindSection(String locator, Options options)
    {
        return new RobustElementScope(new SectionFinder(driver, locator, this), this, robustWrapper, SetOptions(options));
    }

    public ElementScope FindFieldset(String locator, Options options)
    {
        return new RobustElementScope(new FieldsetFinder(driver, locator, this), this, robustWrapper, SetOptions(options));
    }

    public ElementScope FindId(String id, Options options)
    {
        return new RobustElementScope(new IdFinder(driver, id, this), this, robustWrapper, SetOptions(options));
    }

    public void Check(String locator, Options options)
    {
        RetryUntilTimeout(new Check(driver, this, locator, SetOptions(options)));
    }

    public void Uncheck(String locator, Options options)
    {
        RetryUntilTimeout(new Uncheck(driver, this, locator, SetOptions(options)));
    }

    public void Choose(String locator, Options options)
    {
        RetryUntilTimeout(new Choose(driver, this, locator, SetOptions(options)));
    }

    public String ExecuteScript(String javascript)
    {
        return driver.ExecuteScript(javascript,this);
    }

    public DriverScope Hover(Options options)
    {
        RetryUntilTimeout(new Hover(this, driver, SetOptions(options)));
        return this;
    }

    public boolean Has(ElementScope findElement, Options options)
    {
        return findElement.Exists(options);
    }

    public boolean HasNo(ElementScope findElement, Options options)
    {
        return findElement.Missing(options);
    }

    public void RetryUntilTimeout(BrowserAction action)
    {
        Query(action);
    }

    public IFrameElementScope FindIFrame(String locator, Options options)
    {
        return new IFrameElementScope(new IFrameFinder(driver, locator, this), this, robustWrapper, SetOptions(options));
    }

    public void TryUntil(BrowserAction tryThis, PredicateQuery until,  TimeSpan waitBeforeRetry, Options options)
    {
        robustWrapper.TryUntil(tryThis, until, SetOptions(options).Timeout, waitBeforeRetry);
    }

    public State FindState(State[] states, Options options)
    {
        return stateFinder.FindState(SetOptions(options), states);
    }

    public State FindState(params State[] states)
    {
        return stateFinder.FindState(SetOptions(options), states);
    }

    /// <summary>
    /// Try and find this scope now
    /// </summary>
    /// <returns></returns>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    public ElementFound Now()
    {
        return FindElement();
    }

    protected ElementFound FindElement()
    {
        if (element == null || element.Stale())
          element = elementFinder.Find();

        return element;
    }

    public T Query<T>(Query<T> query)
    {
        return robustWrapper.Robustly(query);
    }
}