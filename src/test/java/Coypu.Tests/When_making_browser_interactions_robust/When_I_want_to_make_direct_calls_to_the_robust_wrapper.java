package Coypu.Tests.When_making_browser_interactions_robust;

import Coypu.Actions.BrowserAction;
import Coypu.Options;
import Coypu.Queries.PredicateQuery;
import Coypu.Queries.Query;
import Coypu.Tests.TestDoubles.SpyRobustWrapper;
import Coypu.Tests.When_interacting_with_the_browser.BrowserInteractionTests;
import Coypu.TimeSpan;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_I_want_to_make_direct_calls_to_the_robust_wrapper extends BrowserInteractionTests
{
    @Test
    public void retryUntilTimeout_is_exposed_on_the_session()
    {
        final boolean[] calledOnWrapper = {false};
        browserSession.retryUntilTimeout(new BrowserAction(configuration) {
            public void act() {
                calledOnWrapper[0] = true;
            }
        });
        spyRobustWrapper.queriesRan().get(0).run();
        assertThat(calledOnWrapper[0], is(true));
    }

    @Test
    public void query_is_exposed_on_the_session()
    {
        Query<String> function = new Query<String>() {
            @Override
            public String run() {
                return "The expected result";
            }

            @Override
            public String getExpectedResult() {
                return null;
            }
        };

        spyRobustWrapper.stubQueryResult(SpyRobustWrapper.NO_EXPECTED_RESULT, "immediate result");

        assertThat(browserSession.query(function), is(equalTo("immediate result")));

        Query<String> query = spyRobustWrapper.queriesRan().get(0);
        String result = query.run();

        assertThat(result, is(equalTo("The expected result")));
    }

    @Test
    public void tryUntil_is_exposed_on_the_session()
    {
        final boolean[] tried = {false};
        final boolean[] triedUntil = {false};
        BrowserAction tryThis = new BrowserAction() {
            @Override
            public void act() {
                tried[0] = true;
            }
        };
        
        PredicateQuery until = new PredicateQuery() {
            @Override
            public Boolean predicate() {
                triedUntil[0] = true;
                return true;
            }
        };
        TimeSpan overallTimeout = TimeSpan.fromMilliseconds(1234);

        Options options = new Options();
        options.Timeout = overallTimeout;
        browserSession.tryUntil(tryThis, until,TimeSpan.zero(),options);

        SpyRobustWrapper.TryUntilArgs tryUntil = spyRobustWrapper.DeferredTryUntils.get(0);

        assertThat(tried[0], is(false));
        tryUntil.TryThisBrowserAction.act();
        assertThat(tried[0], is(true));

        assertThat(triedUntil[0], is(false));
        tryUntil.Until.run();
        assertThat(triedUntil[0], is(true));

        assertThat(tryUntil.OverallTimeout, is(equalTo(overallTimeout)));
    }
}