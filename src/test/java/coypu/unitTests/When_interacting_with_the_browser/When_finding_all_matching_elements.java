package coypu.unitTests.When_interacting_with_the_browser;

import coypu.ElementFound;
import coypu.ElementScope;
import coypu.unitTests.TestDoubles.StubElement;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class When_finding_all_matching_elements extends BrowserInteractionTests
{
    private String locator;
    private List<ElementFound> stubbedDriverResult;

    @Before
    public void setUp() {
        locator = "Find me " + UUID.randomUUID().toString();

        stubbedDriverResult = new ArrayList<ElementFound>();
        stubbedDriverResult.add(new StubElement());
        stubbedDriverResult.add(new StubElement());
        stubbedDriverResult.add(new StubElement());
    }

    @Test
    public void findAllCss_should_wrap_elements_in_element_scope()
    {
        driver.stubAllCss(locator, stubbedDriverResult, browserSession);

        List<ElementScope> actualImmediateResult = browserSession.findAllCss(locator, sessionConfiguration);

        assertThat(actualImmediateResult.size(), is(equalTo(stubbedDriverResult.size())));
        assertThat(actualImmediateResult.get(0), is(instanceOf(ElementScope.class)));
        assertNoRobustQueryiesRan();
    }

    @Test
    public void findAllCss_should_wrap_each_element_in_element_scope() {
        driver.stubAllCss(locator, stubbedDriverResult, browserSession);

        List<ElementScope> actualImmediateResult = browserSession.findAllCss(locator, sessionConfiguration);

        assertThat(actualImmediateResult.get(0).now(), is(sameInstance(stubbedDriverResult.get(0))));
        assertThat(actualImmediateResult.get(1).now(), is(sameInstance(stubbedDriverResult.get(1))));
        assertThat(actualImmediateResult.get(2).now(), is(sameInstance(stubbedDriverResult.get(2))));
    }

    @Test
    public void findAllXPath_should_wrap_all_elements()
    {
        driver.stubAllXPath(locator, stubbedDriverResult, browserSession);

        List<ElementScope> actualImmediateResult = browserSession.findAllXPath(locator, sessionConfiguration);

        assertThat(actualImmediateResult.size(), is(equalTo(stubbedDriverResult.size())));
        assertThat(actualImmediateResult.get(0), is(instanceOf(ElementScope.class)));
        assertNoRobustQueryiesRan();
    }

    @Test
    public void stubAllXPath_should_wrap_each_element_in_element_scope() {
        driver.stubAllXPath(locator, stubbedDriverResult, browserSession);

        List<ElementScope> actualImmediateResult = browserSession.findAllXPath(locator, sessionConfiguration);

        assertThat(actualImmediateResult.get(0).now(), is(sameInstance(stubbedDriverResult.get(0))));
        assertThat(actualImmediateResult.get(1).now(), is(sameInstance(stubbedDriverResult.get(1))));
        assertThat(actualImmediateResult.get(2).now(), is(sameInstance(stubbedDriverResult.get(2))));
    }

    private void assertNoRobustQueryiesRan() {
        assertThat("Expected no robust queries run", spyRobustWrapper.noQueriesRan(), is(true));
    }

}