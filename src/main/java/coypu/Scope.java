package coypu;

import coypu.Actions.BrowserAction;
import coypu.Queries.PredicateQuery;
import coypu.Queries.Query;

import java.util.List;
import java.util.regex.Pattern;

/// <summary>
/// The scope for any browser interaction: a browser window, frame or element.
///
public interface Scope {
   /**
    *  Click a button, input of type button|submit|image or div with the css class "button"
    *
    *  @param   locator    The text/value, name or id of the button
    */
    void clickButton(String locator);
    /**
     *  Click a button, input of type button|submit|image or div with the css class "button"
     *
     *  @param   locator    The text/value, name or id of the button
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     */
    void clickButton(String locator, Options options);

   /**
    *  Click the first matching link
    *
    *  @param   locator    The text of the link
    */
    void clickLink(String locator);
    /**
     *  Click the first matching link
     *
     *  @param   locator    The text of the link
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     */
    void clickLink(String locator, Options options);

   /**
    *  Find the first input of type button|submit|image or div with the css class "button" to appear within the configured getTimeout .
    *
    *  @param   locator    The text/value, name or id of the button
    *  @return                    A button
    */
    ElementScope findButton(String locator);
    /**
     *  Find the first input of type button|submit|image or div with the css class "button" to appear within the configured getTimeout .
     *
     *  @param   locator    The text/value, name or id of the button
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    A button
     */
    ElementScope findButton(String locator, Options options);

   /**
    *  Find the first matching link to appear within the configured getTimeout
    *
    *  @param   locator    The text of the link
    *  <p>Override the way Coypu is configured to find elements for this call only.
    *  <p>E.g. A longer wait:
    *
    *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
    *  @return                    A link
    */
    ElementScope findLink(String locator);
    /**
     *  Find the first matching link to appear within the configured getTimeout
     *
     *  @param   locator    The text of the link
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    A link
     */
    ElementScope findLink(String locator, Options options);

   /**
    *  Find the first form field of any type to appear within the configured getTimeout
    *
    *  @param   locator    The text of the associated label element, the id or name, the placeholder text, the value of a radio button, the last part of the id (for asp.net forms testing)
    *  @return                    A form field
    */
    ElementScope findField(String locator);
    /**
     *  Find the first form field of any type to appear within the configured getTimeout
     *
     *  @param   locator    The text of the associated label element, the id or name, the placeholder text, the value of a radio button, the last part of the id (for asp.net forms testing)
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    A form field
     */
    ElementScope findField(String locator, Options options);

   /**
    *  Find the first matching text field to appear within the configured getTimeout to fill in.
    *
    *  @param   locator    The text of the associated label element, the id or name, the placeholder text, the last part of the id (for asp.net forms testing)
    *  @return                    With
    */
    FillInWith fillIn(String locator);
    /**
     *  Find the first matching text field to appear within the configured getTimeout to fill in.
     *
     *  @param   locator    The text of the associated label element, the id or name, the placeholder text, the last part of the id (for asp.net forms testing)
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    With
     */
    FillInWith fillIn(String locator, Options options);

   /**
    *  Select an option from a select element
    *
    *  @param   option    The text or value of the option to select
    *  @return                    From
    */
    SelectFrom select(String option);
    /**
     *  Select an option from a select element
     *
     *  @param   option    The text or value of the option to select
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    From
     */
    SelectFrom select(String option, Options options);

   /**
    *  Query whether text appears on the page within the configured getTimeout
    *
    *  @param   text    The exact text to find
    *  @return                    Whether the text appears
    */
    boolean hasContent(String text);
    /**
     *  Query whether text appears on the page within the configured getTimeout
     *
     *  @param   text    The exact text to find
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    Whether the text appears
     */
    boolean hasContent(String text, Options options);

   /**
    *  Query whether text appears on the page using a regular expression within the configured getTimeout
    *
    *  @param   pattern    The regular expression to match
    *  @return                    Whether the page text matches
    */
    boolean hasContentMatch(Pattern pattern);
    /**
     *  Query whether text appears on the page using a regular expression within the configured getTimeout
     *
     *  @param   pattern    The regular expression to match
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    Whether the page text matches
     */
    boolean hasContentMatch(Pattern pattern, Options options);

   /**
    *  Query whether text does not appear on the page. Returns as soon as the text does not appear, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
    *
    *  @param   text    The exact text expected not to be found
    *  @return                    Whether the text does not appear
    */
    boolean hasNoContent(String text);
    /**
     *  Query whether text does not appear on the page. Returns as soon as the text does not appear, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
     *
     *  @param   text    The exact text expected not to be found
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    Whether the text does not appear
     */
    boolean hasNoContent(String text, Options options);

