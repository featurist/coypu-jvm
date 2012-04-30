package coypu.unitTests.When_interacting_with_the_browser;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_inspecting_title extends BrowserInteractionTests {
    @Test
    public void it_returns_the_driver_page_title()
    {
        String pageTitle = "Coypu interaction tests page";
        driver.stubTitle(pageTitle, browserSession);
        assertThat(browserSession.getTitle(), is(equalTo(pageTitle)));
    }
}
