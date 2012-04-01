package Coypu.Tests.When_interacting_with_the_browser;

import org.junit.Test;

import java.util.regex.Pattern;

public class When_inspecting_the_page extends When_inspecting
{
    @Test
    public void hasContent_queries_robustly_Positive_example()
    {
        driver.stubHasContent(locator, true, browserSession);
        spyRobustWrapper.stubQueryResult(true, !true);

        boolean actualImmediateResult = (boolean) browserSession.hasContent(locator, options);

        assertFoundRobustly(true, individualTimeout, actualImmediateResult);
    }

    @Test
    public void hasContent_queries_robustly_Negative_example()
    {
        driver.stubHasContent(locator, false, browserSession);
        spyRobustWrapper.stubQueryResult(true, !false);

        boolean actualImmediateResult = (boolean) browserSession.hasContent(locator, options);

        assertFoundRobustly(false, individualTimeout, actualImmediateResult);
    }

    @Test
    public void hasContentMatch_queries_robustly_Positive_example()
    {
        Pattern pattern = Pattern.compile("some r[eE]gex^");
        driver.stubHasContentMatch(pattern, true, browserSession);
        spyRobustWrapper.stubQueryResult(true, !true);

        boolean actualImmediateResult = browserSession.hasContentMatch(pattern, options);

        assertFoundRobustly(true, individualTimeout, actualImmediateResult);
    }

    @Test
    public void hasContentMatch_queries_robustly_Negative_example()
    {
        Pattern pattern = Pattern.compile("some r[eE]gex^");
        driver.stubHasContentMatch(pattern, false, browserSession);
        spyRobustWrapper.stubQueryResult(true, !false);

        boolean actualImmediateResult = (boolean) browserSession.hasContentMatch(pattern, options);

        assertFoundRobustly(false, individualTimeout, actualImmediateResult);
    }

    @Test
    public void hasNoContent_queries_robustly_Positive_example()
    {
        driver.stubHasContent(locator, true, browserSession);
        spyRobustWrapper.stubQueryResult(true, !true);

        boolean actualImmediateResult = (boolean) browserSession.hasNoContent(locator, configuration);

        assertFoundRobustlyReversed(true, actualImmediateResult);
    }

    @Test
    public void hasNoContent_queries_robustly_Negative_example()
    {
        driver.stubHasContent(locator, false, browserSession);
        spyRobustWrapper.stubQueryResult(true, !false);

        boolean actualImmediateResult = (boolean) browserSession.hasNoContent(locator, configuration);

        assertFoundRobustlyReversed(false, actualImmediateResult);
    }

    @Test
    public void hasNoContentMatch_queries_robustly_Positive_example()
    {
        Pattern pattern = Pattern.compile("some r[eE]gex^");
        driver.stubHasContentMatch(pattern, true, browserSession);
        spyRobustWrapper.stubQueryResult(true, !true);

        boolean actualImmediateResult = browserSession.hasNoContentMatch(pattern, configuration);

        assertFoundRobustlyReversed(true, actualImmediateResult);
    }

    @Test
    public void hasNoContentMatch_queries_robustly_Negative_example()
    {
        Pattern pattern = Pattern.compile("some r[eE]gex^");
        driver.stubHasContentMatch(pattern, false, browserSession);
        spyRobustWrapper.stubQueryResult(true, !false);

        boolean actualImmediateResult = browserSession.hasNoContentMatch(pattern, configuration);

        assertFoundRobustlyReversed(false, actualImmediateResult);
    }

    @Test
    public void hasCss_queries_robustly_Positive_example()
    {
        driver.stubHasCss(locator, true, browserSession);
        spyRobustWrapper.stubQueryResult(true, !true);

        boolean actualImmediateResult = browserSession.hasCss(locator, options);

        assertFoundRobustly(true, individualTimeout, actualImmediateResult);
    }

    @Test
    public void hasCss_queries_robustly_Negative_example()
    {
        driver.stubHasCss(locator, false, browserSession);
        spyRobustWrapper.stubQueryResult(true, !false);

        boolean actualImmediateResult = browserSession.hasCss(locator, options);

        assertFoundRobustly(false, individualTimeout, actualImmediateResult);
    }

    @Test
    public void hasNoCss_queries_robustly_Positive_example()
    {
        driver.stubHasCss(locator, true, browserSession);
        spyRobustWrapper.stubQueryResult(true, !true);

        boolean actualImmediateResult = browserSession.hasNoCss(locator, configuration);

        assertFoundRobustlyReversed(true, actualImmediateResult);
    }

    @Test
    public void hasNoCss_queries_robustly_Negative_example()
    {
        driver.stubHasCss(locator, false, browserSession);
        spyRobustWrapper.stubQueryResult(true, !false);

        boolean actualImmediateResult = browserSession.hasNoCss(locator, configuration);

        assertFoundRobustlyReversed(false, actualImmediateResult);
    }

    @Test
    public void hasXPath_queries_robustly_Positive_example()
    {
        driver.stubHasXPath(locator, true, browserSession);
        spyRobustWrapper.stubQueryResult(true, !true);

        boolean actualImmediateResult =  browserSession.hasXPath(locator, options);

        assertFoundRobustly(true, individualTimeout, actualImmediateResult);
    }

    @Test
    public void hasXPath_queries_robustly_Negative_example()
    {
        driver.stubHasXPath(locator, false, browserSession);
        spyRobustWrapper.stubQueryResult(true, !false);

        boolean actualImmediateResult = browserSession.hasXPath(locator, options);

        assertFoundRobustly(false, individualTimeout, actualImmediateResult);
    }

    @Test
    public void hasNoXPath_queries_robustly_Positive_example()
    {
        driver.stubHasXPath(locator, true, browserSession);
        spyRobustWrapper.stubQueryResult(true, !true);

        boolean actualImmediateResult = browserSession.hasNoXPath(locator, configuration);

        assertFoundRobustlyReversed(true, actualImmediateResult);
    }

    @Test
    public void hasNoXPath_queries_robustly_Negative_example()
    {
        driver.stubHasXPath(locator, false, browserSession);
        spyRobustWrapper.stubQueryResult(true, !false);

        boolean actualImmediateResult = browserSession.hasNoXPath(locator, configuration);

        assertFoundRobustlyReversed(false, actualImmediateResult);
    }

}