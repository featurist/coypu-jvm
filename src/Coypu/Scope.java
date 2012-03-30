package Coypu;

import Coypu.Actions.BrowserAction;
import Coypu.Queries.PredicateQuery;
import Coypu.Queries.Query;

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
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    void ClickButton(String locator);

    void ClickButton(String locator, Options options);

    /// <summary>
    /// Click the first matching link
    /// </summary>
    /// <param name="locator">The text of the link</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    void ClickLink(String locator);

    void ClickLink(String locator, Options options);

    /// <summary>
    /// Find the first input of type button|submit|image or div with the css class "button" to appear within the configured timeout .
    /// </summary>
    /// <param name="locator">The text/value, name or id of the button</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>A button</returns>
    ElementScope FindButton(String locator);

    ElementScope FindButton(String locator, Options options);

    /// <summary>
    /// Find the first matching link to appear within the configured timeout
    /// </summary>
    /// <param name="locator">The text of the link</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>A link</returns>
    ElementScope FindLink(String locator);

    ElementScope FindLink(String locator, Options options);

    /// <summary>
    /// Find the first form field of any type to appear within the configured timeout
    /// </summary>
    /// <param name="locator">The text of the associated label element, the id or name, the placeholder text, the value of a radio button, the last part of the id (for asp.net forms testing)</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>A form field</returns>
    ElementScope FindField(String locator);

    ElementScope FindField(String locator, Options options);

    /// <summary>
    /// Find the first matching text field to appear within the configured timeout to fill in.
    /// </summary>
    /// <param name="locator">The text of the associated label element, the id or name, the placeholder text, the last part of the id (for asp.net forms testing)</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>With</returns>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    FillInWith FillIn(String locator);

    FillInWith FillIn(String locator, Options options);

    /// <summary>
    /// Select an option from a select element
    /// </summary>
    /// <param name="option">The text or value of the option to select</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>From</returns>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    SelectFrom Select(String option);

    SelectFrom Select(String option, Options options);

    /// <summary>
    /// Query whether text appears on the page within the configured timeout
    /// </summary>
    /// <param name="text">The exact text to find</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>Whether the text appears</returns>
    boolean HasContent(String text);

    boolean HasContent(String text, Options options);

    /// <summary>
    /// Query whether text appears on the page using a regular expression within the configured timeout
    /// </summary>
    /// <param name="pattern">The regular expression to match</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>Whether the page text matches</returns>
    boolean HasContentMatch(Pattern pattern);

    boolean HasContentMatch(Pattern pattern, Options options);

    /// <summary>
    /// Query whether text does not appear on the page. Returns as soon as the text does not appear, or when the <see cref="Configuration.Timeout"/> is reached.
    /// </summary>
    /// <param name="text">The exact text expected not to be found</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>Whether the text does not appear</returns>
    boolean HasNoContent(String text);

    boolean HasNoContent(String text, Options options);

    /// <summary>
    /// Query whether text does not appear on the page using a regular expression. Returns as soon as the text does not appear, or when the <see cref="Configuration.Timeout"/> is reached.
    /// </summary>
    /// <param name="pattern">The regular expression expected not to match</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>Whether the text does not appear</returns>
    boolean HasNoContentMatch(Pattern pattern);

    boolean HasNoContentMatch(Pattern pattern, Options options);

    /// <summary>
    /// Query whether an element matching a CSS selector appears on the page within the configured timeout
    /// </summary>
    /// <param name="cssSelector">CSS selector</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>Whether an element appears</returns>
    boolean HasCss(String cssSelector);

    boolean HasCss(String cssSelector, Options options);

    /// <summary>
    /// Query whether an element matching a CSS selector does not appear on the page. Returns as soon as the element does not appear, or when the <see cref="Configuration.Timeout"/> is reached.
    /// </summary>
    /// <param name="cssSelector">CSS selector</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>Whether an element does not appear</returns>
    boolean HasNoCss(String cssSelector);

    boolean HasNoCss(String cssSelector, Options options);

    /// <summary>
    /// Query whether an element matching an XPath query appears on the page within the configured timeout
    /// </summary>
    /// <param name="xpath">XPath query</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>Whether an element appears</returns>
    boolean HasXPath(String xpath);

    boolean HasXPath(String xpath, Options options);

    /// <summary>
    /// Query whether an element matching an XPath query appears on the page. Returns as soon as the element appears, or when the <see cref="Configuration.Timeout"/> is reached.
    /// </summary>
    /// <param name="xpath">XPath query</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>Whether an element appears</returns>
    boolean HasNoXPath(String xpath);

    boolean HasNoXPath(String xpath, Options options);

    /// <summary>
    /// Find an element matching a CSS selector
    /// </summary>
    /// <param name="cssSelector">CSS selector</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>The first matching element</returns>
    ElementScope FindCss(String cssSelector);

    ElementScope FindCss(String cssSelector, Options options);

    /// <summary>
    /// Find an element matching an XPath query
    /// </summary>
    /// <param name="xpath">XPath query</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>The first matching element</returns>
    ElementScope FindXPath(String xpath);

    ElementScope FindXPath(String xpath, Options options);

    /// <summary>
    /// Find all elements matching a CSS selector at the current moment. Does not wait until the <see cref="Configuration.Timeout"/> but returns as soon as the driver does.
    /// </summary>
    /// <param name="cssSelector">CSS selector</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>All matching elements</returns>
    List<ElementFound> FindAllCss(String cssSelector);

    List<ElementFound> FindAllCss(String cssSelector, Options options);

    /// <summary>
    /// Find all elements matching an XPath query at the current moment. Does not wait until the <see cref="Configuration.Timeout"/> but returns as soon as the driver does.
    /// </summary>
    /// <param name="xpath">XPath query</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>All matching elements</returns>
    List<ElementFound> FindAllXPath(String xpath);

    List<ElementFound> FindAllXPath(String xpath, Options options);

    /// <summary>
    /// <para>Find the first matching section to appear within the configured timeout.</para>
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
    /// <code>    FindSection("Search results")</code>
    /// </para>
    /// </summary>
    /// <param name="locator">The text of a child heading element or section id</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>An element</returns>
    ElementScope FindSection(String locator);

    ElementScope FindSection(String locator, Options options);

    /// <summary>
    /// <para>Find the first matching fieldset to appear within the configured timeout.</para>
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
    /// <code>    FindFieldset("Advanced search")</code>
    /// </para>
    /// </summary>
    /// <param name="locator">The text of a child legend element or fieldset id</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>An element</returns>
    ElementScope FindFieldset(String locator);

    ElementScope FindFieldset(String locator, Options options);

    /// <summary>
    /// <para>Find the first matching IFrame to appear within the configured timeout.</para>
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
    ///         FindIframe("myFrame")
    ///         FindIframe("My I Frame")
    ///         FindIframe("My Frame Header")
    /// </code>
    /// </para>
    /// </summary>
    /// <param name="locator">The text of a child legend element or fieldset id</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>An element</returns>
    IFrameElementScope FindIFrame(String locator);

    IFrameElementScope FindIFrame(String locator, Options options);

    /// <summary>
    /// Find the first matching element with specified id to appear within the configured timeout
    /// </summary>
    /// <param name="id">Element id</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>An element</returns>
    ElementScope FindId(String id);

    ElementScope FindId(String id, Options options);

    /// <summary>
    /// Check the first checkbox to appear within the configured timeout matching the text of the associated label element, the id, name or the last part of the id (for asp.net forms testing).
    /// </summary>
    /// <param name="locator">The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    void Check(String locator);

    void Check(String locator, Options options);

    /// <summary>
    /// Uncheck the first checkbox to appear within the configured timeout matching the text of the associated label element, the id, name or the last part of the id (for asp.net forms testing).
    /// </summary>
    /// <param name="locator">The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    void Uncheck(String locator);

    void Uncheck(String locator, Options options);

    /// <summary>
    /// Choose the first radio button to appear within the configured timeout matching the text of the associated label element, the id, the name, the value or the last part of the id (for asp.net forms testing).
    /// </summary>
    /// <param name="locator">The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    void Choose(String locator);

    void Choose(String locator, Options options);

    /// <summary>
    /// Executes custom javascript in the browser
    /// </summary>
    /// <param name="javascript">JavaScript to execute</param>
    /// <returns>Anything returned from the script</returns>
    String ExecuteScript(String javascript);

    /// <summary>
    /// Query whether an element appears within the configured timeout
    /// </summary>
    /// <param name="findElement">A function to find an element</param>
    boolean Has(ElementScope findElement);

    boolean Has(ElementScope findElement, Options options);

    /// <summary>
    /// Query whether an element does not appear. Returns as soon as the element does not appear or after the <see cref="Configuration.Timeout"/>
    /// </summary>
    /// <param name="findElement">A function to find an element</param>
    boolean HasNo(ElementScope findElement);

    boolean HasNo(ElementScope findElement, Options options);

    /// <summary>
    /// <para>Retry an action on any exception until it succeeds. Once the <see cref="Configuration.Timeout"/> is passed any exception will be rethrown.</para>
    /// <para>Waits for the <see cref="Configuration.RetryInterval"/> between retries</para>
    /// </summary>
    /// <param name="action">An action</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    void RetryUntilTimeout(BrowserAction action);

    /// <summary>
    /// <para>Execute a query repeatedly until either the expected result is returned or the <see cref="Configuration.Timeout"/> is passed.</para>
    /// <para>Once the <see cref="Configuration.Timeout"/> is passed any result will be returned or any exception will be rethrown.</para>
    /// <para>Waits for the <see cref="Configuration.RetryInterval"/> between retries.</para>
    /// </summary>
    /// <param name="query">A query</param>
    <T> T Query(Query<T> query);

    /// <summary>
    /// <para>Execute an action repeatedly until a condition is met.</para>
    /// <para>Allows the time specified in <paramref name="waitBeforeRetry"/> for the <paramref name="until"/> query to return the expected value before each retry.</para>
    /// <para>Once the <see cref="Configuration.Timeout"/> is passed a Coypu.MissingHtmlException will be thrown.</para>
    /// </summary>
    /// <param name="tryThis">The action to try</param>
    /// <param name="until">The condition to be met</param>
    /// <param name="waitBeforeRetry">How long to wait for the condition to be met before retrying</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the until condition is never met</exception>
    void TryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan waitBeforeRetry);
    void TryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan waitBeforeRetry, Options options);

    /// <summary>
    /// <para>Find the first from a list of possible states that your page may arrive at.</para>
    /// <para>Returns as soon as any of the possible states is found.</para>
    /// <para>E.g.:</para>
    ///
    /// <code>
    ///  var signedIn = new State(browser.HasContent("Signed in as:"));
    ///  var signedOut = new State(browser.HasContent("Please sign in"));
    ///
    ///  if (browser.FindState(signedIn,signedOut) == signedIn)
    ///  {
    ///    browser.ClickLink("Sign out");
    ///  }
    ///  </code>
    ///  </summary>
    /// <param name="states">The possible states you are expecting</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns></returns>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the none of the states are reached within the timeout</exception>
    State FindState(State... states);

    State FindState(State[] states, Options options);

    /// <summary>
    /// <para>Click a button, input of type button|submit|image or div with the css class "button".</para>
    /// <para>Wait for a condition to be satisfied for a specified time otherwise click and wait again.</para>
    /// <para>Continues until the expected condition is satisfied or the <see cref="Configuration.Timeout"/> is reached.</para>
    /// </summary>
    /// <param name="locator">The text/value, name or id of the button</param>
    /// <param name="until">The condition to be satisfied</param>
    /// <param name="waitBeforeRetry">How long to wait for the condition to be met before retrying</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>The first matching button</returns>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    DriverScope ClickButton(String locator, PredicateQuery until, TimeSpan waitBeforeRetry);

    DriverScope ClickButton(String locator, PredicateQuery until, TimeSpan waitBeforeRetry, Options options);

    /// <summary>
    /// <para>Click a link and wait for a condition to be satisfied for a specified time otherwise click and wait again.</para>
    /// <para>Continues until the expected condition is satisfied or the <see cref="Configuration.Timeout"/> is reached.</para>
    /// </summary>
    /// <param name="locator">The text of the link</param>
    /// <param name="until">The condition to be satisfied</param>
    /// <param name="waitBeforeRetry">How long to wait for the condition to be met before retrying</param>
    /// <param name="options">
    /// <para>Override the way Coypu is configured to find elements for this call only.</para>
    /// <para>E.g. A longer wait:</para>
    ///
    /// <code>new Options{Timeout = TimeSpan .FromSeconds(60)}</code></param>
    /// <returns>The first matching button</returns>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the element cannot be found</exception>
    DriverScope ClickLink(String locator, PredicateQuery until, TimeSpan waitBeforeRetry);

    DriverScope ClickLink(String locator, PredicateQuery until, TimeSpan waitBeforeRetry, Options options);

}
