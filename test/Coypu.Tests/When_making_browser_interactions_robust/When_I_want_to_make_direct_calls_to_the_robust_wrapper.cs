using System;
using System.Linq;
using Coypu.Tests.TestDoubles;
using Coypu.Tests.When_interacting_with_the_browser;
using NUnit.Framework;

package Coypu.Tests.When_making_browser_interactions_robust
{
    [TestFixture]
    public class When_I_want_to_make_direct_calls_to_the_robust_wrapper : BrowserInteractionTests
    {
        @Test
        public void RobustAction_is_exposed_on_the_session()
        {
            var calledOnWrapper = false;
            browserSession.RetryUntilTimeout(() =>
            {
                calledOnWrapper = true;
            });
            spyRobustWrapper.QueriesRan<object>().First().Run();
            assertThat(calledOnWrapper, Is.True);
        }

        @Test
        public void RobustFunction_is_exposed_on_the_session()
        {
            Func<string> function = () => "The expected result";

            spyRobustWrapper.StubQueryResult(SpyRobustWrapper.NO_EXPECTED_RESULT, "immediate result");

            assertThat(browserSession.RetryUntilTimeout(function), Is.EqualTo("immediate result"));

            var query = spyRobustWrapper.QueriesRan<string>().First();
            query.Run();

            assertThat(query.Result, Is.EqualTo("The expected result"));
        }

        @Test
        public void TryUntil_is_exposed_on_the_session()
        {
            var tried = false;
            var triedUntil = false;
            Action tryThis = () => tried = true;
            Func<bool> until = () => triedUntil = true;
            var overallTimeout = TimeSpan.FromMilliseconds(1234);

            var options = new Options { Timeout = overallTimeout };
            browserSession.TryUntil(tryThis, until,TimeSpan.Zero,options);

            var tryUntil = spyRobustWrapper.DeferredTryUntils[0];

            assertThat(tried, Is.False);
            tryUntil.TryThisBrowserAction.Act();
            assertThat(tried, Is.True);

            assertThat(triedUntil, Is.False);
            tryUntil.Until.Run();
            assertThat(triedUntil, Is.True);

            assertThat(tryUntil.OverallTimeout, Is.EqualTo(overallTimeout));
        }

        @Test
        public void Query_is_exposed_on_the_session()
        {
            Func<string> query = () => "query result";

            spyRobustWrapper.StubQueryResult("expected query result", "immediate query result");

            assertThat(browserSession.Query(query, "expected query result"), Is.EqualTo("immediate query result"));

            var robustQuery = spyRobustWrapper.QueriesRan<string>().First();
            robustQuery.Run();

            assertThat(robustQuery.Result, Is.EqualTo("query result"));
        }
    }
}