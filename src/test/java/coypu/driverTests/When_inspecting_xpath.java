package coypu.driverTests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_inspecting_xpath extends DriverSpecs
{
    @Test
    public void does_not_find_missing_examples()
    {
        String shouldNotFind = "//*[@Id = 'inspectingContent']//p[@class='css-missing-test']";
        assertThat("Expected not to find something at: " + shouldNotFind, driver().hasXPath(shouldNotFind, root()), is(false));
    }


    @Test
    public void only_finds_visible_elements()

    {
        String shouldNotFind = "//*[@Id = 'inspectingContent']//p[@class='css-test']/img";
        assertThat("Expected not to find something at: " + shouldNotFind, driver().hasXPath(shouldNotFind, root()), is(false));
    }


    @Test
    public void finds_present_examples()

    {
        String shouldFind = "//*[@Id = 'inspectingContent']//p[@class='css-test']/span";
        assertThat("Expected to find something at: " + shouldFind, driver().hasXPath(shouldFind, root()), is(true));

        shouldFind = "//ul[@Id='cssTest']/li[3]";
        assertThat("Expected to find something at: " + shouldFind, driver().hasXPath(shouldFind, root()), is(true));
    }
}
