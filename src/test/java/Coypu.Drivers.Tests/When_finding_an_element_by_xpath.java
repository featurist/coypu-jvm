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
    public void finds_present_examples()
    {
        String shouldFind = "//*[@Id = 'inspectingContent']//p[@class='css-test']/span";
        assertThat(driver().findXPath(shouldFind, root()).getText(), is(equalTo("This")));
    
        shouldFind = "//ul[@Id='cssTest']/li[3]";
        assertThat(driver().findXPath(shouldFind, root()).getText(), is(equalTo("Me! Pick me!")));
    }

    @Test
    public void does_not_find_missing_examples()
    {
        String shouldNotFind = "//*[@Id = 'inspectingContent']//p[@class='css-missing-test']";
        try{
            driver().findXPath(shouldNotFind, root());
            fail("Expected not to find something at: " + shouldNotFind);
        }
        catch(MissingHtmlException ex){}
    }

    @Test
    public void only_finds_visible_elements()
    {
        String shouldNotFind = "//*[@Id = 'inspectingContent']//p[@class='css-test']/img";
        try{
            driver().findXPath(shouldNotFind, root());
            fail("Expected not to find something at: " + shouldNotFind);
        }
        catch(MissingHtmlException ex){}
    }
}

