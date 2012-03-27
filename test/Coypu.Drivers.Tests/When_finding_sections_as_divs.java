package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_sections_as_divs extends DriverSpecs
{
    @Test
    public void Finds_by_h1_text()
    {
        assertThat(Driver().FindSection("Div Section One h1", Root()).Id(), is(equalTo("divSectionOne")));
        assertThat(Driver().FindSection("Div Section Two h1", Root()).Id(), is(equalTo("divSectionTwo")));
    }

    @Test
    public void Finds_by_h2_text()
    {
        assertThat(Driver().FindSection("Div Section One h2", Root()).Id(), is(equalTo("divSectionOne")));
        assertThat(Driver().FindSection("Div Section Two h2", Root()).Id(), is(equalTo("divSectionTwo")));
    }

    @Test
    public void Finds_by_h3_text()
    {
        assertThat(Driver().FindSection("Div Section One h3", Root()).Id(), is(equalTo("divSectionOne")));
        assertThat(Driver().FindSection("Div Section Two h3", Root()).Id(), is(equalTo("divSectionTwo")));
    }

    @Test
    public void Finds_by_h6_text()
    {
        assertThat(Driver().FindSection("Div Section One h6", Root()).Id(), is(equalTo("divSectionOne")));
        assertThat(Driver().FindSection("Div Section Two h6", Root()).Id(), is(equalTo("divSectionTwo")));
    }


    @Test
    public void Finds_by_h2_text_within_child_link()
    {
        assertThat(Driver().FindSection("Div Section One h2 with link", Root()).Id(), is(equalTo("divSectionOneWithLink")));
        assertThat(Driver().FindSection("Div Section Two h2 with link", Root()).Id(), is(equalTo("divSectionTwoWithLink")));
    }


    @Test
    public void Finds_by_div_by_id()
    {
        assertThat(Driver().FindSection("divSectionOne", Root()).Native(), is(equalTo(Driver().FindSection("Div Section One h1", Root()).Native())));
        assertThat(Driver().FindSection("divSectionTwo", Root()).Native(), is(equalTo(Driver().FindSection("Div Section Two h1", Root()).Native())));
    }
}
