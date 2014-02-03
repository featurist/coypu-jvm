//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu;

import coypu.Actions.BrowserAction;
import coypu.ElementFound;
import coypu.ElementScope;
import coypu.FillInWith;
import coypu.Options;
import coypu.Queries.PredicateQuery;
import coypu.Queries.Query;
import coypu.Scope;
import coypu.SelectFrom;
import coypu.SnapshotElementScope;
import coypu.State;
import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.TimeSpan;
import java.net.URI;
import java.util.regex.Pattern;

/**
* The scope for any browser interaction: a browser window, frame or element.
*/
public interface Scope   
{
    /**
    * Click a button, input of type button|submit|image or div with the css class "button"
    * 
    *  @param locator The text/value, name or id of the button
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    */
    void clickButton(String locator, Options options) throws Exception ;

    /**
    * Click the first matching link
    * 
    *  @param locator The text of the link
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    */
    void clickLink(String locator, Options options) throws Exception ;

    /**
    * Find the first input of type button|submit|image or div with the css class "button" to appear within the configured timeout .
    * 
    *  @param locator The text/value, name or id of the button
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return A button
    */
    ElementScope findButton(String locator, Options options) throws Exception ;

    /**
    * Find the first matching link to appear within the configured timeout
    * 
    *  @param locator The text of the link
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return A link
    */
    ElementScope findLink(String locator, Options options) throws Exception ;

    /**
    * Find the first form field of any type to appear within the configured timeout
    * 
    *  @param locator The text of the associated label element, the id or name (except radio buttons), the placeholder text, the value of a radio button or checkbox
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return A form field
    */
    ElementScope findField(String locator, Options options) throws Exception ;

    /**
    * Find the first matching text field to appear within the configured timeout to fill in.
    * 
    *  @param locator The text of the associated label element, the id or name, the placeholder text
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return With
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    */
    FillInWith fillIn(String locator, Options options) throws Exception ;

    /**
    * Select an option from a select element
    * 
    *  @param option The text or value of the option to select
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return From
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    */
    SelectFrom select(String option, Options options) throws Exception ;

    /**
    * Query whether text appears on the page within the configured timeout
    * 
    *  @param text The exact text to find
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return Whether the text appears
    */
    boolean hasContent(String text, Options options) throws Exception ;

    /**
    * Query whether text appears on the page using a regular expression within the configured timeout
    * 
    *  @param pattern The regular expression to match
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return Whether the page text matches
    */
    boolean hasContentMatch(Pattern pattern, Options options) throws Exception ;

    /**
    * Query whether text does not appear on the page. Returns as soon as the text does not appear, or when the 
    *  {@link #SessionConfiguration.Timeout}
    *  is reached.
    * 
    *  @param text The exact text expected not to be found
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return Whether the text does not appear
    */
    boolean hasNoContent(String text, Options options) throws Exception ;

    /**
    * Query whether text does not appear on the page using a regular expression. Returns as soon as the text does not appear, or when the 
    *  {@link #SessionConfiguration.Timeout}
    *  is reached.
    * 
    *  @param pattern The regular expression expected not to match
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return Whether the text does not appear
    */
    boolean hasNoContentMatch(Pattern pattern, Options options) throws Exception ;

    /**
    * Find an element matching a CSS selector
    * 
    *  @param cssSelector CSS selector
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return The first matching element
    */
    ElementScope findCss(String cssSelector, Options options) throws Exception ;

    /**
    * Find an element matching a CSS selector
    * 
    *  @param cssSelector CSS selector
    *  @param text The text of the element must exactly match this text
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return The first matching element
    */
    ElementScope findCss(String cssSelector, String text, Options options) throws Exception ;

    /**
    * Find an element matching a CSS selector
    * 
    *  @param cssSelector CSS selector
    *  @param text The text of the element must match this pattern
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return The first matching element
    */
    ElementScope findCss(String cssSelector, Pattern text, Options options) throws Exception ;

    /**
    * Find an element matching an XPath query
    * 
    *  @param xpath XPath query
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return The first matching element
    */
    ElementScope findXPath(String xpath, Options options) throws Exception ;

    /**
    * Find all elements matching a CSS selector. If a predicate is supplied this will wait until the predicate matches, otherwise this will return immediately.
    * 
    *  @param cssSelector CSS selector
    *  @param predicate A predicate to test the entire collection against. It will wait for this predicate before returning a list of matching elements.
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return All matching elements as snapshot scopes which will not respect future changes in the document
    */
    IEnumerable<SnapshotElementScope> findAllCss(String cssSelector, Func<IEnumerable<SnapshotElementScope>, Boolean> predicate, Options options) throws Exception ;

