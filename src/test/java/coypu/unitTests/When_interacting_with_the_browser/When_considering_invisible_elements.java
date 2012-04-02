package coypu.unitTests.When_interacting_with_the_browser;

import coypu.Options;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_considering_invisible_elements extends BrowserInteractionTests
{
    @Test
    public void it_sets_the_driver_scope_to_consider_invisible_elements()
    {
        assertThat(browserSession.considerInvisibleElements(), is(false));

        Options options = new Options();
        options.ConsiderInvisibleElements = true;
        browserSession.clickButton("invisible", options);

        assertThat(browserSession.considerInvisibleElements(), is(true));

        browserSession.clickButton("visible");

        assertThat(browserSession.considerInvisibleElements(), is(false));
    }
}