package coypu.unitTests.TestBuilders;

import coypu.BrowserSession;
import coypu.SessionConfiguration;
import coypu.Driver;
import coypu.Robustness.RobustWrapper;
import coypu.Robustness.Waiter;
import coypu.unitTests.TestDoubles.StubDriverFactory;
import coypu.UrlBuilder;
import coypu.WebRequests.RestrictedResourceDownloader;

public class TestSessionBuilder
{
    public static BrowserSession build(SessionConfiguration sessionConfiguration, Driver driver, RobustWrapper robustWrapper, Waiter waiter,
                                  RestrictedResourceDownloader restrictedResourceDownloader,
                                  UrlBuilder urlBuilder)
    {
        return new BrowserSession(new StubDriverFactory(driver), sessionConfiguration, robustWrapper, waiter, restrictedResourceDownloader, urlBuilder);
    }
}
