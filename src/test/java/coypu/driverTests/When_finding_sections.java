package coypu.driverTests;

import coypu.MissingHtmlException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class When_finding_sections extends DriverSpecs
{
    @Test
    public void finds_by_h1_text()
    {
        assertThat(driver().findSection("Section One h1", root()).getId(), is(equalTo("sectionOne")));
        assertThat(driver().findSection("Section Two h1", root()).getId(), is(equalTo("sectionTwo")));
    }

    @Test
    public void finds_by_h2_text()
    {
        assertThat(driver().findSection("Section One h2", root()).getId(), is(equalTo("sectionOne")));
        assertThat(driver().findSection("Section Two h2", root()).getId(), is(equalTo("sectionTwo")));
    }

    @Test
    public void finds_by_h3_text()
    {
        assertThat(driver().findSection("Section One h3", root()).getId(), is(equalTo("sectionOne")));
        assertThat(driver().findSection("Section Two h3", root()).getId(), is(equalTo("sectionTwo")));
    }

    @Test
    public void finds_by_h6_text()
    {
        assertThat(driver().findSection("Section One h6", root()).getId(), is(equalTo("sectionOne")));
        assertThat(driver().findSection("Section Two h6", root()).getId(), is(equalTo("sectionTwo")));
    }

    @Test
    public void finds_section_by_id()
    {
        assertThat(driver().findSection("sectionOne", root()).getId(), is(equalTo("sectionOne")));
        assertThat(driver().findSection("sectionTwo", root()).getId(), is(equalTo("sectionTwo")));
    }

    @Test
    public void only_finds_div_and_section()
    {
        try{
            driver().findSection("scope1TextInputFieldId", root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}
        try{
            driver().findSection("fieldsetScope2", root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}
    }
}
