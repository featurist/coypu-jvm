package Coypu.Drivers.Tests;

import Coypu.MissingHtmlException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class When_finding_an_element_by_css extends DriverSpecs
{
    @Test
    public void finds_present_examples()
    {
        String shouldFind = "#inspectingContent p.css-test span";
        assertThat(driver().findCss(shouldFind, root()).getText(), is(equalTo("This")));

        shouldFind = "ul#cssTest li:nth-child(3)";
        assertThat(driver().findCss(shouldFind, root()).getText(), is(equalTo("Me! Pick me!")));
    }

    @Test
    public void does_not_find_missing_examples()
    {
        String shouldNotFind = "#inspectingContent p.css-missing-test";

        try
        {
            driver().findCss(shouldNotFind, root());
            fail("Expected not to find something at: " + shouldNotFind);
        }
        catch(MissingHtmlException ex)
        {
        }
    }

    @Test
    public void only_finds_visible_elements()
    {
        String shouldNotFind = "#inspectingContent p.css-test img.invisible";
        try
        {
            driver().findCss(shouldNotFind,root());
            fail("Expected not to find something at: " + shouldNotFind);
        }
        catch(MissingHtmlException ex)
        {
        }
    }
}