   /**
    *  Query whether text does not appear on the page using a regular expression. Returns as soon as the text does not appear, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
    *
    *  @param   pattern    The regular expression expected not to match
    *  @return                    Whether the text does not appear
    */
    boolean hasNoContentMatch(Pattern pattern);
    /**
     *  Query whether text does not appear on the page using a regular expression. Returns as soon as the text does not appear, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
     *
     *  @param   pattern    The regular expression expected not to match
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    Whether the text does not appear
     */
    boolean hasNoContentMatch(Pattern pattern, Options options);

   /**
    *  Query whether an element matching a CSS selector appears on the page within the configured getTimeout
    *
    *  @param   cssSelector    CSS selector
    *  @return                    Whether an element appears
    */
    boolean hasCss(String cssSelector);
    /**
     *  Query whether an element matching a CSS selector appears on the page within the configured getTimeout
     *
     *  @param   cssSelector    CSS selector
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    Whether an element appears
     */
    boolean hasCss(String cssSelector, Options options);

   /**
    *  Query whether an element matching a CSS selector does not appear on the page. Returns as soon as the element does not appear, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
    *
    *  @param   cssSelector    CSS selector
    *  @return                    Whether an element does not appear
    */
    boolean hasNoCss(String cssSelector);
    /**
     *  Query whether an element matching a CSS selector does not appear on the page. Returns as soon as the element does not appear, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
     *
     *  @param   cssSelector    CSS selector
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    Whether an element does not appear
     */
    boolean hasNoCss(String cssSelector, Options options);

   /**
    *  Query whether an element matching an XPath query appears on the page within the configured getTimeout
    *
    *  @param   xpath    XPath query
    *  @return                    Whether an element appears
    */
    boolean hasXPath(String xpath);
    /**
     *  Query whether an element matching an XPath query appears on the page within the configured getTimeout
     *
     *  @param   xpath    XPath query
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    Whether an element appears
     */
    boolean hasXPath(String xpath, Options options);

   /**
    *  Query whether an element matching an XPath query appears on the page. Returns as soon as the element appears, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
    *
    *  @param   xpath    XPath query
    *  @return                    Whether an element appears
    */
    boolean hasNoXPath(String xpath);
    /**
     *  Query whether an element matching an XPath query appears on the page. Returns as soon as the element appears, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
     *
     *  @param   xpath    XPath query
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    Whether an element appears
     */
    boolean hasNoXPath(String xpath, Options options);

   /**
    *  Find an element matching a CSS selector
    *
    *  @param   cssSelector    CSS selector
    *  @return                    The first matching element
    */
    ElementScope findCss(String cssSelector);
    /**
     *  Find an element matching a CSS selector
     *
     *  @param   cssSelector    CSS selector
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    The first matching element
     */
    ElementScope findCss(String cssSelector, Options options);

   /**
    *  Find an element matching an XPath query
    *
    *  @param   xpath    XPath query
    *  @return                    The first matching element
    */

    ElementScope findXPath(String xpath);
    /**
     *  Find an element matching an XPath query
     *
     *  @param   xpath    XPath query
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    The first matching element
     */
    ElementScope findXPath(String xpath, Options options);

   /**
    *  Find all elements matching a CSS selector at the current moment. Does not wait until the <see cref="SessionConfiguration.Timeout"/> but returns as soon as the driver does.
    *
    *  @param   cssSelector    CSS selector
    *  @return                    All matching elements
    */
    List<ElementFound> findAllCss(String cssSelector);
    /**
     *  Find all elements matching a CSS selector at the current moment. Does not wait until the <see cref="SessionConfiguration.Timeout"/> but returns as soon as the driver does.
     *
     *  @param   cssSelector    CSS selector
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    All matching elements
     */
    List<ElementFound> findAllCss(String cssSelector, Options options);

   /**
    *  Find all elements matching an XPath query at the current moment. Does not wait until the <see cref="SessionConfiguration.Timeout"/> but returns as soon as the driver does.
    *
    *  @param   xpath    XPath query
    *  @return                    All matching elements
    */
    List<ElementFound> findAllXPath(String xpath);
    /**
     *  Find all elements matching an XPath query at the current moment. Does not wait until the <see cref="SessionConfiguration.Timeout"/> but returns as soon as the driver does.
     *
     *  @param   xpath    XPath query
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    All matching elements
     */
    List<ElementFound> findAllXPath(String xpath, Options options);

