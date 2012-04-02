package coypu.unitTests.When_interacting_with_the_browser;

import coypu.Element;
import coypu.unitTests.TestDoubles.StubElement;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class When_inspecting_elements extends BrowserInteractionTests
{
    @Test
    public void it_finds_element_robustly_and_returns_id()
    {
        StubElement stubElement = new StubElement();
        stubElement.setId("actual-Id");
        driver.stubId("some-element", stubElement, browserSession);
        
        spyRobustWrapper.alwaysReturnFromRobustly(stubElement);

        assertThat(browserSession.findId("some-element").getId(), is(equalTo("actual-Id")));

        Object queryResult = runQueryAndCheckTiming();

        assertThat((Element)queryResult, is(sameInstance((Element)stubElement)));
    }

    @Test
    public void it_finds_element_robustly_and_returns_text()
    {
        StubElement stubElement = new StubElement();
        stubElement.setText("actual-text");
        driver.stubId("some-element", stubElement, browserSession);

        spyRobustWrapper.alwaysReturnFromRobustly(stubElement);

        assertThat(browserSession.findId("some-element").getText(), is(equalTo("actual-text")));

        Object queryResult = runQueryAndCheckTiming();

        assertThat((Element)queryResult, is(sameInstance((Element)stubElement)));
    }

    @Test
    public void it_finds_element_robustly_and_returns_value()
    {
        StubElement stubElement = new StubElement();
        stubElement.setValue("actual-value");
        driver.stubId("some-element", stubElement, browserSession);

        spyRobustWrapper.alwaysReturnFromRobustly(stubElement);

        assertThat(browserSession.findId("some-element").getValue(), is(equalTo("actual-value")));

        Object queryResult = runQueryAndCheckTiming();

        assertThat((Element)queryResult, is(sameInstance((Element)stubElement)));
    }

    @Test
    public void it_finds_element_robustly_and_returns_attributes()
    {
        StubElement stubElement = new StubElement();
        stubElement.stubAttribute("href","http://some.href");
        driver.stubId("some-element", stubElement, browserSession);

        spyRobustWrapper.alwaysReturnFromRobustly(stubElement);

        assertThat(browserSession.findId("some-element").getAttribute("href"), is(equalTo("http://some.href")));

        Object queryResult = runQueryAndCheckTiming();

        assertThat((Element)queryResult, is(sameInstance((Element)stubElement)));
    }

    @Test
    public void it_finds_element_robustly_and_returns_name()
    {
        StubElement stubElement = new StubElement();
        stubElement.setName("actual-name");
        driver.stubId("some-element", stubElement, browserSession);

        spyRobustWrapper.alwaysReturnFromRobustly(stubElement);

        assertThat(browserSession.findId("some-element").getName(), is(equalTo("actual-name")));

        Object queryResult = runQueryAndCheckTiming();

        assertThat((Element)queryResult, is(sameInstance((Element)stubElement)));
    }

    @Test
    public void it_finds_element_robustly_and_returns_selected_option()
    {
        StubElement stubElement = new StubElement();
        stubElement.setSelectedOption("actual-selected-option");
        driver.stubId("some-element", stubElement, browserSession);

        spyRobustWrapper.alwaysReturnFromRobustly(stubElement);

        assertThat(browserSession.findId("some-element").getSelectedOption(), is(equalTo("actual-selected-option")));

        Object queryResult = runQueryAndCheckTiming();

        assertThat((Element)queryResult, is(sameInstance((Element)stubElement)));
    }

    @Test
    public void it_finds_element_robustly_and_returns_selected_positive()
    {
        StubElement stubElement = new StubElement();
        stubElement.setSelected(true);
        driver.stubId("some-element", stubElement, browserSession);

        spyRobustWrapper.alwaysReturnFromRobustly(stubElement);

        assertThat(browserSession.findId("some-element").getSelected(), is(equalTo(true)));

        Object queryResult = runQueryAndCheckTiming();

        assertThat((Element)queryResult, is(sameInstance((Element)stubElement)));
    }

    @Test
    public void it_finds_element_robustly_and_returns_selected_negative()
    {
        StubElement stubElement = new StubElement();
        stubElement.setSelected(false);
        driver.stubId("some-element", stubElement, browserSession);

        spyRobustWrapper.alwaysReturnFromRobustly(stubElement);

        assertThat(browserSession.findId("some-element").getSelected(), is(equalTo(false)));

        Object queryResult = runQueryAndCheckTiming();

        assertThat((Element)queryResult, is(sameInstance((Element)stubElement)));
    }

    @Test
    public void it_finds_element_robustly_and_returns_native()
    {
        Object nativeElement = new Object();
        StubElement stubElement = new StubElement();
        stubElement.setNativeElement(nativeElement);
        driver.stubId("some-element", stubElement, browserSession);

        spyRobustWrapper.alwaysReturnFromRobustly(stubElement);

        assertThat(browserSession.findId("some-element").getNative(), is(sameInstance(nativeElement)));

        Object queryResult = runQueryAndCheckTiming();

        assertThat((Element)queryResult, is(sameInstance((Element)stubElement)));
    }
}
