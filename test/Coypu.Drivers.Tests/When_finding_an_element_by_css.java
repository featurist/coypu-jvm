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
    public void Finds_present_examples()
    {
        String shouldFind = "#inspectingContent p.css-test span";
        assertThat(Driver().FindCss(shouldFind, Root()).Text(), is(equalTo("This")));

        shouldFind = "ul#cssTest li:nth-child(3)";
        assertThat(Driver().FindCss(shouldFind, Root()).Text(), is(equalTo("Me! Pick me!")));
    }

    @Test
    public void Does_not_find_missing_examples()
    {
        String shouldNotFind = "#inspectingContent p.css-missing-test";

        try
        {
            Driver().FindCss(shouldNotFind, Root());
            fail("Expected not to find something at: " + shouldNotFind);
        }
        catch(MissingHtmlException ex)
        {
        }
    }

    @Test
    public void Only_finds_visible_elements()
    {
        String shouldNotFind = "#inspectingContent p.css-test img.invisible";
        try
        {
            Driver().FindCss(shouldNotFind,Root());
            fail("Expected not to find something at: " + shouldNotFind);
        }
        catch(MissingHtmlException ex)
        {
        }
    }
}

