package Coypu.Tests.When_interacting_with_the_browser;

import Coypu.Element;
import Coypu.Tests.TestDoubles.StubElement;
import org.junit.Test;

import static Coypu.Tests.When_interacting_with_the_browser.IsEmpty.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_finding_then_clicking_any_element extends BrowserInteractionTests
{
    @Test
    public void it_makes_robust_call_to_find_then_clicks_element_on_underlying_driver()
    {
        Element element = new StubElement();
        driver.stubCss("something.to click", element, browserSession);
        spyRobustWrapper.alwaysReturnFromRobustly(element);

        elementScope = browserSession.findCss("something.to click");

        assertThat("Finder not called robustly", driver.FindCssRequests, is(empty()));

        elementScope.click();

        runQueryAndCheckTiming();

        assertThat("Scope finder was not deferred", driver.FindCssRequests, is(empty()));

        assertThat(driver.ClickedElements, hasItem(element));
    }
}
