//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu;

import coypu.ActivatorDriverFactory;
import coypu.BrowserWindow;
import coypu.Driver;
import coypu.DriverFactory;
import coypu.Finders.DisambiguationStrategy;
import coypu.Finders.FinderOptionsDisambiguationStrategy;
import coypu.Finders.WindowFinder;
import coypu.FullyQualifiedUrlBuilder;
import coypu.Options;
import coypu.RobustWindowScope;
import coypu.SessionConfiguration;
import coypu.Timing.RetryUntilTimeoutTimingStrategy;
import coypu.Timing.StopwatchWaiter;
import coypu.Timing.TimingStrategy;
import coypu.Timing.Waiter;
import coypu.UrlBuilder;
import coypu.WebRequests.RestrictedResourceDownloader;
import coypu.WebRequests.WebClientWithCookies;
import CS2JNet.System.LCC.IDisposable;

/**
* A browser session
*/
public class BrowserSession  extends BrowserWindow implements IDisposable
{
    private final RestrictedResourceDownloader restrictedResourceDownloader;
    private boolean __WasDisposed;
    public boolean getWasDisposed() {
        return __WasDisposed;
    }

    public void setWasDisposed(boolean value) {
        __WasDisposed = value;
    }

    /**
    * A new browser session. Control the lifecycle of this session with using{} / session.Dispose()
    * 
    *  @return The new session with default configuration
    */
    public BrowserSession() throws Exception {
        this(new SessionConfiguration());
    }

    /// <summary>
    /// A new browser session. Control the lifecycle of this session with using{} / session.Dispose()
    /// </summary>
    /// <param name="SessionConfigurationconfiguration for this session</param>
    /// <returns>The new session</returns>
    public BrowserSession(SessionConfiguration SessionConfiguration) throws Exception {
        this(new ActivatorDriverFactory(), SessionConfiguration, new RetryUntilTimeoutTimingStrategy(), new StopwatchWaiter(), new WebClientWithCookies(), new FullyQualifiedUrlBuilder(), new FinderOptionsDisambiguationStrategy());
    }

    public BrowserSession(DriverFactory driver, SessionConfiguration SessionConfiguration, TimingStrategy timingStrategy, Waiter waiter, RestrictedResourceDownloader restrictedResourceDownloader, UrlBuilder urlBuilder, DisambiguationStrategy disambiguationStrategy) throws Exception {
        super(SessionConfiguration, null, driver.newWebDriver(SessionConfiguration.getDriver(),SessionConfiguration.getBrowser()), timingStrategy, waiter, urlBuilder, disambiguationStrategy);
        this.restrictedResourceDownloader = restrictedResourceDownloader;
    }

    public Driver getDriver() throws Exception {
        return driver;
    }

    /**
    * The native driver for the Coypu.Driver / browser combination you supplied. E.g. for SeleniumWebDriver + Firefox it will currently be a OpenQA.Selenium.Firefox.FirefoxDriver.
    */
    public Object getNative() throws Exception {
        return driver.getNative();
    }

    /**
    * Saves a resource from the web to a local file using the cookies from the current browser session.
    * Allows you to sign in through the browser and then directly download a resource restricted to signed-in users.
    * 
    *  @param resource  The location of the resource to download
    *  @param saveAs Path to save the file to
    */
    public void saveWebResource(String resource, String saveAs) throws Exception {
        restrictedResourceDownloader.setCookies(driver.getBrowserCookies());
        restrictedResourceDownloader.downloadFile(urlBuilder.getFullyQualifiedUrl(resource,SessionConfiguration),saveAs);
    }

    /**
    * Find an open browser window or tab by its title or name. If no exact match is found a partial match on title will be considered.
    * 
    *  @param locator Window title or name
    *  @param options Override the way Coypu is configured to find elements for this call only.E.g. A longer wait:
    *  {@code new Options{Timeout = TimeSpan.FromSeconds(60)}}
    * 
    *  @return The matching BrowserWindow scope
    */
    public BrowserWindow findWindow(String locator, Options options) throws Exception {
        return new RobustWindowScope(new WindowFinder(driver, locator, this, merge(options)),this,merge(options));
    }

    /**
    * Disposes the current session, closing any open browser.
    */
    public void dispose() throws Exception {
        if (getWasDisposed())
            return ;
         
        driver.close();
        ActivatorDriverFactory.setOpenDrivers(ActivatorDriverFactory.getOpenDrivers() - 1);
        setWasDisposed(true);
    }

}


