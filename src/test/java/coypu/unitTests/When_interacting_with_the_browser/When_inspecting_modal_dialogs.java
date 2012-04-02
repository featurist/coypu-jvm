package coypu.unitTests.When_interacting_with_the_browser;

import org.junit.Test;

public class When_inspecting_modal_dialogs extends When_inspecting
{
    @Test
    public void hasDialog_should_wait_for_robustly_Positive_example()
    {
        driver.stubDialog(locator, true, browserSession);
        spyRobustWrapper.stubQueryResult(true, !true);

        boolean actualImmediateResult = browserSession.hasDialog(locator, options);

        assertFoundRobustly(true, individualTimeout, actualImmediateResult);
    }

    @Test
    public void hasDialog_should_wait_for_robustly_Negative_example()
    {
        driver.stubDialog(locator, false, browserSession);
        spyRobustWrapper.stubQueryResult(true, !false);

        boolean actualImmediateResult = browserSession.hasDialog(locator, options);

        assertFoundRobustly(false, individualTimeout, actualImmediateResult);
    }

    @Test
    public void hasNoDialog_should_wait_for_robustly_Positive_example()
    {
        driver.stubDialog(locator, true, browserSession);
        spyRobustWrapper.stubQueryResult(true, !true);

        boolean actualImmediateResult = browserSession.hasNoDialog(locator, sessionConfiguration);

        assertFoundRobustlyReversed(true, actualImmediateResult);
    }

    @Test
    public void hasNoDialog_should_wait_for_robustly_Negative_example()
    {
        driver.stubDialog(locator, false, browserSession);
        spyRobustWrapper.stubQueryResult(true, !false);

        boolean actualImmediateResult = browserSession.hasNoDialog(locator, sessionConfiguration);

        assertFoundRobustlyReversed(false, actualImmediateResult);
    }
}