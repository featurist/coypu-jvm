package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_iframes extends DriverSpecs
{
    @Test
    public void Finds_by_header_text()
    {
        assertThat(Driver().FindIFrame("I am iframe one", Root()).Id(), is(equalTo("iframe1")));
        assertThat(Driver().FindIFrame("I am iframe two", Root()).Id(), is(equalTo("iframe2")));
    }

    @Test
    public void Finds_by_id()
    {
        assertThat(Driver().FindIFrame("iframe1", Root()).Id(), is(equalTo("iframe1")));
        assertThat(Driver().FindIFrame("iframe2", Root()).Id(), is(equalTo("iframe2")));
    }

    @Test
    public void Finds_by_title()
    {
        assertThat(Driver().FindIFrame("iframe one title", Root()).Id(), is(equalTo("iframe1")));
        assertThat(Driver().FindIFrame("iframe two title", Root()).Id(), is(equalTo("iframe2")));
    }
}
