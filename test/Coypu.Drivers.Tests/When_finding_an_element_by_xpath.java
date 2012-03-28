package Coypu.Drivers.Tests;

import Coypu.MissingHtmlException;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_an_element_by_xpath extends DriverSpecs
{
    @Test
    public void Finds_present_examples()
    {
        String shouldFind = "//*[@id = 'inspectingContent']//p[@class='css-test']/span";
        assertThat(Driver().FindXPath(shouldFind, Root()).Text(), is(equalTo("This")));
    
        shouldFind = "//ul[@id='cssTest']/li[3]";
        assertThat(Driver().FindXPath(shouldFind, Root()).Text(), is(equalTo("Me! Pick me!")));
    }

    @Test
    public void Does_not_find_missing_examples()
    {
        String shouldNotFind = "//*[@id = 'inspectingContent']//p[@class='css-missing-test']";
        try{
            Driver().FindXPath(shouldNotFind, Root());
            fail("Expected not to find something at: " + shouldNotFind);
        }
        catch(MissingHtmlException ex){}
    }

    @Test
    public void Only_finds_visible_elements()
    {
        String shouldNotFind = "//*[@id = 'inspectingContent']//p[@class='css-test']/img";
        try{
            Driver().FindXPath(shouldNotFind, Root());
            fail("Expected not to find something at: " + shouldNotFind);
        }
        catch(MissingHtmlException ex){}
    }
}

