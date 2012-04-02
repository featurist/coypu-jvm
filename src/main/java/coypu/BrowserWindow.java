package coypu;

import coypu.Actions.AcceptModalDialog;
import coypu.Actions.CancelModalDialog;
import coypu.Finders.ElementFinder;
import coypu.Queries.HasDialogQuery;
import coypu.Queries.HasNoDialogQuery;
import coypu.Robustness.RobustWrapper;
import coypu.Robustness.Waiter;

/// <summary>
/// A browser window belonging to a particular browser session
/// </summary>
public class BrowserWindow extends DriverScope {

    public BrowserWindow(SessionConfiguration sessionConfiguration, ElementFinder elementFinder, Driver driver, RobustWrapper robustWrapper, Waiter waiter, UrlBuilder urlBuilder) {
        super(sessionConfiguration, elementFinder, driver, robustWrapper, waiter, urlBuilder);
    }

    /// <summary>
    /// Check that a dialog with the specified text appears within the <see cref="SessionConfiguration.Timeout"/>
    /// </summary>
    /// <param name="withText">Dialog text</param>
    /// <returns>Whether an element appears</returns>
    public boolean hasDialog(String withText) {
        return hasDialog(withText, sessionConfiguration);
    }

    public boolean hasDialog(String withText, Options options) {
        return query(new HasDialogQuery(driver, withText, this, setOptions(options)));
    }

    /// <summary>
    /// Check that a dialog with the specified is not present. Returns as soon as the dialog is not present, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
    /// </summary>
    /// <param name="withText">Dialog text</param>
    /// <returns>Whether an element does not appears</returns>
    public boolean hasNoDialog(String withText) {
        return hasNoDialog(withText, sessionConfiguration);
    }

    public boolean hasNoDialog(String withText, Options options) {
        return query(new HasNoDialogQuery(driver, withText, this, setOptions(options)));
    }

    /// <summary>
    /// Accept the first modal dialog to appear within the <see cref="SessionConfiguration.Timeout"/>
    /// </summary>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the dialog cannot be found</exception>
    public void acceptModalDialog() {
        acceptModalDialog(sessionConfiguration);
    }

    public void acceptModalDialog(Options options) {
        retryUntilTimeout(new AcceptModalDialog(this, driver, setOptions(options)));
    }

    /// <summary>
    /// Cancel the first modal dialog to appear within the <see cref="SessionConfiguration.Timeout"/>
    /// </summary>
    /// <exception cref="T:coypu.MissingHtmlException">Thrown if the dialog cannot be found</exception>
    public void cancelModalDialog() {
        cancelModalDialog(sessionConfiguration);
    }
    public void cancelModalDialog(Options options) {
        retryUntilTimeout(new CancelModalDialog(this, driver, setOptions(options)));
    }

    /// <summary>
    /// Visit a url in the browser
    /// </summary>
    /// <param name="virtualPath">Virtual paths will use the SessionConfiguration.AppHost,Port,SSL settings. Otherwise supply a fully qualified URL.</param>
    public void visit(String virtualPath) {
        driver.visit(urlBuilder.getFullyQualifiedUrl(virtualPath, sessionConfiguration));
    }

    /// <summary>
    /// Fill in a previously found text field
    /// </summary>
    /// <param name="element">The text field</param>
    /// <returns>With</returns>
    public FillInWith fillIn(Element element) {
        return fillIn(element, sessionConfiguration);
    }

    public FillInWith fillIn(Element element, Options options) {
        return new FillInWith(element, driver, robustWrapper, this, setOptions(options));
    }
}