   /**
    *  Find the first matching section to appear within the configured getTimeout.
    *  <p>Sections are identified by the text of their child heading element, or by id.
    *  <p>E.g. to find this:
    *
    *  <code>
    *      &lt;div&gt;
    *          &lt;h2&gt;Search results&lt;/h2&gt;
    *          ...
    *      &lt;/div&gt;</code>
    *
    *  or this:
    *
    *  <code>
    *      &lt;section&gt;
    *          &lt;h3&gt;Search results&lt;/h3&gt;
    *          ...
    *      &lt;/section&gt;</code>
    *
    *  <p>use this:
    *  <p>
    *  <code>    findSection("Search results")</code>
    *
    *
    *  @param   locator    The text of a child heading element or section id
    *  @return                    An element
    */
    ElementScope findSection(String locator);
    /**
     *  Find the first matching section to appear within the configured getTimeout.
     *  <p>Sections are identified by the text of their child heading element, or by id.
     *  <p>E.g. to find this:
     *
     *  <code>
     *      &lt;div&gt;
     *          &lt;h2&gt;Search results&lt;/h2&gt;
     *          ...
     *      &lt;/div&gt;</code>
     *
     *  or this:
     *
     *  <code>
     *      &lt;section&gt;
     *          &lt;h3&gt;Search results&lt;/h3&gt;
     *          ...
     *      &lt;/section&gt;</code>
     *
     *  <p>use this:
     *  <p>
     *  <code>    findSection("Search results")</code>
     *
     *
     *  @param   locator    The text of a child heading element or section id
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    An element
     */
    ElementScope findSection(String locator, Options options);

   /**
    *  Find the first matching fieldset to appear within the configured getTimeout.
    *  <p>Fieldsets are identified by the text of their child legend element, or by id.
    *  <p>E.g. to find this:
    *
    *  <code>
    *      &lt;fieldset&gt;
    *          &lt;legend&gt;Advanced search&lt;/legend&gt;
    *          ...
    *      &lt;/fieldset&gt;</code>
    *
    *  <p>use this:
    *  <p>
    *  <code>    findFieldset("Advanced search")</code>
    *
    *
    *  @param   locator    The text of a child legend element or fieldset id
    *  @return                    An element
    */
    ElementScope findFieldset(String locator);
    /**
     *  Find the first matching fieldset to appear within the configured getTimeout.
     *  <p>Fieldsets are identified by the text of their child legend element, or by id.
     *  <p>E.g. to find this:
     *
     *  <code>
     *      &lt;fieldset&gt;
     *          &lt;legend&gt;Advanced search&lt;/legend&gt;
     *          ...
     *      &lt;/fieldset&gt;</code>
     *
     *  <p>use this:
     *  <p>
     *  <code>    findFieldset("Advanced search")</code>
     *
     *
     *  @param   locator    The text of a child legend element or fieldset id
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    An element
     */
    ElementScope findFieldset(String locator, Options options);

   /**
    *  Find the first matching IFrame to appear within the configured getTimeout.
    *  <p>IFrames are identified by the their id or title attributes, or by the text of the &lt;h1&gt; element in their content.
    *  <p>E.g. to find this:
    *
    *  <code>
    *      &lt;iframe id="myFrame" title="My I Frame" src="..."&gt;
    *          &lt;h1&gt;My Frame Header&lt;/h1&gt;
    *          ...
    *      &lt;/iframe&gt;
    *  </code>
    *
    *  <p>use one of these:
    *
    *  <code>
    *          findIframe("myFrame")
    *          findIframe("My I Frame")
    *          findIframe("My Frame Header")
    *  </code>
    *
    *
    *  @param   locator    The text of a child legend element or fieldset id
    *  @return                    An element
    */
    IFrameElementScope findIFrame(String locator);
    /**
     *  Find the first matching IFrame to appear within the configured getTimeout.
     *  <p>IFrames are identified by the their id or title attributes, or by the text of the &lt;h1&gt; element in their content.
     *  <p>E.g. to find this:
     *
     *  <code>
     *      &lt;iframe id="myFrame" title="My I Frame" src="..."&gt;
     *          &lt;h1&gt;My Frame Header&lt;/h1&gt;
     *          ...
     *      &lt;/iframe&gt;
     *  </code>
     *
     *  <p>use one of these:
     *
     *  <code>
     *          findIframe("myFrame")
     *          findIframe("My I Frame")
     *          findIframe("My Frame Header")
     *  </code>
     *
     *
     *  @param   locator    The text of a child legend element or fieldset id
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    An element
     */
    IFrameElementScope findIFrame(String locator, Options options);

