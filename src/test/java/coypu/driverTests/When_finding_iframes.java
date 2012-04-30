package coypu.driverTests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_iframes extends DriverSpecs
{
    @Test
    public void finds_by_header_text()
    {
        assertThat(driver().findFrame("I am iframe one", root()).getId(), is(equalTo("iframe1")));
        assertThat(driver().findFrame("I am iframe two", root()).getId(), is(equalTo("iframe2")));
    }

    @Test
    public void finds_by_id()
    {
        assertThat(driver().findFrame("iframe1", root()).getId(), is(equalTo("iframe1")));
        assertThat(driver().findFrame("iframe2", root()).getId(), is(equalTo("iframe2")));
    }

    @Test
    public void finds_by_title()
    {
        assertThat(driver().findFrame("iframe one title", root()).getId(), is(equalTo("iframe1")));
        assertThat(driver().findFrame("iframe two title", root()).getId(), is(equalTo("iframe2")));
    }
}
