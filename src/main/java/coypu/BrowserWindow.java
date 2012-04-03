package coypu;

import coypu.Actions.AcceptModalDialog;
import coypu.Actions.CancelModalDialog;
import coypu.Finders.ElementFinder;
import coypu.Queries.HasDialogQuery;
import coypu.Queries.HasNoDialogQuery;
import coypu.Robustness.RobustWrapper;
import coypu.Robustness.Waiter;

/**
* A browser window belonging to a particular browser session
*/
public class BrowserWindow extends DriverScope {

    public BrowserWindow(SessionConfiguration sessionConfiguration, ElementFinder elementFinder, Driver driver, RobustWrapper robustWrapper, Waiter waiter, UrlBuilder urlBuilder) {
        super(sessionConfiguration, elementFinder, driver, robustWrapper, waiter, urlBuilder);
    }

   /**
    *  Check that a dialog with the specified text appears within the <see cref="SessionConfiguration.Timeout"/>
    *
    *  @param   withText    Dialog text
    *  @return                    Whether an element appears
    */
    public boolean hasDialog(String withText) {
        return hasDialog(withText, sessionConfiguration);
    }

    public boolean hasDialog(String withText, Options options) {
        return query(new HasDialogQuery(driver, withText, this, setOptions(options)));
    }

   /**
    *  Check that a dialog with the specified is not present. Returns as soon as the dialog is not present, or when the <see cref="SessionConfiguration.Timeout"/> is reached.
    *
    *  @param   withText    Dialog text
    *  @return                    Whether an element does not appears
    */
    public boolean hasNoDialog(String withText) {
        return hasNoDialog(withText, sessionConfiguration);
    }

    public boolean hasNoDialog(String withText, Options options) {
        return query(new HasNoDialogQuery(driver, withText, this, setOptions(options)));
    }

   /**
    *  Accept the first modal dialog to appear within the <see cref="SessionConfiguration.Timeout"/>
    */
    public void acceptModalDialog() {
        acceptModalDialog(sessionConfiguration);
    }

    public void acceptModalDialog(Options options) {
        retryUntilTimeout(new AcceptModalDialog(this, driver, setOptions(options)));
    }

   /**
    *  Cancel the first modal dialog to appear within the <see cref="SessionConfiguration.Timeout"/>
    */
    public void cancelModalDialog() {
        cancelModalDialog(sessionConfiguration);
    }
    public void cancelModalDialog(Options options) {
        retryUntilTimeout(new CancelModalDialog(this, driver, setOptions(options)));
    }

   /**
    *  Visit a url in the browser
    */
    public void visit(String virtualPath) {
        driver.visit(urlBuilder.getFullyQualifiedUrl(virtualPath, sessionConfiguration));
    }

   /**
    *  Fill in a previously found text field
    *
    *  @param   element    The text field
    *  @return                    With
    */
    public FillInWith fillIn(Element element) {
        return fillIn(element, sessionConfiguration);
    }

    public FillInWith fillIn(Element element, Options options) {
        return new FillInWith(element, driver, robustWrapper, this, setOptions(options));
    }
}
