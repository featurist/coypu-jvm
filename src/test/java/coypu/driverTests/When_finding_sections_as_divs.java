package coypu.driverTests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_sections_as_divs extends DriverSpecs
{
    @Test
    public void finds_by_h1_text()
    {
        assertThat(driver().findSection("Div Section One h1", root()).getId(), is(equalTo("divSectionOne")));
        assertThat(driver().findSection("Div Section Two h1", root()).getId(), is(equalTo("divSectionTwo")));
    }

    @Test
    public void finds_by_h2_text()
    {
        assertThat(driver().findSection("Div Section One h2", root()).getId(), is(equalTo("divSectionOne")));
        assertThat(driver().findSection("Div Section Two h2", root()).getId(), is(equalTo("divSectionTwo")));
    }

    @Test
    public void finds_by_h3_text()
    {
        assertThat(driver().findSection("Div Section One h3", root()).getId(), is(equalTo("divSectionOne")));
        assertThat(driver().findSection("Div Section Two h3", root()).getId(), is(equalTo("divSectionTwo")));
    }

    @Test
    public void finds_by_h6_text()
    {
        assertThat(driver().findSection("Div Section One h6", root()).getId(), is(equalTo("divSectionOne")));
        assertThat(driver().findSection("Div Section Two h6", root()).getId(), is(equalTo("divSectionTwo")));
    }


    @Test
    public void finds_by_h2_text_within_child_link()
    {
        assertThat(driver().findSection("Div Section One h2 with link", root()).getId(), is(equalTo("divSectionOneWithLink")));
        assertThat(driver().findSection("Div Section Two h2 with link", root()).getId(), is(equalTo("divSectionTwoWithLink")));
    }


    @Test
    public void finds_by_div_by_id()
    {
        assertThat(driver().findSection("divSectionOne", root()).getNative(), is(equalTo(driver().findSection("Div Section One h1", root()).getNative())));
        assertThat(driver().findSection("divSectionTwo", root()).getNative(), is(equalTo(driver().findSection("Div Section Two h1", root()).getNative())));
    }
}
