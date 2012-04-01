package Coypu.Tests.When_interacting_with_the_browser;

import Coypu.*;
import Coypu.Tests.TestDoubles.StubElement;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class When_finding_single_elements extends BrowserInteractionTests
{
    private String locator;
    private Element expectedImmediateResult;
    private Element expectedDeferredResult;
    private Options options;

    @Before
    public void setUp() {
        locator = "Find me " + UUID.randomUUID().toString();

        options = new Options();
        options.Timeout = TimeSpan.fromMilliseconds(1234);

        expectedImmediateResult = new StubElement();
        expectedDeferredResult = new StubElement();

        spyRobustWrapper.alwaysReturnFromRobustly(expectedImmediateResult);    
    }
    @Test
    public void findButton_should_make_robust_call_to_underlying_driver()
    {
        driver.stubButton(locator, expectedDeferredResult, browserSession);
        driver.stubButton(locator, expectedDeferredResult, elementScope);

        verifyFoundRobustly(browserSession.findButton(locator, options).now(), 0);
        verifyFoundRobustly(elementScope.findButton(locator, options).now(), 1);
    }

    @Test
    public void findLink_should_make_robust_call_to_underlying_driver()
    {
        driver.stubLink(locator, expectedDeferredResult, browserSession);
        driver.stubLink(locator, expectedDeferredResult, elementScope);

        verifyFoundRobustly(browserSession.findLink(locator, options).now(), 0);
        verifyFoundRobustly(elementScope.findLink(locator, options).now(), 1);
    }

    @Test
    public void findField_should_make_robust_call_to_underlying_driver()
    {
        driver.stubField(locator, expectedDeferredResult, browserSession);
        driver.stubField(locator, expectedDeferredResult, elementScope);

        verifyFoundRobustly(browserSession.findField(locator, options).now(), 0);
        verifyFoundRobustly(elementScope.findField(locator, options).now(), 1);
    }

    @Test
    public void findCss_should_make_robust_call_to_underlying_driver()
    {
        driver.stubCss(locator, expectedDeferredResult, browserSession);
        driver.stubCss(locator, expectedDeferredResult, elementScope);

        verifyFoundRobustly(browserSession.findCss(locator, options).now(), 0);
        verifyFoundRobustly(elementScope.findCss(locator, options).now(), 1);
    }

    @Test
    public void findId_should_make_robust_call_to_underlying_driver() 
    {
        driver.stubId(locator, expectedDeferredResult, browserSession);
        driver.stubId(locator, expectedDeferredResult, elementScope);

        verifyFoundRobustly(browserSession.findId(locator, options).now(), 0);
        verifyFoundRobustly(elementScope.findId(locator, options).now(), 1);
    }

    @Test
    public void findSection_should_make_robust_call_to_underlying_driver() 
    {
        driver.stubSection(locator, expectedDeferredResult, browserSession);
        driver.stubSection(locator, expectedDeferredResult, elementScope);

        verifyFoundRobustly(browserSession.findSection(locator, options).now(), 0);
        verifyFoundRobustly(elementScope.findSection(locator, options).now(), 1);
    }

    @Test
    public void findFieldset_should_make_robust_call_to_underlying_driver() 
    {
        driver.stubFieldset(locator, expectedDeferredResult, browserSession);
        driver.stubFieldset(locator, expectedDeferredResult, elementScope);

        verifyFoundRobustly(browserSession.findFieldset(locator, options).now(), 0);
        verifyFoundRobustly(elementScope.findFieldset(locator, options).now(), 1);
    }

    @Test
    public void findIFrame_should_make_robust_call_to_underlying_driver()
    {
        driver.stubIFrame(locator, expectedDeferredResult, browserSession);
        driver.stubIFrame(locator, expectedDeferredResult, elementScope);

        verifyFoundRobustly(browserSession.findIFrame(locator, options).now(), 0);
        verifyFoundRobustly(elementScope.findIFrame(locator, options).now(), 1);
    }

    @Test
    public void findWindow_should_make_robust_call_to_underlying_driver()
    {
        driver.stubWindow(locator, expectedDeferredResult, browserSession);
        driver.stubWindow(locator, expectedDeferredResult, elementScope);

        verifyFoundRobustly(browserSession.findWindow(locator, options).now(), 0);
    }

    @Test
    public void findXPath_should_make_robust_call_to_underlying_driver() 
    {
        driver.stubXPath(locator, expectedDeferredResult, browserSession);
        driver.stubXPath(locator, expectedDeferredResult, elementScope);

        verifyFoundRobustly(browserSession.findXPath(locator, options).now(), 0);
        verifyFoundRobustly(elementScope.findXPath(locator, options).now(), 1);
    }

    private void verifyFoundRobustly(Element scopedResult, int driverCallIndex)
    {
        assertThat("Result was not found robustly",scopedResult, is(not(sameInstance(expectedDeferredResult))));
        assertThat(scopedResult, is(sameInstance(expectedImmediateResult)));

        Element elementScopeResult = runQueryAndCheckTiming(options.Timeout, driverCallIndex);

        assertThat(elementScopeResult, is(sameInstance(expectedDeferredResult)));
    }
}