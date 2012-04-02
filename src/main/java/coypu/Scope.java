package coypu;

import coypu.Actions.BrowserAction;
import coypu.Queries.PredicateQuery;
import coypu.Queries.Query;

import java.util.List;
import java.util.regex.Pattern;

/// <summary>
/// The scope for any browser interaction: a browser window, frame or element.
/// </summary>
public interface Scope {
    /// <summary>
    /// Click a button, input of type button|submit|image or div with the css class "button"
    /// </summary>
    /// <param name="locator">The text/value, name or id of the button</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    void clickButton(String locator);

    void clickButton(String locator, Options options);

    /// <summary>
    /// Click the first matching link
    /// </summary>
    /// <param name="locator">The text of the link</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    void clickLink(String locator);

    void clickLink(String locator, Options options);

    /// <summary>
    /// Find the first input of type button|submit|image or div with the css class "button" to appear within the configured getTimeout .
    /// </summary>
    /// <param name="locator">The text/value, name or id of the button</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>A button</returns>
    ElementScope findButton(String locator);

    ElementScope findButton(String locator, Options options);

    /// <summary>
    /// Find the first matching link to appear within the configured getTimeout
    /// </summary>
    /// <param name="locator">The text of the link</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>A link</returns>
    ElementScope findLink(String locator);

    ElementScope findLink(String locator, Options options);

    /// <summary>
    /// Find the first form field of any type to appear within the configured getTimeout
    /// </summary>
    /// <param name="locator">The text of the associated label element, the id or name, the placeholder text, the value of a radio button, the last part of the id (for asp.net forms testing)</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>A form field</returns>
    ElementScope findField(String locator);

    ElementScope findField(String locator, Options options);

    /// <summary>
    /// Find the first matching text field to appear within the configured getTimeout to fill in.
    /// </summary>
    /// <param name="locator">The text of the associated label element, the id or name, the placeholder text, the last part of the id (for asp.net forms testing)</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>With</returns>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    FillInWith fillIn(String locator);

    FillInWith fillIn(String locator, Options options);

    /// <summary>
    /// Select an option from a select element
    /// </summary>
    /// <param name="option">The text or value of the option to select</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>From</returns>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    SelectFrom select(String option);

    SelectFrom select(String option, Options options);

    /// <summary>
    /// Query whether text appears on the page within the configured getTimeout
    /// </summary>
    /// <param name="text">The exact text to find</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>Whether the text appears</returns>
    boolean hasContent(String text);

    boolean hasContent(String text, Options options);

    /// <summary>
    /// Query whether text appears on the page using a regular expression within the configured getTimeout
    /// </summary>
    /// <param name="pattern">The regular expression to match</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>Whether the page text matches</returns>
    boolean hasContentMatch(Pattern pattern);

    boolean hasContentMatch(Pattern pattern, Options options);

    /// <summary>
    /// Query whether text does not appear on the page. Returns as soon as the text does not appear, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
    /// </summary>
    /// <param name="text">The exact text expected not to be found</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>Whether the text does not appear</returns>
    boolean hasNoContent(String text);

    boolean hasNoContent(String text, Options options);

    /// <summary>
    /// Query whether text does not appear on the page using a regular expression. Returns as soon as the text does not appear, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
    /// </summary>
    /// <param name="pattern">The regular expression expected not to match</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>Whether the text does not appear</returns>
    boolean hasNoContentMatch(Pattern pattern);

    boolean hasNoContentMatch(Pattern pattern, Options options);

    /// <summary>
    /// Query whether an element matching a CSS selector appears on the page within the configured getTimeout
    /// </summary>
    /// <param name="cssSelector">CSS selector</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>Whether an element appears</returns>
    boolean hasCss(String cssSelector);

    boolean hasCss(String cssSelector, Options options);

    /// <summary>
    /// Query whether an element matching a CSS selector does not appear on the page. Returns as soon as the element does not appear, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
    /// </summary>
    /// <param name="cssSelector">CSS selector</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>Whether an element does not appear</returns>
    boolean hasNoCss(String cssSelector);

    boolean hasNoCss(String cssSelector, Options options);

    /// <summary>
    /// Query whether an element matching an XPath query appears on the page within the configured getTimeout
    /// </summary>
    /// <param name="xpath">XPath query</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>Whether an element appears</returns>
    boolean hasXPath(String xpath);

    boolean hasXPath(String xpath, Options options);

    /// <summary>
    /// Query whether an element matching an XPath query appears on the page. Returns as soon as the element appears, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
    /// </summary>
    /// <param name="xpath">XPath query</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>Whether an element appears</returns>
    boolean hasNoXPath(String xpath);

    boolean hasNoXPath(String xpath, Options options);

    /// <summary>
    /// Find an element matching a CSS selector
    /// </summary>
    /// <param name="cssSelector">CSS selector</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>The first matching element</returns>
    ElementScope findCss(String cssSelector);

