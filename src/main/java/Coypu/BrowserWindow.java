package Coypu;

import Coypu.Actions.AcceptModalDialog;
import Coypu.Actions.CancelModalDialog;
import Coypu.Finders.ElementFinder;
import Coypu.Queries.HasDialogQuery;
import Coypu.Queries.HasNoDialogQuery;
import Coypu.Robustness.RobustWrapper;
import Coypu.Robustness.Waiter;

/// <summary>
/// A browser window belonging to a particular browser session
/// </summary>
public class BrowserWindow extends DriverScope {

    public BrowserWindow(Configuration configuration, ElementFinder elementFinder, Driver driver, RobustWrapper robustWrapper, Waiter waiter, UrlBuilder urlBuilder) {
        super(configuration, elementFinder, driver, robustWrapper, waiter, urlBuilder);
    }

    /// <summary>
    /// Check that a dialog with the specified text appears within the <see cref="Configuration.Timeout"/>
    /// </summary>
    /// <param name="withText">Dialog text</param>
    /// <returns>Whether an element appears</returns>
    public boolean hasDialog(String withText) {
        return hasDialog(withText, configuration);
    }

    public boolean hasDialog(String withText, Options options) {
        return query(new HasDialogQuery(driver, withText, this, setOptions(options)));
    }

    /// <summary>
    /// Check that a dialog with the specified is not present. Returns as soon as the dialog is not present, or when the <see cref="Configuration.Timeout"/> is reached.
    /// </summary>
    /// <param name="withText">Dialog text</param>
    /// <returns>Whether an element does not appears</returns>
    public boolean hasNoDialog(String withText) {
        return hasNoDialog(withText,configuration);
    }

    public boolean hasNoDialog(String withText, Options options) {
        return query(new HasNoDialogQuery(driver, withText, this, setOptions(options)));
    }

    /// <summary>
    /// Accept the first modal dialog to appear within the <see cref="Configuration.Timeout"/>
    /// </summary>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the dialog cannot be found</exception>
    public void acceptModalDialog() {
        acceptModalDialog(configuration);
    }

    public void acceptModalDialog(Options options) {
        retryUntilTimeout(new AcceptModalDialog(this, driver, setOptions(options)));
    }

    /// <summary>
    /// Cancel the first modal dialog to appear within the <see cref="Configuration.Timeout"/>
    /// </summary>
    /// <exception cref="T:Coypu.MissingHtmlException">Thrown if the dialog cannot be found</exception>
    public void cancelModalDialog() {
        cancelModalDialog(configuration);
    }
    public void cancelModalDialog(Options options) {
        retryUntilTimeout(new CancelModalDialog(this, driver, setOptions(options)));
    }

    /// <summary>
    /// Visit a url in the browser
    /// </summary>
    /// <param name="virtualPath">Virtual paths will use the Configuration.AppHost,Port,SSL settings. Otherwise supply a fully qualified URL.</param>
    public void visit(String virtualPath) {
        driver.visit(urlBuilder.getFullyQualifiedUrl(virtualPath, configuration));
    }

    /// <summary>
    /// Fill in a previously found text field
    /// </summary>
    /// <param name="element">The text field</param>
    /// <returns>With</returns>
    public FillInWith fillIn(Element element) {
        return fillIn(element,configuration);
    }

    public FillInWith fillIn(Element element, Options options) {
        return new FillInWith(element, driver, robustWrapper, this, setOptions(options));
    }
}
