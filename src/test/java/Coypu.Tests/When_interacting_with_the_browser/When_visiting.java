package Coypu.Tests.When_interacting_with_the_browser;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_visiting extends BrowserInteractionTests
{
    @Test
    public void it_uses_a_fully_qualified_url_from_the_url_builder()
    {
        stubUrlBuilder.setStubUrl("/some/resource", "http://blank.org");
        browserSession.visit("/some/resource");
        assertThat(driver.Visits.get(0), is(equalTo("http://blank.org")));
    }
}
