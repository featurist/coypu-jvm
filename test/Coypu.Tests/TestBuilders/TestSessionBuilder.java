package Coypu.Tests.TestBuilders;

import Coypu.BrowserSession;
import Coypu.Configuration;
import Coypu.Driver;
import Coypu.Robustness.RobustWrapper;
import Coypu.Robustness.Waiter;
import Coypu.Tests.TestDoubles.StubDriverFactory;
import Coypu.UrlBuilder;
import Coypu.WebRequests.RestrictedResourceDownloader;

public class TestSessionBuilder
{
    public static BrowserSession Build(Configuration configuration, Driver driver, RobustWrapper robustWrapper, Waiter waiter,
                                  RestrictedResourceDownloader restrictedResourceDownloader,
                                  UrlBuilder urlBuilder)
    {
        return new BrowserSession(new StubDriverFactory(driver), configuration, robustWrapper, waiter, restrictedResourceDownloader, urlBuilder);
    }
}
