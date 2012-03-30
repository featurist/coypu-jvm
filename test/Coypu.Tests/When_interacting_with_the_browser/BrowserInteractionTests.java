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
    protected Object queryResult;

    @Before
    public void SetUp()
    {
        driver = new FakeDriver();
        spyRobustWrapper = new SpyRobustWrapper();
        fakeWaiter = new FakeWaiter();
        stubUrlBuilder = new StubUrlBuilder();
        configuration = new Configuration();
        browserSession = TestSessionBuilder.Build(configuration, driver, spyRobustWrapper, fakeWaiter, new SpyRestrictedResourceDownloader(),
                stubUrlBuilder);

        elementScope = browserSession.FindXPath(".");
    }

    protected Object RunQueryAndCheckTiming()
    {
        return RunQueryAndCheckTiming(Object.class);
    }

    protected Object RunQueryAndCheckTiming(TimeSpan timeout)
    {
        return RunQueryAndCheckTiming(Object.class, timeout);
    }

    protected <T> T RunQueryAndCheckTiming(Class<T> type)
    {
        return RunQueryAndCheckTiming(type, configuration.Timeout);
    }

    protected <T> T RunQueryAndCheckTiming(Class<T> type, TimeSpan timeout)
    {
        Query<T> query = spyRobustWrapper.QueriesRan(type).get(0);
        RunQueryAndCheckTiming(query, timeout);

        return query.Result();
    }

    protected <T> T RunQueryAndCheckTiming(Class<T> type, TimeSpan timeout, int index)
    {
        Query<T> query = spyRobustWrapper.QueriesRan(type).get(index);
        RunQueryAndCheckTiming(query, timeout);

        return query.Result();
    }

    protected <T> T RunQueryAndCheckTiming(Query<T> query)
    {
        return RunQueryAndCheckTiming(query, configuration.Timeout);
    }

    protected <T> T RunQueryAndCheckTiming(Query<T> query, TimeSpan timeout)
    {
        query.Run();

        queryResult = query.Result();

        assertThat(query.Timeout(), is(equalTo((timeout))));
        assertThat(query.RetryInterval(), is(equalTo((configuration.RetryInterval))));

        return query.Result();
    }
}

