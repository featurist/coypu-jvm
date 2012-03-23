package Coypu;

import Coypu.Finders.WindowFinder;
import Coypu.Robustness.RetryUntilTimeoutRobustWrapper;
import Coypu.Robustness.RobustWrapper;
import Coypu.Robustness.StopwatchWaiter;
import Coypu.Robustness.Waiter;
//import Coypu.WebRequests.RestrictedResourceDownloader;
//import Coypu.WebRequests.WebClientWithCookies;

/// <summary>
/// A browser session
/// </summary>
public class BrowserSession extends BrowserWindow
{

    private boolean wasDisposed;
    //private final RestrictedResourceDownloader restrictedResourceDownloader;

    /// <summary>
    /// A new browser session. Control the lifecycle of this session with using{} / session.Dispose()
    /// </summary>
    /// <returns>The new session with default configuration </returns>
    public BrowserSession()
    {
        this(new Configuration());
    }

    /// <summary>
    /// A new browser session. Control the lifecycle of this session with using{} / session.Dispose()
    /// </summary>
    /// <param name="configuration">Your configuration for this session</param>
    /// <returns>The new session</returns>
    public BrowserSession(Configuration configuration)
    {
        this(new ActivatorDriverFactory(),
                configuration,
                new RetryUntilTimeoutRobustWrapper(),
                new StopwatchWaiter(),
                //new WebClientWithCookies(),
                new FullyQualifiedUrlBuilder());
    }

    public BrowserSession(DriverFactory driver, Configuration configuration, RobustWrapper robustWrapper, Waiter waiter,
                          //RestrictedResourceDownloader restrictedResourceDownloader,
                          UrlBuilder urlBuilder)
    {
        super(configuration, null, driver.NewWebDriver(configuration.Driver, configuration.Browser), robustWrapper, waiter, urlBuilder);
        //this.restrictedResourceDownloader = restrictedResourceDownloader;
    }

    public Driver Driver()
    {
        return driver;
    }


    public boolean WasDisposed() {
        return wasDisposed;
    }

    /// <summary>
    /// Disposes the current session, closing any open browser.
    /// </summary>
    public void Dispose()
    {
        if (wasDisposed)
            return;

        driver.Dispose();
        ActivatorDriverFactory.OpenDrivers--;

        wasDisposed = true;
    }

    /// <summary>
    /// Saves a resource from the web to a local file using the cookies from the current browser session.
    /// Allows you to sign in through the browser and then directly download a resource restricted to signed-in users.
    /// </summary>
    /// <param name="resource"> The location of the resource to download</param>
    /// <param name="saveAs">Path to save the file to</param>
    public void SaveWebResource(String resource, String saveAs)
    {
        //restrictedResourceDownloader.SetCookies(driver.GetBrowserCookies());
        //restrictedResourceDownloader.DownloadFile(urlBuilder.GetFullyQualifiedUrl(resource, configuration), saveAs);
    }

    public BrowserWindow FindWindow(String titleOrName, Options options)
    {
        return new RobustWindowScope(driver, configuration, robustWrapper, waiter, urlBuilder, SetOptions(options), new WindowFinder(driver, titleOrName, this));
    }
}
