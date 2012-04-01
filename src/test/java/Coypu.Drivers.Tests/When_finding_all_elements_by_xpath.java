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
    public void returns_empty_if_no_matches()
    {
        String shouldNotFind = "//*[@Id = 'inspectingContent']//p[@class='css-missing-test']";
        assertThat(driver().findAllXPath(shouldNotFind, root()).size(), is(equalTo(0)));
    }

    @Test
    public void returns_all_matches_by_xpath()
    {
        String shouldFind = "//*[@Id='inspectingContent']//ul[@Id='cssTest']/li";
        List<ElementFound> all = driver().findAllXPath(shouldFind, root());
        assertThat(all.size(), is(equalTo(3)));
        assertThat(all.get(1).getText(), is(equalTo("two")));
        assertThat(all.get(2).getText(), is(equalTo("Me! Pick me!")));
    }
}
