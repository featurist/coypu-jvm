package coypu.driverTests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_inspecting_location extends DriverSpecs
{
    @Test
    public void gets_the_current_browser_location()

    {
        driver().visit("http://localhost:4567");
        assertThat(driver().location(), is(equalTo("http://localhost:4567/")));

        driver().visit("http://localhost:4567/auto_login");
        assertThat(driver().location(), is(equalTo("http://localhost:4567/auto_login")));
    }

    @Test
    public void not_just_when_set_by_visit()

    {
        driver().visit("http://localhost:4567/auto_login");
        driver().executeScript("document.location.href = 'http://localhost:4567/resource/js_redirect'", root());

        assertThat(driver().location(), is(equalTo("http://localhost:4567/resource/js_redirect")));
    }
}
