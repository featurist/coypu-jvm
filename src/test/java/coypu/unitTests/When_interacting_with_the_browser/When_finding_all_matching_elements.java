package coypu.unitTests.When_interacting_with_the_browser;

import coypu.ElementFound;
import coypu.unitTests.TestDoubles.StubElement;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class When_finding_all_matching_elements extends BrowserInteractionTests
{
    private String locator;
    private List<ElementFound> expectedImmediateResult;

    @Before
    public void setUp() {
        locator = "Find me " + UUID.randomUUID().toString();

        expectedImmediateResult = new ArrayList<ElementFound>();
        expectedImmediateResult.add(new StubElement());
    }

    @Test
    public void findAllCss_should_make_direct_call_to_underlying_driver()
    {
        driver.stubAllCss(locator, expectedImmediateResult, browserSession);

        List<ElementFound> actualImmediateResult = browserSession.findAllCss(locator, sessionConfiguration);

        assertThat(actualImmediateResult, is(sameInstance(expectedImmediateResult)));
        assertNoRobustQueryiesRan();
    }
    @Test
    public void findAllXPath_should_make_direct_call_to_underlying_driver()
    {
        driver.stubAllXPath(locator, expectedImmediateResult, browserSession);

        List<ElementFound> actualImmediateResult = browserSession.findAllXPath(locator, sessionConfiguration);

        assertThat(actualImmediateResult, is(sameInstance(expectedImmediateResult)));
        assertNoRobustQueryiesRan();
    }

    private void assertNoRobustQueryiesRan() {
        assertThat("Expected no robust queries run", spyRobustWrapper.noQueriesRan(), is(true));
    }

}