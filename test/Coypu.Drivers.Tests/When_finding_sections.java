package Coypu.Drivers.Tests;

import Coypu.MissingHtmlException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class When_finding_sections extends DriverSpecs
{
    @Test
    public void Finds_by_h1_text()
    {
        assertThat(Driver().FindSection("Section One h1", Root()).Id(), is(equalTo("sectionOne")));
        assertThat(Driver().FindSection("Section Two h1", Root()).Id(), is(equalTo("sectionTwo")));
    }

    @Test
    public void Finds_by_h2_text()
    {
        assertThat(Driver().FindSection("Section One h2", Root()).Id(), is(equalTo("sectionOne")));
        assertThat(Driver().FindSection("Section Two h2", Root()).Id(), is(equalTo("sectionTwo")));
    }

    @Test
    public void Finds_by_h3_text()
    {
        assertThat(Driver().FindSection("Section One h3", Root()).Id(), is(equalTo("sectionOne")));
        assertThat(Driver().FindSection("Section Two h3", Root()).Id(), is(equalTo("sectionTwo")));
    }

    @Test
    public void Finds_by_h6_text()
    {
        assertThat(Driver().FindSection("Section One h6", Root()).Id(), is(equalTo("sectionOne")));
        assertThat(Driver().FindSection("Section Two h6", Root()).Id(), is(equalTo("sectionTwo")));
    }

    @Test
    public void Finds_section_by_id()
    {
        assertThat(Driver().FindSection("sectionOne", Root()).Id(), is(equalTo("sectionOne")));
        assertThat(Driver().FindSection("sectionTwo", Root()).Id(), is(equalTo("sectionTwo")));
    }

    @Test
    public void Only_finds_div_and_section()
    {
        try{
            Driver().FindSection("scope1TextInputFieldId", Root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}
        try{
            Driver().FindSection("fieldsetScope2", Root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}
    }
}
