package coypu.unitTests.When_interacting_with_the_browser;

import coypu.Element;
import coypu.unitTests.TestDoubles.StubElement;
import org.junit.Test;

import static coypu.unitTests.When_interacting_with_the_browser.IsEmpty.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_finding_then_hovering extends BrowserInteractionTests
{
    @Test
    public void it_makes_robust_call_to_find_then_hover_element_on_underlying_driver()
    {
        Element element = new StubElement();
        driver.stubCss("something.to hover", element, browserSession);
        spyRobustWrapper.alwaysReturnFromRobustly(element);

        browserSession.findCss("something.to hover").hover();

        runQueryAndCheckTiming();

        assertThat("Finder call not deferred", driver.FindCssRequests, is(empty()));
        assertThat(driver.HoveredElements, hasItem(element));
    }
}
