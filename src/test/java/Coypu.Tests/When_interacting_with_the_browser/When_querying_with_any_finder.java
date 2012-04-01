package Coypu.Tests.When_interacting_with_the_browser;

import Coypu.Configuration;
import Coypu.Queries.Query;
import Coypu.Tests.TestBuilders.TestSessionBuilder;
import Coypu.Tests.TestDoubles.ImmediateSingleExecutionFakeRobustWrapper;
import Coypu.Tests.TestDoubles.StubElement;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_querying_with_any_finder extends BrowserInteractionTests
{
    @Test
    public void it_reports_that_a_findable_element_exists()
    {
        browserSession = TestSessionBuilder.build(new Configuration(), driver, new ImmediateSingleExecutionFakeRobustWrapper(), null, null, null);
        driver.stubLink("Sign out", new StubElement(), browserSession);

        assertThat(browserSession.findLink("Sign out").exists(), is(true));
    }

    @Test
    public void it_reports_that_a_missing_element_does_not_exist()
    {
        browserSession = TestSessionBuilder.build(new Configuration(), driver, new ImmediateSingleExecutionFakeRobustWrapper(), null, null, null);
        driver.stubLink("Sign out", new StubElement(), browserSession);

        assertThat(browserSession.findLink("Sign in").exists(), is(false));
    }

    @Test
    public void it_reports_that_a_missing_element_is_missing()
    {
        browserSession = TestSessionBuilder.build(new Configuration(), driver, new ImmediateSingleExecutionFakeRobustWrapper(), null, null, null);
        driver.stubLink("Sign out", new StubElement(), browserSession);

        assertThat(browserSession.findLink("Sign in").missing(), is(true));
    }

    @Test
    public void it_reports_that_a_findable_element_is_not_missing()
    {
        browserSession = TestSessionBuilder.build(new Configuration(), driver, new ImmediateSingleExecutionFakeRobustWrapper(), null, null, null);
        driver.stubLink("Sign out", new StubElement(), browserSession);

        assertThat(browserSession.findLink("Sign out").missing(), is(false));
    }

    @Test
    public void it_checks_for_existing_elements_with_a_RobustQuery()
    {
        spyRobustWrapper.stubQueryResult(true,false);

        driver.stubLink("Sign out", new StubElement(), browserSession);

        browserSession.findLink("Sign in").exists();
        browserSession.findLink("Sign out").exists();

        Query firstQuery = spyRobustWrapper.queriesRan().get(0);
        Query secondQuery = spyRobustWrapper.queriesRan().get(1);

        assertThat(runQueryAndCheckTiming(firstQuery), is((Object) false));
        assertThat(runQueryAndCheckTiming(secondQuery), is((Object) true));
    }

    @Test
    public void it_checks_for_missing_elements_with_a_RobustQuery()
    {
        spyRobustWrapper.stubQueryResult(true, false);

        driver.stubLink("Sign out", new StubElement(), browserSession);
        browserSession.findLink("Sign in").missing();
        browserSession.findLink("Sign out").missing();

        Query firstQuery = spyRobustWrapper.queriesRan().get(0);
        Query secondQuery = spyRobustWrapper.queriesRan().get(1);

        assertThat(runQueryAndCheckTiming(firstQuery), is((Object) true));
        assertThat(runQueryAndCheckTiming(secondQuery), is((Object) false));
    }
}
