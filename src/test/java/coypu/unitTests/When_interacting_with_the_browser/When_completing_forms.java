package coypu.unitTests.When_interacting_with_the_browser;

import coypu.Element;
import coypu.unitTests.TestDoubles.StubElement;
import org.hamcrest.Matchers;
import org.junit.Test;

import static coypu.unitTests.When_interacting_with_the_browser.HasEnumerationMember.hasEnumerationMember;
import static coypu.unitTests.When_interacting_with_the_browser.IsEmpty.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class When_completing_forms extends BrowserInteractionTests
{


    @Test
    public void when_filling_in_a_text_field_It_finds_field_and_sets_value_robustly()
    {
        Element element = new StubElement();
        driver.stubField("Some field locator", element, browserSession);

        browserSession.fillIn("Some field locator").with("some value for the field");

        assertThat(driver.SetFields.keys(), not(hasEnumerationMember(element)));

        runQueryAndCheckTiming();

        assertThat(driver.SetFields.keys(), hasEnumerationMember(element));
        assertThat(driver.SetFields.get(element), is(equalTo("some value for the field")));
    }

    @Test
    public void when_filling_in_a_field_It_clicks_to_ensure_focus()
    {
        Element element = new StubElement();
        driver.stubField("Some field locator", element, browserSession);

        browserSession.fillIn("Some field locator").with("some value for the field");

        assertThat(driver.ClickedElements, is(empty()));

        runQueryAndCheckTiming();

        assertThat(driver.ClickedElements, Matchers.hasItem(element));
    }

    @Test
    public void when_filling_in_file_field_It_doesnt_click() {
        StubElement element = new StubElement();
        element.stubAttribute("type", "file");
        driver.stubField("Some field locator", element, browserSession);

        browserSession.fillIn("Some field locator").with("some value for the field");

        runQueryAndCheckTiming();

        assertThat(driver.ClickedElements, not(hasItem((Element) element)));
    }

    @Test
    public void when_filling_in_an_field_already_found_It_sets_value_robustly()
    {
        Element element = new StubElement();

        browserSession.fillIn(element).with("some value for the field");

        assertThat(driver.SetFields.keys(), not(hasEnumerationMember(element)));

        runQueryAndCheckTiming();

        assertThat(driver.SetFields.keys(), hasEnumerationMember(element));
        assertThat(driver.SetFields.get(element), is(equalTo("some value for the field")));
    }

    @Test
    public void when_selecting_an_option_It_finds_field_and_selects_option_robustly()
    {
        Element element = new StubElement();
        driver.stubField("Some select field locator", element, browserSession);

        browserSession.select("some option to select").from("Some select field locator");

        assertThat(driver.SelectedOptions.keys(), not(hasEnumerationMember(element)));

        runQueryAndCheckTiming();

        assertThat(driver.SelectedOptions.keys(), hasEnumerationMember(element));
        assertThat(driver.SelectedOptions.get(element), is(equalTo("some option to select")));
    }

    @Test
    public void when_checking_a_checkbox_It_find_fields_and_checks_robustly()
    {
        Element element = new StubElement();
        driver.stubField("Some checkbox locator", element, browserSession);

        browserSession.check("Some checkbox locator");

        assertThat(driver.CheckedElements, not(hasItem(element)));

        runQueryAndCheckTiming();

        assertThat(driver.CheckedElements, hasItem(element));
    }

    @Test
    public void when_unchecking_a_checkbox_It_finds_field_and_unchecks_robustly()
    {
        Element element = new StubElement();
        driver.stubField("Some checkbox locator", element, browserSession);

        browserSession.uncheck("Some checkbox locator");

        assertThat(driver.UncheckedElements, not(hasItem(element)));

        runQueryAndCheckTiming();

        assertThat(driver.UncheckedElements, hasItem(element));
    }

    @Test
    public void when_choosing_a_radio_button_It_finds_field_and_chooses_robustly()
    {
        Element element = new StubElement();
        driver.stubField("Some radio locator", element, browserSession);

        browserSession.choose("Some radio locator");

        assertThat(driver.ChosenElements, not(hasItem(element)));

        runQueryAndCheckTiming();

        assertThat(driver.ChosenElements, hasItem(element));
    }

}