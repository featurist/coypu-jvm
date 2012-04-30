package coypu.driverTests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_inspecting_page_title extends DriverSpecs
{
    @Test
    public void gets_the_current_browser_location()
    {
        assertThat(driver().getTitle(root()), is(equalTo(("Coypu interaction tests page"))));
    }
}
