package Coypu.Tests.When_interacting_with_the_browser;

import Coypu.Element;
import Coypu.Options;
import Coypu.Queries.PredicateQuery;
import Coypu.Queries.Query;
import Coypu.Tests.TestDoubles.Action;
import Coypu.Tests.TestDoubles.SpyRobustWrapper;
import Coypu.Tests.TestDoubles.StubElement;
import Coypu.TimeSpan;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class When_clicking_buttons extends BrowserInteractionTests
{
    @Test
    public void it_robustly_finds_by_text_and_clicks()
    {
        StubElement buttonToBeClicked = stubButtonToBeClicked("Some button locator");

        browserSession.clickButton("Some button locator");

        assertButtonNotClickedYet(buttonToBeClicked);

        runQueryAndCheckTiming();

        assertClicked(buttonToBeClicked);
    }

    private void assertClicked(StubElement buttonToBeClicked)
    {
        assertThat(driver.ClickedElements, hasItem((Element) buttonToBeClicked));
    }

    @Test
    public void it_tries_clicking_robustly_until_expected_conditions_met_example_1()
    {
        it_tries_clicking_robustly_until_expected_conditions_met(true, 1);
    }

    @Test
    public void it_tries_clicking_robustly_until_expected_conditions_met_example_2()
    {
        it_tries_clicking_robustly_until_expected_conditions_met(false, 1);
    }

    @Test
    public void it_tries_clicking_robustly_until_expected_conditions_met_example_3()
    {
        it_tries_clicking_robustly_until_expected_conditions_met(false, 321);
    }
    public void it_tries_clicking_robustly_until_expected_conditions_met(final boolean stubUntil, int waitBeforeRetrySecs)
    {
        TimeSpan waitBetweenRetries = TimeSpan.fromSeconds(waitBeforeRetrySecs);
        StubElement buttonToBeClicked = stubButtonToBeClicked("Some button locator");
        TimeSpan overallTimeout = TimeSpan.fromMilliseconds(waitBeforeRetrySecs + 1000);

        Options options = new Options();
        options.Timeout = overallTimeout;

        browserSession.clickButton("Some button locator", new PredicateQuery() {
            @Override
            public Boolean predicate() {
                return stubUntil;
            }
        }, waitBetweenRetries, options);

        SpyRobustWrapper.TryUntilArgs tryUntilArgs = spyRobustWrapper.DeferredTryUntils.get(0);

        assertButtonNotClickedYet(buttonToBeClicked);
        tryUntilArgs.TryThisBrowserAction.act();
        assertClicked(buttonToBeClicked);

        assertThat(tryUntilArgs.Until.run(), is(equalTo(stubUntil)));
        assertThat(tryUntilArgs.WaitBeforeRetry, is(equalTo(waitBetweenRetries)));
        assertThat(tryUntilArgs.OverallTimeout, is(equalTo(overallTimeout)));
    }

    @Test
    public void it_waits_between_find_and_click_as_configured_example_1()
    {
        it_waits_between_find_and_click_as_configured(200);
    }

    @Test
    public void it_waits_between_find_and_click_as_configured_example_2()
    {
        it_waits_between_find_and_click_as_configured(300);
    }

    public void it_waits_between_find_and_click_as_configured(int waitMs)
    {
        final StubElement stubButtonToBeClicked = stubButtonToBeClicked("Some button locator");
        final TimeSpan expectedWait = TimeSpan.fromMilliseconds(waitMs);
        configuration.WaitBeforeClick = expectedWait;

        final boolean[] waiterCalled = {false};

        fakeWaiter.doOnWait(new Action<TimeSpan>() {
            @Override
            public void invoke(TimeSpan on) {
                assertThat(on, is(sameInstance(expectedWait)));

                assertButtonFound();
                assertButtonNotClickedYet(stubButtonToBeClicked);

                waiterCalled[0] = true;
            }
        });

        browserSession.clickButton("Some button locator");
        
        Query lastQuery = spyRobustWrapper.queriesRan().get(spyRobustWrapper.queriesRan().size() - 1);
        lastQuery.run();

        assertThat("The waiter was not called", waiterCalled[0], is(true));
        assertClicked(stubButtonToBeClicked);
    }

    private void assertButtonFound()
    {
        assertThat("Wait called before find", driver.FindButtonRequests, hasItem("Some button locator"));
    }

    private void assertButtonNotClickedYet(Element buttonToBeClicked)
    {
        assertThat(driver.ClickedElements, not(hasItem(buttonToBeClicked)));
    }

    private StubElement stubButtonToBeClicked(String locator)
    {
        StubElement buttonToBeClicked = new StubElement();
        buttonToBeClicked.setId(UUID.randomUUID().toString());
        driver.stubButton(locator, buttonToBeClicked, browserSession);
        return buttonToBeClicked;
    }
}
