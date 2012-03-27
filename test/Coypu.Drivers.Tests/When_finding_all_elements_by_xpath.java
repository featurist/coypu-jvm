package Coypu.Drivers.Tests;

import Coypu.ElementFound;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_all_elements_by_xpath extends DriverSpecs
{       
    @Test
    public void Returns_empty_if_no_matches()
    {
        String shouldNotFind = "//*[@id = 'inspectingContent']//p[@class='css-missing-test']";
        assertThat(Driver().FindAllXPath(shouldNotFind, Root()).size(), is(equalTo(0)));
    }

    @Test
    public void Returns_all_matches_by_xpath()
    {
        String shouldFind = "//*[@id='inspectingContent']//ul[@id='cssTest']/li";
        List<ElementFound> all = Driver().FindAllXPath(shouldFind, Root());
        assertThat(all.size(), is(equalTo(3)));
        assertThat(all.get(1).Text(), is(equalTo("two")));
        assertThat(all.get(2).Text(), is(equalTo("Me! Pick me!")));
    }
}