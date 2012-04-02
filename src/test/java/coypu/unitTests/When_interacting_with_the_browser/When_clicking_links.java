package coypu.unitTests.When_interacting_with_the_browser;

import coypu.Element;
import coypu.Options;
import coypu.Queries.PredicateQuery;
import coypu.Queries.Query;
import coypu.unitTests.TestDoubles.Action;
import coypu.unitTests.TestDoubles.SpyRobustWrapper;
import coypu.unitTests.TestDoubles.StubElement;
import coypu.TimeSpan;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class When_clicking_links extends BrowserInteractionTests
{
    @Test
    public void it_robustly_finds_by_text_and_clicks()
    {
        StubElement linkToBeClicked = stubLinkToBeClicked("Some link locator");

        browserSession.clickLink("Some link locator");

        assertButtonNotClickedYet(linkToBeClicked);

        runQueryAndCheckTiming();

        assertClicked(linkToBeClicked);
    }

    private void assertClicked(Element linkToBeClicked)
    {
        assertThat(driver.ClickedElements, hasItem(linkToBeClicked));
    }

    @Test
    public void it_tries_clicking_robustly_until_expected_conditions_met_case_1(){
        it_tries_clicking_robustly_until_expected_conditions_met(false,1);
    }

    @Test
    public void it_tries_clicking_robustly_until_expected_conditions_met_case_2(){
        it_tries_clicking_robustly_until_expected_conditions_met(true,1);
    }

    @Test
    public void it_tries_clicking_robustly_until_expected_conditions_met_case_3() {
        it_tries_clicking_robustly_until_expected_conditions_met(false,321);
    }
    
    public void it_tries_clicking_robustly_until_expected_conditions_met(final boolean stubUntil, int waitBeforeRetrySecs)
    {
        TimeSpan waitBetweenRetries = TimeSpan.fromSeconds(waitBeforeRetrySecs);
        TimeSpan overallTimeout = TimeSpan.fromSeconds(waitBeforeRetrySecs + 1000);
        Element linkToBeClicked = stubLinkToBeClicked("Some link locator");

        Options options = new Options();
        options.Timeout = overallTimeout;
        browserSession.clickLink("Some link locator", new PredicateQuery() {
            public Boolean predicate() {
                return stubUntil;
            }
        }, waitBetweenRetries,options);

        SpyRobustWrapper.TryUntilArgs tryUntilArgs = getTryUntilArgs();

        assertButtonNotClickedYet(linkToBeClicked);
        tryUntilArgs.TryThisBrowserAction.act();
        assertClicked(linkToBeClicked);

        assertThat(tryUntilArgs.Until.run(), is(equalTo(stubUntil)));
        assertThat(tryUntilArgs.WaitBeforeRetry, is(equalTo(waitBetweenRetries)));
        assertThat(tryUntilArgs.OverallTimeout, is(equalTo(overallTimeout)));
    }

    @Test
    public void it_waits_between_find_and_click_as_configured_case_1()
    {
        it_waits_between_find_and_click_as_configured(200);
    }

    @Test
    public void it_waits_between_find_and_click_as_configured_case_2()
    {
        it_waits_between_find_and_click_as_configured(200);
    }
    
    public void it_waits_between_find_and_click_as_configured(int waitMs)
    {
        final Element stubLinkToBeClicked = stubLinkToBeClicked("Some link locator");
        final TimeSpan expectedWait = TimeSpan.fromMilliseconds(waitMs);
        sessionConfiguration.WaitBeforeClick = expectedWait;

        final boolean[] waiterCalled = {false};
        fakeWaiter.doOnWait(new Action<TimeSpan>() {
            @Override
            public void invoke(TimeSpan on) {
                assertThat(on.getMilliseconds(), is(equalTo(expectedWait.getMilliseconds())));

                assertButtonFound();
                assertButtonNotClickedYet(stubLinkToBeClicked);

                waiterCalled[0] = true;
            }
        });
        browserSession.clickLink("Some link locator");
        executeLastDeferedRobustAction();

        assertThat("The waiter was not called",waiterCalled[0], is(true));
        assertClicked(stubLinkToBeClicked);
    }

    private void assertButtonFound()
    {
        assertThat("Wait called before find", driver.FindLinkRequests, hasItem("Some link locator"));
    }

    private void assertButtonNotClickedYet(Element linkToBeClicked)
    {
        assertThat("Expected link not to have been clicked yet, but it has been", driver.ClickedElements, not(hasItem(linkToBeClicked)));
    }

    private SpyRobustWrapper.TryUntilArgs getTryUntilArgs()
    {
        return spyRobustWrapper.DeferredTryUntils.get(0);
    }

    private void executeLastDeferedRobustAction()
    {
        List<Query> queries = spyRobustWrapper.queriesRan();
        queries.get(queries.size() - 1).run();
    }

    private StubElement stubLinkToBeClicked(String someLinkLocator)
    {
        StubElement linkToBeClicked = new StubElement();
        linkToBeClicked.setId(UUID.randomUUID().toString());
        driver.stubLink(someLinkLocator, linkToBeClicked, browserSession);
        return linkToBeClicked;
    }
}