    ElementScope findCss(String cssSelector, Options options);

    /// <summary>
    /// Find an element matching an XPath query
    /// </summary>
    /// <param name="xpath">XPath query</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>The first matching element</returns>
    ElementScope findXPath(String xpath);

    ElementScope findXPath(String xpath, Options options);

    /// <summary>
    /// Find all elements matching a CSS selector at the current moment. Does not wait until the <see cref="SessionConfiguration.Timeout"/> but returns as soon as the driver does.
    /// </summary>
    /// <param name="cssSelector">CSS selector</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>All matching elements</returns>
    List<ElementFound> findAllCss(String cssSelector);

    List<ElementFound> findAllCss(String cssSelector, Options options);

    /// <summary>
    /// Find all elements matching an XPath query at the current moment. Does not wait until the <see cref="SessionConfiguration.Timeout"/> but returns as soon as the driver does.
    /// </summary>
    /// <param name="xpath">XPath query</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>All matching elements</returns>
    List<ElementFound> findAllXPath(String xpath);

    List<ElementFound> findAllXPath(String xpath, Options options);

    /// <summary>
    /// <para>Find the first matching section to appear within the configured getTimeout.</para>
    /// <para>Sections are identified by the text of their child heading element, or by id.</para>
    /// <para>E.g. to find this:
    ///
    /// <code>
    ///     &lt;div&gt;
    ///         &lt;h2&gt;Search results&lt;/h2&gt;
    ///         ...
    ///     &lt;/div&gt;</code>
    ///
    /// or this:
    ///
    /// <code>
    ///     &lt;section&gt;
    ///         &lt;h3&gt;Search results&lt;/h3&gt;
    ///         ...
    ///     &lt;/section&gt;</code>
    /// </para>
    /// <para>use this:</para>
    /// <para>
    /// <code>    findSection("Search results")</code>
    /// </para>
    /// </summary>
    /// <param name="locator">The text of a child heading element or section id</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>An element</returns>
    ElementScope findSection(String locator);

    ElementScope findSection(String locator, Options options);

    /// <summary>
    /// <para>Find the first matching fieldset to appear within the configured getTimeout.</para>
    /// <para>Fieldsets are identified by the text of their child legend element, or by id.</para>
    /// <para>E.g. to find this:
    ///
    /// <code>
    ///     &lt;fieldset&gt;
    ///         &lt;legend&gt;Advanced search&lt;/legend&gt;
    ///         ...
    ///     &lt;/fieldset&gt;</code>
    /// </para>
    /// <para>use this:</para>
    /// <para>
    /// <code>    findFieldset("Advanced search")</code>
    /// </para>
    /// </summary>
    /// <param name="locator">The text of a child legend element or fieldset id</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>An element</returns>
    ElementScope findFieldset(String locator);

    ElementScope findFieldset(String locator, Options options);

    /// <summary>
    /// <para>Find the first matching IFrame to appear within the configured getTimeout.</para>
    /// <para>IFrames are identified by the their id or title attributes, or by the text of the &lt;h1&gt; element in their content.</para>
    /// <para>E.g. to find this:
    ///
    /// <code>
    ///     &lt;iframe id="myFrame" title="My I Frame" src="..."&gt;
    ///         &lt;h1&gt;My Frame Header&lt;/h1&gt;
    ///         ...
    ///     &lt;/iframe&gt;
    /// </code>
    /// </para>
    /// <para>use one of these:
    ///
    /// <code>
    ///         findIframe("myFrame")
    ///         findIframe("My I Frame")
    ///         findIframe("My Frame Header")
    /// </code>
    /// </para>
    /// </summary>
    /// <param name="locator">The text of a child legend element or fieldset id</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>An element</returns>
    IFrameElementScope findIFrame(String locator);

    IFrameElementScope findIFrame(String locator, Options options);

    /// <summary>
    /// Find the first matching element with specified id to appear within the configured getTimeout
    /// </summary>
    /// <param name="id">Element id</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>An element</returns>
    ElementScope findId(String id);

    ElementScope findId(String id, Options options);

    /// <summary>
    /// Check the first checkbox to appear within the configured getTimeout matching the text of the associated label element, the id, name or the last part of the id (for asp.net forms testing).
    /// </summary>
    /// <param name="locator">The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    void check(String locator);

    void check(String locator, Options options);

    /// <summary>
    /// Uncheck the first checkbox to appear within the configured getTimeout matching the text of the associated label element, the id, name or the last part of the id (for asp.net forms testing).
    /// </summary>
    /// <param name="locator">The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    void uncheck(String locator);

    void uncheck(String locator, Options options);

    /// <summary>
    /// Choose the first radio button to appear within the configured getTimeout matching the text of the associated label element, the id, the name, the value or the last part of the id (for asp.net forms testing).
    /// </summary>
    /// <param name="locator">The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    void choose(String locator);

