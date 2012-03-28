package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_inspecting_xpath extends DriverSpecs
{
    @Test
    public void Does_not_find_missing_examples()
    {
        String shouldNotFind = "//*[@id = 'inspectingContent']//p[@class='css-missing-test']";
        assertThat("Expected not to find something at: " + shouldNotFind, Driver().HasXPath(shouldNotFind, Root()), is(false));
    }


    @Test
    public void Only_finds_visible_elements()

    {
        String shouldNotFind = "//*[@id = 'inspectingContent']//p[@class='css-test']/img";
        assertThat("Expected not to find something at: " + shouldNotFind, Driver().HasXPath(shouldNotFind, Root()), is(false));
    }


    @Test
    public void Finds_present_examples()

    {
        String shouldFind = "//*[@id = 'inspectingContent']//p[@class='css-test']/span";
        assertThat("Expected to find something at: " + shouldFind, Driver().HasXPath(shouldFind, Root()), is(true));

        shouldFind = "//ul[@id='cssTest']/li[3]";
        assertThat("Expected to find something at: " + shouldFind, Driver().HasXPath(shouldFind, Root()), is(true));
    }
}
