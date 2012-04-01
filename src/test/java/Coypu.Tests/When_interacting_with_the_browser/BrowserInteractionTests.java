package Coypu.Tests.When_interacting_with_the_browser;

import Coypu.BrowserSession;
import Coypu.Configuration;
import Coypu.ElementScope;
import Coypu.Queries.Query;
import Coypu.Tests.TestBuilders.TestSessionBuilder;
import Coypu.Tests.TestDoubles.*;
import Coypu.TimeSpan;
import org.junit.Before;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public abstract class BrowserInteractionTests
{
    protected FakeDriver driver;
    protected FakeWaiter fakeWaiter;
    protected BrowserSession browserSession;
    protected SpyRobustWrapper spyRobustWrapper;
    protected StubUrlBuilder stubUrlBuilder;
    protected Configuration configuration;
    protected ElementScope elementScope;

    @Before
    public void baseSetUp()
    {
        driver = new FakeDriver();
        spyRobustWrapper = new SpyRobustWrapper();
        fakeWaiter = new FakeWaiter();
        stubUrlBuilder = new StubUrlBuilder();
        configuration = new Configuration();
        browserSession = TestSessionBuilder.build(configuration, driver, spyRobustWrapper, fakeWaiter, new SpyRestrictedResourceDownloader(),
                stubUrlBuilder);

        elementScope = browserSession.findXPath(".");
    }

    protected <T> T runQueryAndCheckTiming()
    {
        return runQueryAndCheckTiming(configuration.Timeout);
    }

    protected <T> T runQueryAndCheckTiming(TimeSpan timeout)
    {
        Query<T> query = (Query<T>) spyRobustWrapper.queriesRan().get(0);
        return runQueryAndCheckTiming(query, timeout);
    }

    protected <T> T runQueryAndCheckTiming(TimeSpan timeout, int index)
    {
        Query<T> query = (Query<T>) spyRobustWrapper.queriesRan().get(index);
        return runQueryAndCheckTiming(query, timeout);
    }

    protected <T> T runQueryAndCheckTiming(Query<T> query)
    {
        return runQueryAndCheckTiming(query, configuration.Timeout);
    }

    protected <T> T runQueryAndCheckTiming(Query<T> query, TimeSpan timeout)
    {
        T result = query.run();

        assertThat(query.getTimeout().getMilliseconds(), is(equalTo((timeout.getMilliseconds()))));
        assertThat(query.getRetryInterval().getMilliseconds(), is(equalTo((configuration.RetryInterval.getMilliseconds()))));

        return result;
    }
}

