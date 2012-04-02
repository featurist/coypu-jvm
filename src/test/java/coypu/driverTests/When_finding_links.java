package coypu.driverTests;

import coypu.ElementFound;
import coypu.MissingHtmlException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class When_finding_links extends DriverSpecs
{
    @Test
    public void finds_link_by_text()
    {
        assertThat(driver().findLink("first link", root()).getId(), is(equalTo("firstLinkId")));
        assertThat(driver().findLink("second link", root()).getId(), is(equalTo("secondLinkId")));
    }

    @Test
    public void does_not_find_display_none()
    {
        try{
            driver().findLink("I am an invisible link by display", root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}
    }


    @Test
    public void does_not_find_visibility_hidden_links()
    {

        try{
            driver().findLink("I am an invisible link by visibility", root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}
    }


    @Test
    public void finds_a_link_with_both_types_of_quote_in_its_text()
    {
        ElementFound link = driver().findLink("I'm a link with \"both\" types of quote in my text", root());
        assertThat(link.getId(), is(equalTo("linkWithBothQuotesId")));
    }
}
