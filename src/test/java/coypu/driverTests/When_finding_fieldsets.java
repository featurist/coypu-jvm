package coypu.driverTests;

import coypu.MissingHtmlException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class When_finding_fieldsets extends DriverSpecs
{
    @Test
    public void finds_by_legend_text()
    {
        assertThat(driver().findFieldset("Scope 1", root()).getId(), is(equalTo("fieldsetScope1")));
        assertThat(driver().findFieldset("Scope 2", root()).getId(), is(equalTo("fieldsetScope2")));
    }

    @Test
    public void finds_by_id()
    {
        assertThat(driver().findFieldset("fieldsetScope1", root()).getNative(), is(equalTo(driver().findFieldset("Scope 1", root()).getNative())));
        assertThat(driver().findFieldset("fieldsetScope2", root()).getNative(), is(equalTo(driver().findFieldset("Scope 2", root()).getNative())));
    }

    @Test
    public void finds_only_fieldsets()
    {
        try {
            driver().findFieldset("scope1TextInputFieldId", root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}

        try {
            driver().findFieldset("sectionOne", root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}
    }
}
