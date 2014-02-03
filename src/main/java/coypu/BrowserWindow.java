//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu;

import coypu.Actions.AcceptModalDialog;
import coypu.Actions.CancelModalDialog;
import coypu.Driver;
import coypu.DriverScope;
import coypu.Finders.DisambiguationStrategy;
import coypu.Finders.ElementFinder;
import coypu.Options;
import coypu.Queries.HasDialogQuery;
import coypu.Queries.HasNoDialogQuery;
import coypu.SessionConfiguration;
import coypu.Timing.TimingStrategy;
import coypu.Timing.Waiter;
import coypu.UrlBuilder;

/**
* A browser window belonging to a particular browser session
*/
public class BrowserWindow  extends DriverScope 
{
    public BrowserWindow(SessionConfiguration SessionConfiguration, ElementFinder elementFinder, Driver driver, TimingStrategy timingStrategy, Waiter waiter, UrlBuilder urlBuilder, DisambiguationStrategy disambiguationStrategy) throws Exception {
        super(SessionConfiguration, elementFinder, driver, timingStrategy, waiter, urlBuilder, disambiguationStrategy);
    }

    public BrowserWindow(ElementFinder elementFinder, DriverScope outerScope) throws Exception {
        super(elementFinder, outerScope);
    }

    /**
    * Check that a dialog with the specified text appears within the 
    *  {@link #SessionConfiguration.Timeout}
    * 
    *  @param withText Dialog text
    *  @return Whether an element appears
    */
    public boolean hasDialog(String withText, Options options) throws Exception {
        return Query(new HasDialogQuery(driver, withText, this, merge(options)));
    }

    /**
    * Check that a dialog with the specified is not present. Returns as soon as the dialog is not present, or when the 
    *  {@link #SessionConfiguration.Timeout}
    *  is reached.
    * 
    *  @param withText Dialog text
    *  @return Whether an element does not appears
    */
    public boolean hasNoDialog(String withText, Options options) throws Exception {
        return Query(new HasNoDialogQuery(driver, withText, this, merge(options)));
    }

    /**
    * Accept the first modal dialog to appear within the 
    *  {@link #SessionConfiguration.Timeout}
    * 
    *  @throws T:Coypu.MissingHtmlException Thrown if the dialog cannot be found
    */
    public void acceptModalDialog(Options options) throws Exception {
        retryUntilTimeout(new AcceptModalDialog(this, driver, merge(options)));
    }

    /**
    * Cancel the first modal dialog to appear within the 
    *  {@link #SessionConfiguration.Timeout}
    * 
    *  @throws T:Coypu.MissingHtmlException Thrown if the dialog cannot be found
    */
    public void cancelModalDialog(Options options) throws Exception {
        retryUntilTimeout(new CancelModalDialog(this, driver, merge(options)));
    }

    /**
    * Visit a url in the browser
    * 
    *  @param virtualPath Virtual paths will use the SessionConfiguration.AppHost,Port,SSL settings. Otherwise supply a fully qualified URL.
    */
    public void visit(String virtualPath) throws Exception {
        driver.visit(urlBuilder.getFullyQualifiedUrl(virtualPath,SessionConfiguration),this);
    }

    /**
    * Navigate back to the previous location in the browser's history
    */
    public void goBack() throws Exception {
        driver.goBack(this);
    }

    /**
    * Navigate forward to the next location in the browser's history
    */
    public void goForward() throws Exception {
        driver.goForward(this);
    }

    /**
    * Returns the page's title displayed in the browser
    */
    public String getTitle() throws Exception {
        return driver.title(this);
    }

    /**
    * Executes custom javascript in the browser
    * 
    *  @param javascript JavaScript to execute
    *  @return Anything returned from the script
    */
    public String executeScript(String javascript) throws Exception {
        return driver.executeScript(javascript,this);
    }

    /**
    * Maximises this browser window
    */
    public void maximiseWindow() throws Exception {
        driver.maximiseWindow(this);
    }

    /**
    * Resizes this browser window to the supplied dimensions
    * 
    *  @param width The required width
    *  @param height The required height
    */
    public void resizeTo(int width, int height) throws Exception {
        driver.resizeTo(new Size(),this);
    }

    /**
    * Refreshes the current browser window page
    */
    public void refresh() throws Exception {
        driver.refresh(this);
    }

    public void saveScreenshot(String saveAs, ImageFormat imageFormat) throws Exception {
        driver.saveScreenshot(saveAs,this);
    }

}


