package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_inspecting_location extends DriverSpecs
{
    @Test
    public void Gets_the_current_browser_location()

    {
        Driver().Visit("http://localhost:4567");
        assertThat(Driver().Location(), is(equalTo("http://localhost:4567/")));

        Driver().Visit("http://localhost:4567/auto_login");
        assertThat(Driver().Location(), is(equalTo("http://localhost:4567/auto_login")));
    }

    @Test
    public void Not_just_when_set_by_visit()

    {
        Driver().Visit("http://localhost:4567/auto_login");
        Driver().ExecuteScript("document.location.href = 'http://localhost:4567/resource/js_redirect'", Root());

        assertThat(Driver().Location(), is(equalTo("http://localhost:4567/resource/js_redirect")));
    }
}