    void choose(String locator, Options options);

    /// <summary>
    /// Executes custom javascript in the browser
    /// </summary>
    /// <param name="javascript">JavaScript to execute</param>
    /// <returns>Anything returned from the script</returns>
    String executeScript(String javascript);

    /// <summary>
    /// Query whether an element appears within the configured getTimeout
    /// </summary>
    /// <param name="findElement">A function to find an element</param>
    boolean has(ElementScope findElement);

    boolean has(ElementScope findElement, Options options);

    /// <summary>
    /// Query whether an element does not appear. Returns as soon as the element does not appear or after the <see cref="SessionConfiguration.Timeout"/>
    /// </summary>
    /// <param name="findElement">A function to find an element</param>
    boolean hasNo(ElementScope findElement);

    boolean hasNo(ElementScope findElement, Options options);

    /// <summary>
    /// <para>Retry an action on any exception until it succeeds. Once the <see cref="SessionConfiguration.Timeout"/> is passed any exception will be rethrown.</para>
    /// <para>Waits for the <see cref="SessionConfiguration.RetryInterval"/> between retries</para>
    /// </summary>
    /// <param name="action">An action</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    void retryUntilTimeout(BrowserAction action);

    /// <summary>
    /// <para>Execute a query repeatedly until either the expected result is returned or the <see cref="SessionConfiguration.Timeout"/> is passed.</para>
    /// <para>Once the <see cref="SessionConfiguration.Timeout"/> is passed any result will be returned or any exception will be rethrown.</para>
    /// <para>Waits for the <see cref="SessionConfiguration.RetryInterval"/> between retries.</para>
    /// </summary>
    /// <param name="query">A query</param>
    <T> T query(Query<T> query);

    /// <summary>
    /// <para>Execute an action repeatedly until a condition is met.</para>
    /// <para>Allows the time specified in <paramref name="waitBeforeRetry"/> for the <paramref name="until"/> query to return the expected value before each retry.</para>
    /// <para>Once the <see cref="SessionConfiguration.Timeout"/> is passed a coypu.MissingHtmlException will be thrown.</para>
    /// </summary>
    /// <param name="tryThis">The action to try</param>
    /// <param name="until">The condition to be met</param>
    /// <param name="waitBeforeRetry">How long to wait for the condition to be met before retrying</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the until condition is never met</exception>
    void tryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan waitBeforeRetry);
    void tryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan waitBeforeRetry, Options options);

    /// <summary>
    /// <para>Find the first from a list of possible states that your page may arrive at.</para>
    /// <para>Returns as soon as any of the possible states is found.</para>
    /// <para>E.g.:</para>
    ///
    /// <code>
    ///  var signedIn = new State(browser.hasContent("Signed in as:"));
    ///  var signedOut = new State(browser.hasContent("Please sign in"));
    ///
    ///  if (browser.findState(signedIn,signedOut) == signedIn)
    ///  {
    ///    browser.clickLink("Sign out");
    ///  }
    ///  </code>
    ///  </summary>
    /// <param name="states">The possible states you are expecting</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns></returns>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the none of the states are reached within the getTimeout</exception>
    State findState(State... states);

    State findState(State[] states, Options options);

    /// <summary>
    /// <para>Click a button, input of type button|submit|image or div with the css class "button".</para>
    /// <para>Wait for a condition to be satisfied for a specified time otherwise click and wait again.</para>
    /// <para>Continues until the expected condition is satisfied or the <see cref="SessionConfiguration.Timeout"/> is reached.</para>
    /// </summary>
    /// <param name="locator">The text/value, name or id of the button</param>
    /// <param name="until">The condition to be satisfied</param>
    /// <param name="waitBeforeRetry">How long to wait for the condition to be met before retrying</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>The first matching button</returns>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    DriverScope clickButton(String locator, PredicateQuery until, TimeSpan waitBeforeRetry);

    DriverScope clickButton(String locator, PredicateQuery until, TimeSpan waitBeforeRetry, Options options);

    /// <summary>
    /// <para>Click a link and wait for a condition to be satisfied for a specified time otherwise click and wait again.</para>
    /// <para>Continues until the expected condition is satisfied or the <see cref="SessionConfiguration.Timeout"/> is reached.</para>
    /// </summary>
    /// <param name="locator">The text of the link</param>
    /// <param name="until">The condition to be satisfied</param>
    /// <param name="waitBeforeRetry">How long to wait for the condition to be met before retrying</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code></param>
    /// <returns>The first matching button</returns>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    DriverScope clickLink(String locator, PredicateQuery until, TimeSpan waitBeforeRetry);

    DriverScope clickLink(String locator, PredicateQuery until, TimeSpan waitBeforeRetry, Options options);

}
