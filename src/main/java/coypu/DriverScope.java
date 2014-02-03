//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:16
//

package coypu;

import coypu.Actions.BrowserAction;
import coypu.Actions.CheckAction;
import coypu.Actions.Choose;
import coypu.Actions.LambdaBrowserAction;
import coypu.Actions.Uncheck;
import coypu.Actions.WaitThenClick;
import coypu.Driver;
import coypu.Drivers.Browser;
import coypu.DriverScope;
import coypu.ElementFound;
import coypu.ElementScope;
import coypu.FillInWith;
import coypu.Finders.ButtonFinder;
import coypu.Finders.CssFinder;
import coypu.Finders.DisambiguationStrategy;
import coypu.Finders.DocumentElementFinder;
import coypu.Finders.ElementFinder;
import coypu.Finders.FieldFinder;
import coypu.Finders.FieldsetFinder;
import coypu.Finders.FinderOptionsDisambiguationStrategy;
import coypu.Finders.FrameFinder;
import coypu.Finders.IdFinder;
import coypu.Finders.LinkFinder;
import coypu.Finders.SectionFinder;
import coypu.Finders.StateFinder;
import coypu.Finders.XPathFinder;
import coypu.Options;
import coypu.Queries.FindAllCssWithPredicateQuery;
import coypu.Queries.FindAllXPathWithPredicateQuery;
import coypu.Queries.HasContentMatchQuery;
import coypu.Queries.HasContentQuery;
import coypu.Queries.HasNoContentMatchQuery;
import coypu.Queries.HasNoContentQuery;
import coypu.Queries.LambdaPredicateQuery;
import coypu.Queries.LambdaQuery;
import coypu.Queries.PredicateQuery;
import coypu.Queries.Query;
import coypu.Scope;
import coypu.SelectFrom;
import coypu.SessionConfiguration;
import coypu.SnapshotElementScope;
import coypu.State;
import coypu.Timing.TimingStrategy;
import coypu.Timing.Waiter;
import coypu.UrlBuilder;
import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.TimeSpan;
import java.net.URI;
import java.util.regex.Pattern;

public class DriverScope   implements Scope
{
    protected final SessionConfiguration SessionConfiguration;
    public final ElementFinder elementFinder;
    protected Driver driver;
    protected TimingStrategy timingStrategy;
    protected final Waiter waiter;
    public UrlBuilder urlBuilder;
    public StateFinder stateFinder;
    private ElementFound element;
    private final DriverScope outerScope;
    private final DisambiguationStrategy disambiguationStrategy = new FinderOptionsDisambiguationStrategy();
    public DriverScope(SessionConfiguration sessionConfiguration, ElementFinder elementFinder, Driver driver, TimingStrategy timingStrategy, Waiter waiter, UrlBuilder urlBuilder, DisambiguationStrategy disambiguationStrategy) throws Exception {
        this.elementFinder = elementFinder != null ? elementFinder : new DocumentElementFinder(driver, sessionConfiguration);
        this.SessionConfiguration = sessionConfiguration;
        this.driver = driver;
        this.timingStrategy = timingStrategy;
        this.waiter = waiter;
        this.urlBuilder = urlBuilder;
        this.disambiguationStrategy = disambiguationStrategy;
        stateFinder = new StateFinder(timingStrategy);
    }

    public DriverScope(ElementFinder elementFinder, DriverScope outerScope) throws Exception {
        this.elementFinder = elementFinder;
        this.outerScope = outerScope;
        driver = outerScope.driver;
        timingStrategy = outerScope.timingStrategy;
        urlBuilder = outerScope.urlBuilder;
        disambiguationStrategy = outerScope.disambiguationStrategy;
        stateFinder = outerScope.stateFinder;
        waiter = outerScope.waiter;
        SessionConfiguration = outerScope.SessionConfiguration;
    }

    public DriverScope getOuterScope() throws Exception {
        return outerScope;
    }

    public URI getLocation() throws Exception {
        return driver.location(this);
    }

    public String getText() throws Exception {
        return now().getText();
    }

    public Browser getBrowser() throws Exception {
        return SessionConfiguration.getBrowser();
    }

    public ElementFinder getElementFinder() throws Exception {
        return elementFinder;
    }