   /**
    *  Find the first matching element with specified id to appear within the configured getTimeout
    *
    *  @param   id    Element id
    *  @return                    An element
    */
    ElementScope findId(String id);
    /**
     *  Find the first matching element with specified id to appear within the configured getTimeout
     *
     *  @param   id    Element id
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    An element
     */
    ElementScope findId(String id, Options options);

   /**
    *  Check the first checkbox to appear within the configured getTimeout matching the text of the associated label element, the id, name or the last part of the id (for asp.net forms testing).
    *
    *  @param   locator    The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)
    */
    void check(String locator);
    /**
     *  Check the first checkbox to appear within the configured getTimeout matching the text of the associated label element, the id, name or the last part of the id (for asp.net forms testing).
     *
     *  @param   locator    The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     */
    void check(String locator, Options options);

   /**
    *  Uncheck the first checkbox to appear within the configured getTimeout matching the text of the associated label element, the id, name or the last part of the id (for asp.net forms testing).
    *
    *  @param   locator    The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)
    */
    void uncheck(String locator);
    /**
     *  Uncheck the first checkbox to appear within the configured getTimeout matching the text of the associated label element, the id, name or the last part of the id (for asp.net forms testing).
     *
     *  @param   locator    The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     */
    void uncheck(String locator, Options options);

   /**
    *  Choose the first radio button to appear within the configured getTimeout matching the text of the associated label element, the id, the name, the value or the last part of the id (for asp.net forms testing).
    *
    *  @param   locator    The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)
    */
    void choose(String locator);
    /**
     *  Choose the first radio button to appear within the configured getTimeout matching the text of the associated label element, the id, the name, the value or the last part of the id (for asp.net forms testing).
     *
     *  @param   locator    The text of the associated label element, the id or name, the last part of the id (for asp.net forms testing)
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     */
    void choose(String locator, Options options);

   /**
    *  Executes custom javascript in the browser
    *
    *  @param   javascript    JavaScript to execute
    *  @return                    Anything returned from the script
    */
    String executeScript(String javascript);

   /**
    *  Query whether an element appears within the configured getTimeout
    *
    *  @param   findElement    A function to find an element
    */
    boolean has(ElementScope findElement);

    /**
     *  Query whether an element appears within the configured getTimeout
     *
     *  @param   findElement    A function to find an element
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     */
    boolean has(ElementScope findElement, Options options);

   /**
    *  Query whether an element does not appear. Returns as soon as the element does not appear or after the <see cref="SessionConfiguration.Timeout"/>
    *
    *  @param   findElement    A function to find an element
    */
    boolean hasNo(ElementScope findElement);
    /**
     *  Query whether an element does not appear. Returns as soon as the element does not appear or after the <see cref="SessionConfiguration.Timeout"/>
     *
     *  @param   findElement    A function to find an element
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     */
    boolean hasNo(ElementScope findElement, Options options);

   /**
    *  Retry an action on any exception until it succeeds. Once the <see cref="SessionConfiguration.Timeout"/> is passed any exception will be rethrown.
    *  <p>Waits for the <see cref="SessionConfiguration.RetryInterval"/> between retries
    *
    *  @param   action    An action
    */
    void retryUntilTimeout(BrowserAction action);

   /**
    *  Execute a query repeatedly until either the expected result is returned or the <see cref="SessionConfiguration.Timeout"/> is passed.
    *  <p>Once the <see cref="SessionConfiguration.Timeout"/> is passed any result will be returned or any exception will be rethrown.
    *  <p>Waits for the <see cref="SessionConfiguration.RetryInterval"/> between retries.
    *
    *  @param   query    A query
    */
    <T> T query(Query<T> query);

