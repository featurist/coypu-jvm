package Coypu.Tests.When_interacting_with_the_browser;

import org.junit.Test;

import java.net.URISyntaxException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_inspecting_location extends BrowserInteractionTests
{
    @Test
    public void it_returns_the_driver_url() throws URISyntaxException {
        String driverLocation = "https://blank.org:8080/actual_location";
        driver.stubLocation(driverLocation);
        assertThat(browserSession.location(), is(equalTo((driverLocation))));
    }
}