package Coypu.Drivers.Tests;

import Coypu.ElementFound;
import Coypu.MissingHtmlException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class When_finding_links extends DriverSpecs
{
    @Test
    public void Finds_link_by_text()
    {
        assertThat(Driver().FindLink("first link", Root()).Id(), is(equalTo("firstLinkId")));
        assertThat(Driver().FindLink("second link", Root()).Id(), is(equalTo("secondLinkId")));
    }

    @Test
    public void Does_not_find_display_none()
    {
        try{
            Driver().FindLink("I am an invisible link by display", Root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}
    }


    @Test
    public void Does_not_find_visibility_hidden_links()
    {

        try{
            Driver().FindLink("I am an invisible link by visibility", Root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}
    }


    @Test
    public void Finds_a_link_with_both_types_of_quote_in_its_text()
    {
        ElementFound link = Driver().FindLink("I'm a link with \"both\" types of quote in my text", Root());
        assertThat(link.Id(), is(equalTo("linkWithBothQuotesId")));
    }
}