    public Options merge(Options options) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ mergeWith = getElementFinder() != null ? getElementFinder().getOptions() : SessionConfiguration;
        return Options.Merge(options, mergeWith);
    }

    public void clickButton(String locator, Options options) throws Exception {
        retryUntilTimeout(waitThenClickButton(locator,merge(options)));
    }

    public void clickLink(String locator, Options options) throws Exception {
        retryUntilTimeout(waitThenClickLink(locator,merge(options)));
    }

    private WaitThenClick waitThenClickLink(String locator, Options options) throws Exception {
        return new WaitThenClick(driver, merge(options), waiter, new LinkFinder(driver, locator, this, merge(options)), disambiguationStrategy);
    }

    private WaitThenClick waitThenClickButton(String locator, Options options) throws Exception {
        return new WaitThenClick(driver, merge(options), waiter, new ButtonFinder(driver, locator, this, merge(options)), disambiguationStrategy);
    }

    public Scope clickButton(String locator, PredicateQuery until, Options options) throws Exception {
        tryUntil(waitThenClickButton(locator,merge(options)),until,merge(options));
        return this;
    }

    public Scope clickLink(String locator, PredicateQuery until, Options options) throws Exception {
        tryUntil(waitThenClickLink(locator,merge(options)),until,merge(options));
        return this;
    }

    public ElementScope findButton(String locator, Options options) throws Exception {
        return (new ButtonFinder(driver, locator, this, merge(options))).asScope();
    }

    public ElementScope findLink(String locator, Options options) throws Exception {
        return (new LinkFinder(driver, locator, this, merge(options))).asScope();
    }

    public ElementScope findField(String locator, Options options) throws Exception {
        return (new FieldFinder(driver, locator, this, merge(options))).asScope();
    }

    public FillInWith fillIn(String locator, Options options) throws Exception {
        return new FillInWith(findField(locator,options),driver,timingStrategy,merge(options));
    }

    public SelectFrom select(String option, Options options) throws Exception {
        return new SelectFrom(option,driver,timingStrategy,this,merge(options),disambiguationStrategy);
    }

    public boolean hasContent(String text, Options options) throws Exception {
        return Query(new HasContentQuery(this, text, merge(options)));
    }

    public boolean hasContentMatch(Pattern pattern, Options options) throws Exception {
        return Query(new HasContentMatchQuery(this, pattern, merge(options)));
    }

    public boolean hasNoContent(String text, Options options) throws Exception {
        return Query(new HasNoContentQuery(this, text, merge(options)));
    }

    public boolean hasNoContentMatch(Pattern pattern, Options options) throws Exception {
        return Query(new HasNoContentMatchQuery(this, pattern, merge(options)));
    }

    public ElementScope findCss(String cssSelector, Options options) throws Exception {
        return (new CssFinder(driver, cssSelector, this, merge(options))).asScope();
    }

    public ElementScope findCss(String cssSelector, String text, Options options) throws Exception {
        return (new CssFinder(driver, cssSelector, this, merge(options), text)).asScope();
    }

    public ElementScope findCss(String cssSelector, Pattern text, Options options) throws Exception {
        return (new CssFinder(driver, cssSelector, this, merge(options), text)).asScope();
    }

    public ElementScope findXPath(String xpath, Options options) throws Exception {
        return (new XPathFinder(driver, xpath, this, merge(options))).asScope();
    }

    public boolean hasCss(String cssSelector, String text, Options options) throws Exception {
        return findCss(cssSelector,text,options).exists();
    }

    public boolean hasCss(String cssSelector, Pattern text, Options options) throws Exception {
        return findCss(cssSelector,text,options).exists();
    }

    public boolean hasXPath(String xpath, Options options) throws Exception {
        return findXPath(xpath,options).exists();
    }

    public boolean hasNoCss(String cssSelector, String text, Options options) throws Exception {
        return findCss(cssSelector,text,options).missing();
    }

    public boolean hasNoCss(String cssSelector, Pattern text, Options options) throws Exception {
        return findCss(cssSelector,text,options).missing();
    }

    public boolean hasNoXPath(String xpath, Options options) throws Exception {
        return findXPath(xpath,options).missing();
    }

    public IEnumerable<SnapshotElementScope> findAllCss(String cssSelector, Func<IEnumerable<SnapshotElementScope>, Boolean> predicate, Options options) throws Exception {
        if (predicate != null)
            return Query(new FindAllCssWithPredicateQuery(cssSelector, predicate, this, merge(options)));
         
        return findAllCssNoPredicate(cssSelector,merge(options));
    }

    public IEnumerable<SnapshotElementScope> findAllCssNoPredicate(String cssSelector, Options options) throws Exception {
        return driver.FindAllCss(cssSelector, this, options).AsSnapshotElementScopes(this, options);
    }

    public IEnumerable<SnapshotElementScope> findAllXPath(String xpath, Func<IEnumerable<SnapshotElementScope>, Boolean> predicate, Options options) throws Exception {
        if (predicate != null)
            return Query(new FindAllXPathWithPredicateQuery(xpath, predicate, this, merge(options)));
         
        return findAllXPathNoPredicate(xpath,merge(options));
    }

    public IEnumerable<SnapshotElementScope> findAllXPathNoPredicate(String xpath, Options options) throws Exception {
        return driver.findAllXPath(xpath,this,options).AsSnapshotElementScopes(this, options);
    }

    public ElementScope findSection(String locator, Options options) throws Exception {
        return (new SectionFinder(driver, locator, this, merge(options))).asScope();
    }

    public ElementScope findFieldset(String locator, Options options) throws Exception {
        return (new FieldsetFinder(driver, locator, this, merge(options))).asScope();
    }

    public ElementScope findId(String id, Options options) throws Exception {
        return (new IdFinder(driver, id, this, merge(options))).asScope();
    }

    public void check(String locator, Options options) throws Exception {
        retryUntilTimeout(new CheckAction(driver, findField(locator,options), merge(options)));
    }

    public void uncheck(String locator, Options options) throws Exception {
        retryUntilTimeout(new Uncheck(driver, findField(locator,options), merge(options)));
    }

    public void choose(String locator, Options options) throws Exception {
        retryUntilTimeout(new Choose(driver, findField(locator,options), merge(options)));
    }

    public void retryUntilTimeout(Action action, Options options) throws Exception {
        timingStrategy.Synchronise(new LambdaBrowserAction(action, merge(options)));
    }

    public <TResult>TResult retryUntilTimeout(Func<TResult> function, Options options) throws Exception {
        return timingStrategy.Synchronise(new LambdaQuery<TResult>(function, merge(options)));
    }

    public void retryUntilTimeout(BrowserAction action) throws Exception {
        Query(action);
    }

    public ElementScope findFrame(String locator, Options options) throws Exception {
        return (new FrameFinder(driver, locator, this, merge(options))).asScope();
    }

    public <T>T query(Func<T> query, T expecting, Options options) throws Exception {
        return timingStrategy.synchronise(new LambdaQuery<T>(query, expecting, merge(options)));
    }

    public <T>T query(Query<T> query) throws Exception {
        return timingStrategy.synchronise(query);
    }

    public void tryUntil(Action tryThis, Func<Boolean> until, TimeSpan waitBeforeRetry, Options options) throws Exception {
        Options mergedOptions = merge(options);
        Options predicateOptions = Options.merge(new Options(),mergedOptions);
        timingStrategy.TryUntil(new LambdaBrowserAction(tryThis, mergedOptions), new LambdaPredicateQuery(WithZeroTimeout(until), predicateOptions), mergedOptions);
    }

    private Func<Boolean> withZeroTimeout(Func<Boolean> query) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ zeroTimeoutUntil = new Func<Boolean>(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "() => {
            Boolean was = timingStrategy.getZeroTimeout();
            timingStrategy.setZeroTimeout(true);
            try
            {
                return query();
            }
            finally
            {
                timingStrategy.setZeroTimeout(was);
            }
        }" */);
        return zeroTimeoutUntil;
    }

    public void tryUntil(BrowserAction tryThis, PredicateQuery until, Options options) throws Exception {
        timingStrategy.TryUntil(tryThis, until, merge(options));
    }

    public State findState(State[] states, Options options) throws Exception {
        return stateFinder.FindState(states, this, merge(options));
    }

    public State findState(State... states) throws Exception {
        return findState(states,null);
    }

    /**
    * Try and find this scope now
    * 
    *  @return 
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    *  @throws T:Coypu.AmbiguousHtmlException Thrown if the there is more than one matching element and the Match.Single option is set
    */
    public ElementFound now() throws Exception {
        return findElement();
    }

    protected public ElementFound findElement() throws Exception {
        if (element == null || element.Stale(getElementFinder().getOptions()))
            element = disambiguationStrategy.resolveQuery(getElementFinder());
         
        return element;
    }

}


