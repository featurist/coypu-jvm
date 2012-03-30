//package Coypu.Tests.When_interacting_with_the_browser;
//
//import Coypu.Element;
//import Coypu.Options;
//import Coypu.Queries.PredicateQuery;
//import Coypu.Tests.TestDoubles.SpyRobustWrapper;
//import Coypu.Tests.TestDoubles.StubElement;
//import Coypu.TimeSpan;
//import org.junit.Test;
//
//import java.util.UUID;
//
//import static Coypu.Tests.When_interacting_with_the_browser.HasMember.hasMember;
//import static org.junit.Assert.assertThat;
//
//public class When_clicking_buttons extends BrowserInteractionTests
//{
//    @Test
//    public void It_robustly_finds_by_text_and_clicks()
//    {
//        StubElement buttonToBeClicked = StubButtonToBeClicked("Some button locator");
//
//        browserSession.ClickButton("Some button locator");
//
//        AssertButtonNotClickedYet(buttonToBeClicked);
//
//        RunQueryAndCheckTiming();
//
//        AssertClicked(buttonToBeClicked);
//    }
//
//    private void AssertClicked(StubElement buttonToBeClicked)
//    {
//        assertThat(driver.ClickedElements, hasMember((Element) buttonToBeClicked));
//    }
//
////    [TestCase(true, 1)]
////    [TestCase(false, 1)]
////    [TestCase(false, 321)]
//    public void It_tries_clicking_robustly_until_expected_conditions_met(boolean stubUntil, int waitBeforeRetrySecs)
//    {
//        TimeSpan waitBetweenRetries = TimeSpan.FromSeconds(waitBeforeRetrySecs);
//        StubElement buttonToBeClicked = StubButtonToBeClicked("Some button locator");
//        TimeSpan overallTimeout = TimeSpan.FromMilliseconds(waitBeforeRetrySecs + 1000);
//
//        Options options = new Options();
//        options.Timeout = overallTimeout;
//
//        browserSession.ClickButton("Some button locator", new PredicateQuery() {
//            @Override
//            public boolean Predicate() {
//                () => stubUntil waitBetweenRetries, options
//            }
//        }
//
//        SpyRobustWrapper.TryUntilArgs tryUntilArgs = spyRobustWrapper.DeferredTryUntils.get(0);
//0
//        AssertButtonNotClickedYet(buttonToBeClicked);
//        tryUntilArgs.TryThisBrowserAction.Act();
//        AssertClicked(buttonToBeClicked);
//
//        tryUntilArgs.Until.Run();
//        assertThat(tryUntilArgs.Until.Result, Is.EqualTo(stubUntil));
//        assertThat(tryUntilArgs.WaitBeforeRetry, Is.EqualTo(waitBetweenRetries));
//        assertThat(tryUntilArgs.OverallTimeout, Is.EqualTo(overallTimeout));
//    }
//
//    [TestCase(200)]
//    [TestCase(300)]
//    public void It_waits_between_find_and_click_as_configured(int waitMs)
//    {
//        var stubButtonToBeClicked = StubButtonToBeClicked("Some button locator");
//        var expectedWait = TimeSpan.FromMilliseconds(waitMs);
//        configuration.WaitBeforeClick = expectedWait;
//
//        var waiterCalled = false;
//        fakeWaiter.DoOnWait(milliseconds =>
//        {
//            assertThat(milliseconds, Is.EqualTo(expectedWait));
//
//            AssertButtonFound();
//            AssertButtonNotClickedYet(stubButtonToBeClicked);
//
//            waiterCalled = true;
//        });
//        browserSession.ClickButton("Some button locator");
//        spyRobustWrapper.QueriesRan<object>().Last().Run();
//
//        assertThat(waiterCalled, "The waiter was not called");
//        AssertClicked(stubButtonToBeClicked);
//    }
//
//    private void AssertButtonFound()
//    {
//        assertThat(driver.FindButtonRequests.Contains("Some button locator"), "Wait called before find");
//    }
//
//    private void AssertButtonNotClickedYet(StubElement buttonToBeClicked)
//    {
//        assertThat(driver.ClickedElements, Has.No.Member(buttonToBeClicked));
//    }
//
//    private StubElement StubButtonToBeClicked(String locator)
//    {
//        StubElement buttonToBeClicked = new StubElement();
//        buttonToBeClicked.setId(UUID.randomUUID().toString());
//        driver.StubButton(locator, buttonToBeClicked, browserSession);
//        return buttonToBeClicked;
//    }
//}
