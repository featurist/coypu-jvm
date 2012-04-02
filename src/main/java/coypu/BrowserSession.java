package coypu;

import coypu.Finders.WindowFinder;
import coypu.Robustness.RetryUntilTimeoutRobustWrapper;
import coypu.Robustness.RobustWrapper;
import coypu.Robustness.StopwatchWaiter;
import coypu.Robustness.Waiter;
import coypu.WebRequests.RestrictedResourceDownloader;
//import coypu.WebRequests.RestrictedResourceDownloader;
//import coypu.WebRequests.WebClientWithCookies;

/// <summary>
/// A browser session
/// </summary>
public class BrowserSession extends BrowserWindow {

    private boolean wasDisposed;
    private RestrictedResourceDownloader restrictedResourceDownloader;
    //private final RestrictedResourceDownloader restrictedResourceDownloader;

    /// <summary>
    /// A new Browser session. Control the lifecycle of this session with using{} / session.dispose()
    /// </summary>
    /// <returns>The new Session with default configuration </returns>
    public BrowserSession() {
        this(new SessionConfiguration());
    }

    /// <summary>
    /// The native driver for the coypu.Driver / browser combination you supplied. E.g. for SeleniumWebDriver + Firefox it will currently be a OpenQA.Selenium.Firefox.FirefoxDriver.
    /// </summary>
    public Object getNative() {
        return driver.getNative();
    }

    /// <summary>
    /// A new Browser session. Control the lifecycle of this session with using{} / session.dispose()
    /// </summary>
    /// <param name="sessionConfiguration">Your configuration for this session</param>
    /// <returns>The new Session</returns>
    public BrowserSession(SessionConfiguration sessionConfiguration) {
        this(new ActivatorDriverFactory(),
                sessionConfiguration,
                new RetryUntilTimeoutRobustWrapper(),
                new StopwatchWaiter(),
                null, // TODO: Cookies
                new FullyQualifiedUrlBuilder());
    }

    public BrowserSession(DriverFactory driver, SessionConfiguration sessionConfiguration, RobustWrapper robustWrapper, Waiter waiter,
                          RestrictedResourceDownloader restrictedResourceDownloader,
                          UrlBuilder urlBuilder) {
        super(sessionConfiguration, null, driver.newWebDriver(sessionConfiguration.Driver, sessionConfiguration.Browser), robustWrapper, waiter, urlBuilder);
        this.restrictedResourceDownloader = restrictedResourceDownloader;
    }

    public Driver driver() {
        return driver;
    }


    public boolean wasDisposed() {
        return wasDisposed;
    }

    /// <summary>
    /// Disposes the current session, closing any open browser.
    /// </summary>
    public void dispose() {
        if (wasDisposed)
            return;

        driver.dispose();
        ActivatorDriverFactory.OpenDrivers--;

        wasDisposed = true;
    }

    /// <summary>
    /// Saves a resource from the web to a local file using the cookies from the current browser session.
    /// Allows you to sign in through the browser and then directly download a resource restricted to signed-in users.
    /// </summary>
    /// <param name="resource"> The location of the resource to download</param>
    /// <param name="saveAs">Path to save the file to</param>
    public void saveWebResource(String resource, String saveAs) {
        //restrictedResourceDownloader.setCookies(driver.getBrowserCookies());
        //restrictedResourceDownloader.downloadFile(urlBuilder.getFullyQualifiedUrl(resource, configuration), saveAs);
    }

    public BrowserWindow findWindow(String titleOrName) {
        return findWindow(titleOrName, sessionConfiguration);
    }

    public BrowserWindow findWindow(String titleOrName, Options options) {
        return new RobustWindowScope(driver, sessionConfiguration, robustWrapper, waiter, urlBuilder, setOptions(options), new WindowFinder(driver, titleOrName, this));
    }
}