   /**
    *  Execute an action repeatedly until a condition is met.
    *  <p>Allows the time specified in <paramref name="waitBeforeRetry"/> for the <paramref name="until"/> query to return the expected value before each retry.
    *  <p>Once the <see cref="SessionConfiguration.Timeout"/> is passed a coypu.MissingHtmlException will be thrown.
    *
    *  @param   tryThis    The action to try
    *  @param   until    The condition to be met
    *  @param   waitBeforeRetry    How long to wait for the condition to be met before retrying
    */
    void tryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan waitBeforeRetry);
    /**
     *  Execute an action repeatedly until a condition is met.
     *  <p>Allows the time specified in <paramref name="waitBeforeRetry"/> for the <paramref name="until"/> query to return the expected value before each retry.
     *  <p>Once the <see cref="SessionConfiguration.Timeout"/> is passed a coypu.MissingHtmlException will be thrown.
     *
     *  @param   tryThis    The action to try
     *  @param   until    The condition to be met
     *  @param   waitBeforeRetry    How long to wait for the condition to be met before retrying
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     */
    void tryUntil(BrowserAction tryThis, PredicateQuery until, TimeSpan waitBeforeRetry, Options options);

   /**
    *  Find the first from a list of possible states that your page may arrive at.
    *  <p>Returns as soon as any of the possible states is found.
    *  <p>E.g.:
    *
    *  <code>
    *   var signedIn = new State(browser.hasContent("Signed in as:"));
    *   var signedOut = new State(browser.hasContent("Please sign in"));
    *
    *   if (browser.findState(signedIn,signedOut) == signedIn)
    *   {
    *     browser.clickLink("Sign out");
    *   }
    *   </code>
    *
    *  @param   states    The possible states you are expecting
    *  @return            The first matching state found
    */
    State findState(State... states);
    /**
     *  Find the first from a list of possible states that your page may arrive at.
     *  <p>Returns as soon as any of the possible states is found.
     *  <p>E.g.:
     *
     *  <code>
     *   var signedIn = new State(browser.hasContent("Signed in as:"));
     *   var signedOut = new State(browser.hasContent("Please sign in"));
     *
     *   if (browser.findState(signedIn,signedOut) == signedIn)
     *   {
     *     browser.clickLink("Sign out");
     *   }
     *   </code>
     *
     *  @param   states    The possible states you are expecting
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return            The first matching state found
     */
    State findState(State[] states, Options options);

   /**
    *  Click a button, input of type button|submit|image or div with the css class "button".
    *  <p>Wait for a condition to be satisfied for a specified time otherwise click and wait again.
    *  <p>Continues until the expected condition is satisfied or the <see cref="SessionConfiguration.Timeout"/> is reached.
    *
    *  @param   locator    The text/value, name or id of the button
    *  @param   until    The condition to be satisfied
    *  @param   waitBeforeRetry    How long to wait for the condition to be met before retrying
    *  @return                    The first matching button
    */
    DriverScope clickButton(String locator, PredicateQuery until, TimeSpan waitBeforeRetry);
    /**
     *  Click a button, input of type button|submit|image or div with the css class "button".
     *  <p>Wait for a condition to be satisfied for a specified time otherwise click and wait again.
     *  <p>Continues until the expected condition is satisfied or the <see cref="SessionConfiguration.Timeout"/> is reached.
     *
     *  @param   locator    The text/value, name or id of the button
     *  @param   until    The condition to be satisfied
     *  @param   waitBeforeRetry    How long to wait for the condition to be met before retrying
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    The first matching button
     */
    DriverScope clickButton(String locator, PredicateQuery until, TimeSpan waitBeforeRetry, Options options);

   /**
    *  Click a link and wait for a condition to be satisfied for a specified time otherwise click and wait again.
    *  <p>Continues until the expected condition is satisfied or the <see cref="SessionConfiguration.Timeout"/> is reached.
    *
    *  @param   locator    The text of the link
    *  @param   until    The condition to be satisfied
    *  @param   waitBeforeRetry    How long to wait for the condition to be met before retrying
    *  @return                    The first matching button
    */
    DriverScope clickLink(String locator, PredicateQuery until, TimeSpan waitBeforeRetry);
    /**
     *  Click a link and wait for a condition to be satisfied for a specified time otherwise click and wait again.
     *  <p>Continues until the expected condition is satisfied or the <see cref="SessionConfiguration.Timeout"/> is reached.
     *
     *  @param   locator    The text of the link
     *  @param   until    The condition to be satisfied
     *  @param   waitBeforeRetry    How long to wait for the condition to be met before retrying
     *  @param   options
     *  <p>Override the way Coypu is configured to find elements for this call only.
     *  <p>E.g. A longer wait:
     *
     *  <code>new Options{Timeout = TimeSpan .fromSeconds(60)}</code>
     *  @return                    The first matching button
     */
    DriverScope clickLink(String locator, PredicateQuery until, TimeSpan waitBeforeRetry, Options options);

}