    /**
    * Find all elements matching an XPath query. If a predicate is supplied this will wait until the predicate matches, otherwise this will return immediately.
    * 
    *  @param xpath XPath query
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return All matching elements
    */
    IEnumerable<SnapshotElementScope> findAllXPath(String xpath, Func<IEnumerable<SnapshotElementScope>, Boolean> predicate, Options options) throws Exception ;

    /**
    * Find the first matching section to appear within the configured timeout.Sections are identified by the text of their child heading element, or by id.E.g. to find this:
    * 
    * 
    *  {@code 
    * <div>
    * <h2>Search results</h2>
    * ...
    * </div>}
    * 
    * 
    * or this:
    * 
    * 
    *  {@code 
    * <section>
    * <h3>Search results</h3>
    * ...
    * </section>}
    * use this:
    *  {@code     FindSection("Search results")}
    * 
    *  @param locator The text of a child heading element or section id
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return An element
    */
    ElementScope findSection(String locator, Options options) throws Exception ;

    /**
    * Find the first matching fieldset to appear within the configured timeout.Fieldsets are identified by the text of their child legend element, or by id.E.g. to find this:
    * 
    * 
    *  {@code 
    * <fieldset>
    * <legend>Advanced search</legend>
    * ...
    * </fieldset>}
    * use this:
    *  {@code     FindFieldset("Advanced search")}
    * 
    *  @param locator The text of a child legend element or fieldset id
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return An element
    */
    ElementScope findFieldset(String locator, Options options) throws Exception ;

    /**
    * Find the first matching frame or iframe to appear within the configured timeout.Frames are identified by the their id, name or title attributes, by their loaded page title, or by the text of the <h1> element in their content.E.g. to find this:
    * 
    * 
    *  {@code 
    * <iframe id="myFrame" title="My I Frame" src="...">
    * <h1>My Frame Header</h1>
    * ...
    * </iframe>
    * }
    * use one of these:
    * 
    * 
    *  {@code 
    * Findframe("myFrame")
    * Findframe("My I Frame")
    * Findframe("My Frame Header")
    * }
    * 
    *  @param locator The text of a child legend element or fieldset id
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return An element
    */
    ElementScope findFrame(String locator, Options options) throws Exception ;

    /**
    * Find the first matching element with specified id to appear within the configured timeout
    * 
    *  @param id Element id
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return An element
    */
    ElementScope findId(String id, Options options) throws Exception ;

    /**
    * Check the first checkbox to appear within the configured timeout matching the text of the associated label element, the id, name or value.
    * 
    *  @param locator The text of the associated label element, the id or name
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    */
    void check(String locator, Options options) throws Exception ;

    /**
    * Uncheck the first checkbox to appear within the configured timeout matching the text of the associated label element, the id, name.
    * 
    *  @param locator The text of the associated label element, the id or name
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    */
    void uncheck(String locator, Options options) throws Exception ;

    /**
    * Choose the first radio button to appear within the configured timeout matching the text of the associated label element, the id or the value.
    * 
    *  @param locator The text of the associated label element, the id or name
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    */
    void choose(String locator, Options options) throws Exception ;

    /**
    * Retry an action on any exception until it succeeds. Once the 
    *  {@link #SessionConfiguration.Timeout}
    *  is passed any exception will be rethrown.Waits for the 
    *  {@link #SessionConfiguration.RetryInterval}
    *  between retries
    *  @param action An action
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    */
    void retryUntilTimeout(Action action, Options options) throws Exception ;

    /**
    * Retry an action on any exception until it succeeds. Once the 
    *  {@link #SessionConfiguration.Timeout}
    *  is passed any exception will be rethrown.Waits for the 
    *  {@link #SessionConfiguration.RetryInterval}
    *  between retries
    *  @param action An action
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    */
    void retryUntilTimeout(BrowserAction action) throws Exception ;

    /**
    * Retry a function on any exception until it succeeds. Once the 
    *  {@link #SessionConfiguration.Timeout}
    *  is passed any exception will be rethrown.Waits for the 
    *  {@link #SessionConfiguration.RetryInterval}
    *  between retries
    *  @param function A function
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    */
    <TResult>TResult retryUntilTimeout(Func<TResult> function, Options options) throws Exception ;

    /**
    * Execute a query repeatedly until either the expected result is returned or the 
    *  {@link #SessionConfiguration.Timeout}
    *  is passed.Once the 
    *  {@link #SessionConfiguration.Timeout}
    *  is passed any result will be returned or any exception will be rethrown.Waits for the 
    *  {@link #SessionConfiguration.RetryInterval}
    *  between retries.
    *  @param query A query
    */
    <T>T query(Query<T> query) throws Exception ;

