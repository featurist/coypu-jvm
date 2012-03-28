package Coypu.Drivers.Tests;

import Coypu.MissingHtmlException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class When_finding_fieldsets extends DriverSpecs
{
    @Test
    public void Finds_by_legend_text()
    {
        assertThat(Driver().FindFieldset("Scope 1", Root()).Id(), is(equalTo("fieldsetScope1")));
        assertThat(Driver().FindFieldset("Scope 2", Root()).Id(), is(equalTo("fieldsetScope2")));
    }

    @Test
    public void Finds_by_id()
    {
        assertThat(Driver().FindFieldset("fieldsetScope1", Root()).Native(), is(equalTo(Driver().FindFieldset("Scope 1", Root()).Native())));
        assertThat(Driver().FindFieldset("fieldsetScope2", Root()).Native(), is(equalTo(Driver().FindFieldset("Scope 2", Root()).Native())));
    }

    @Test
    public void Finds_only_fieldsets()
    {
        try {
            Driver().FindFieldset("scope1TextInputFieldId", Root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}

        try {
            Driver().FindFieldset("sectionOne", Root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}
    }
}
