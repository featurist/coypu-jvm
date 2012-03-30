//package Coypu.Tests.When_interacting_with_the_browser;
//
//import Coypu.BrowserSession;
//import Coypu.Configuration;
//import Coypu.ElementScope;
//import Coypu.Queries.Query;
//import Coypu.Tests.TestBuilders.TestSessionBuilder;
//import Coypu.Tests.TestDoubles.FakeDriver;
//import Coypu.Tests.TestDoubles.FakeWaiter;
//import Coypu.Tests.TestDoubles.SpyRobustWrapper;
//import Coypu.Tests.TestDoubles.StubUrlBuilder;
//import Coypu.TimeSpan;
//import org.junit.Before;
//
//public abstract class BrowserInteractionTests
//{
//    protected FakeDriver driver;
//    protected FakeWaiter fakeWaiter;
//    protected BrowserSession browserSession;
//    protected SpyRobustWrapper spyRobustWrapper;
//    protected StubUrlBuilder stubUrlBuilder;
//    protected Configuration configuration;
//    protected ElementScope elementScope;
//    protected Object queryResult;
//
//    @Before
//    public void SetUp()
//    {
//        driver = new FakeDriver();
//        spyRobustWrapper = new SpyRobustWrapper();
//        fakeWaiter = new FakeWaiter();
//        stubUrlBuilder = new StubUrlBuilder();
//        configuration = new Configuration();
//        browserSession = TestSessionBuilder.Build(configuration, driver, spyRobustWrapper, fakeWaiter, new SpyRestrictedResourceDownloader(),
//                stubUrlBuilder);
//
//        elementScope = browserSession.FindXPath(".");
//    }
//
//    protected Object RunQueryAndCheckTiming()
//    {
//        return RunQueryAndCheckTiming(new Object());
//    }
//
//    protected Object RunQueryAndCheckTiming(TimeSpan timeout)
//    {
//        return RunQueryAndCheckTiming(new Object(), timeout);
//    }
//
//    protected <T> T RunQueryAndCheckTiming(Class<T> type)
//    {
//        return RunQueryAndCheckTiming(type, configuration.Timeout);
//    }
//
//    protected <T> T RunQueryAndCheckTiming(Class<T> type, TimeSpan timeout)
//    {
//        Query<T> query = spyRobustWrapper.QueriesRan(type).Single();
//        RunQueryAndCheckTiming(query, timeout);
//
//        return query.Result();
//    }
//
//    protected <T> T RunQueryAndCheckTiming(Class<T> type, TimeSpan timeout, int index)
//    {
//        var query = spyRobustWrapper.QueriesRan(type).ElementAt(index);
//        RunQueryAndCheckTiming(query, timeout);
//
//        return query.Result;
//    }
//
//    protected <T> T RunQueryAndCheckTiming(Query<T> query)
//    {
//        return RunQueryAndCheckTiming(query, configuration.Timeout);
//    }
//
//    protected <T> T RunQueryAndCheckTiming(Query<T> query, TimeSpan timeout)
//    {
//        query.Run();
//
//        queryResult = query.Result;
//
//        assertThat(query.Timeout, is(equalTo((timeout));
//        assertThat(query.RetryInterval, is(equalTo((configuration.RetryInterval));
//
//        return query.Result;
//    }
//}
//