    /**
    * Execute a query repeatedly until either the expected result is returned or the 
    *  {@link #SessionConfiguration.Timeout}
    *  is passed.Once the 
    *  {@link #SessionConfiguration.Timeout}
    *  is passed any result will be returned or any exception will be rethrown.Waits for the 
    *  {@link #SessionConfiguration.RetryInterval}
    *  between retries.
    *  @param query A query
    *  @param expecting Expected result
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    */
    <T>T query(Func<T> query, T expecting, Options options) throws Exception ;

    /**
    * Execute an action repeatedly until a condition is met.Allows the time specified in 
    *  {@code waitBeforeRetry}
    *  for the 
    *  {@code until}
    *  condition to be met before each retry.Once the 
    *  {@link #SessionConfiguration.Timeout}
    *  is passed a Coypu.MissingHtmlException will be thrown.
    *  @param tryThis The action to try
    *  @param until The condition to be met
    *  @param waitBeforeRetry How long to wait for the condition to be met before retrying
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @throws T:Coypu.MissingHtmlException Thrown if the until condition is never met
    */
    void tryUntil(Action tryThis, Func<Boolean> until, TimeSpan waitBeforeRetry, Options options) throws Exception ;

    /**
    * Execute an action repeatedly until a condition is met.Allows the time specified in 
    *  {@code waitBeforeRetry}
    *  for the 
    *  {@code until}
    *  query to return the expected value before each retry.Once the 
    *  {@link #SessionConfiguration.Timeout}
    *  is passed a Coypu.MissingHtmlException will be thrown.
    *  @param tryThis The action to try
    *  @param until The condition to be met
    *  @param waitBeforeRetry How long to wait for the condition to be met before retrying
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @throws T:Coypu.MissingHtmlException Thrown if the until condition is never met
    */
    void tryUntil(BrowserAction tryThis, PredicateQuery until, Options options) throws Exception ;

    /**
    * Find the first from a list of possible states that your page may arrive at.Returns as soon as any of the possible states is found.E.g.:
    *  {@code 
    * var signedIn = new State(() => browser.HasContent("Signed in as:"));
    * var signedOut = new State(() => browser.HasContent("Please sign in"));
    * 
    * if (browser.FindState(signedIn,signedOut) == signedIn)
    * {
    * browser.ClickLink("Sign out");
    * }
    * }
    * 
    *  @param states The possible states you are expecting
    *  @return
    */
    State findState(State... states) throws Exception ;

    /**
    * Find the first from a list of possible states that your page may arrive at.Returns as soon as any of the possible states is found.E.g.:
    *  {@code 
    * var signedIn = new State(browser.HasContent("Signed in as:"));
    * var signedOut = new State(browser.HasContent("Please sign in"));
    * 
    * if (browser.FindState(signedIn,signedOut) == signedIn)
    * {
    * browser.ClickLink("Sign out");
    * }
    * }
    * 
    *  @param states The possible states you are expecting
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return 
    *  @throws T:Coypu.MissingHtmlException Thrown if the none of the states are reached within the timeout
    */
    State findState(State[] states, Options options) throws Exception ;

    /**
    * Click a button, input of type button|submit|image or div with the css class "button".Wait for a condition to be satisfied for a specified time otherwise click and wait again.Continues until the expected condition is satisfied or the 
    *  {@link #SessionConfiguration.Timeout}
    *  is reached.
    *  @param locator The text/value, name or id of the button
    *  @param until The condition to be satisfied
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return The first matching button
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    */
    Scope clickButton(String locator, PredicateQuery until, Options options) throws Exception ;

    /**
    * Click a link and wait for a condition to be satisfied for a specified time otherwise click and wait again.Continues until the expected condition is satisfied or the 
    *  {@link #SessionConfiguration.Timeout}
    *  is reached.
    *  @param locator The text of the link
    *  @param until The condition to be satisfied
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return The first matching button
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    */
    Scope clickLink(String locator, PredicateQuery until, Options options) throws Exception ;

    /**
    * Try and find this scope now
    * 
    *  @return The element found
    *  @throws T:Coypu.MissingHtmlException Thrown if the element cannot be found
    */
    ElementFound now() throws Exception ;

    /**
    * The location of the current browser window
    */
    URI getLocation() throws Exception ;

    boolean hasCss(String cssSelector, String text, Options options) throws Exception ;

    boolean hasCss(String cssSelector, Pattern text, Options options) throws Exception ;

    boolean hasXPath(String xpath, Options options) throws Exception ;

    boolean hasNoCss(String cssSelector, String text, Options options) throws Exception ;

    boolean hasNoCss(String cssSelector, Pattern text, Options options) throws Exception ;

    boolean hasNoXPath(String xpath, Options options) throws Exception ;

}


