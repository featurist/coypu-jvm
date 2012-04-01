package Coypu.Tests.When_interacting_with_the_browser;

import Coypu.*;
import Coypu.Queries.PredicateQuery;
import Coypu.Robustness.RobustWrapper;
import Coypu.Tests.TestBuilders.TestSessionBuilder;
import Coypu.Tests.TestDoubles.FakeDriver;
import Coypu.Tests.TestDoubles.FakeWaiter;
import Coypu.Tests.TestDoubles.ImmediateSingleExecutionFakeRobustWrapper;
import Coypu.Tests.TestDoubles.SpyRobustWrapper;
import Coypu.Tests.When_making_browser_interactions_robust.AlwaysSucceedsPredicateQuery;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class When_finding_state
{
    public BrowserSession buildSession(RobustWrapper robustWrapper)
    {
        configuration = new Configuration();
        return TestSessionBuilder.build(configuration, new FakeDriver(), robustWrapper, new FakeWaiter(), null, null);
    }

    private Configuration configuration;

    @Test
    public void it_checks_all_of_the_states_in_a_robust_query_expecting_true()
    {
        final boolean[] queriedState1 = {false};
        final boolean[] queriedState2 = {false};

        State state1 = new State() {
            public Boolean predicate() {
                queriedState1[0] = true;
                return false;
            }
        };
        State state2 = new State() {
            public Boolean predicate() {
                queriedState2[0] = true;
                return true;
            }
        };
        State state3 = new State() {
            public Boolean predicate() {
                return true;
            }
        };
        
        state3.checkCondition();

        SpyRobustWrapper robustWrapper = new SpyRobustWrapper();
        robustWrapper.stubQueryResult(true, true);

        BrowserSession session = buildSession(robustWrapper);

        assertThat(session.findState(state1, state2, state3), is(sameInstance(state3)));

        assertThat(queriedState1[0], is(false));
        assertThat(queriedState2[0], is(false));

        PredicateQuery query = (PredicateQuery) robustWrapper.queriesRan().get(0);

        assertThat(query.run(), is(true));

        assertThat(queriedState1[0], is(true));
        assertThat(queriedState2[0], is(true));
    }

    @Test
    public void it_returns_the_state_that_was_found_first_Example_1()
    {
        BrowserSession session = buildSession(new ImmediateSingleExecutionFakeRobustWrapper());

        State state1 = new AlwaysSucceedsPredicateQuery(true, TimeSpan.zero(), configuration.RetryInterval);
        State state2 = new AlwaysSucceedsPredicateQuery(false, TimeSpan.zero(), configuration.RetryInterval);
        State state3 = new AlwaysSucceedsPredicateQuery(false, TimeSpan.zero(), configuration.RetryInterval);

        State foundState = session.findState(state1, state2, state3);

        assertThat(foundState, is(sameInstance(state1)));
    }

    @Test
    public void it_returns_the_state_that_was_found_first_Example_2()
    {
        BrowserSession session = buildSession(new ImmediateSingleExecutionFakeRobustWrapper());
        
        State state1 = new AlwaysSucceedsPredicateQuery(false, TimeSpan.zero(), configuration.RetryInterval);
        State state2 = new AlwaysSucceedsPredicateQuery(true, TimeSpan.zero(), configuration.RetryInterval);
        State state3 = new AlwaysSucceedsPredicateQuery(false, TimeSpan.zero(), configuration.RetryInterval);

        State foundState = session.findState(state1, state2, state3);

        assertThat(foundState, is(sameInstance(state2)));
    }

    @Test
    public void it_returns_the_state_that_was_found_first_Example_3()
    {
        BrowserSession session = buildSession(new ImmediateSingleExecutionFakeRobustWrapper());
        
        State state1 = new AlwaysSucceedsPredicateQuery(false, TimeSpan.zero(), configuration.RetryInterval);
        State state2 = new AlwaysSucceedsPredicateQuery(false, TimeSpan.zero(), configuration.RetryInterval);
        State state3 = new AlwaysSucceedsPredicateQuery(true, TimeSpan.zero(), configuration.RetryInterval);

        State foundState = session.findState(state1, state2, state3);

        assertThat(foundState, is(sameInstance(state3)));
    }

    @Test
    public void it_uses_a_zero_timeout_when_evaluating_the_conditions()
    {
        final RobustWrapper robustWrapper = new ImmediateSingleExecutionFakeRobustWrapper();
        final boolean[] zeroTimeout1 = {false};
        final boolean[] zeroTimeout2 = {false};
        State state1 = new State() {
            public Boolean predicate() {
                zeroTimeout1[0] = robustWrapper.getZeroTimeout();
                return false;
            }
        };
        
        State state2 = new State() {
            public Boolean predicate() {
                zeroTimeout2[0] = robustWrapper.getZeroTimeout();
                return true;                
            }
        };

        BrowserSession session = buildSession(robustWrapper);
        session.findState(state1, state2);

        assertThat(zeroTimeout1[0], is(equalTo(true)));
        assertThat(zeroTimeout2[0], is(equalTo(true)));

        assertThat(robustWrapper.getZeroTimeout(), is(equalTo(false)));
    }


    @Test
    public void when_query_returns_false_It_raises_an_exception()
    {
        SpyRobustWrapper robustWrapper = new SpyRobustWrapper();
        BrowserSession session = buildSession(robustWrapper);

        State state1 = new AlwaysSucceedsPredicateQuery(false, TimeSpan.zero(), configuration.RetryInterval);
        State state2 = new AlwaysSucceedsPredicateQuery(false, TimeSpan.zero(), configuration.RetryInterval);

        robustWrapper.stubQueryResult(true, false);

        try
        {
            session.findState(state1, state2);
            fail("Expected MissingHtmlException");
        }
        catch (MissingHtmlException e)
        {
            assertThat(e.getMessage(), is(equalTo("None of the given states was reached within the configured getTimeout.")));
        }
    }
}