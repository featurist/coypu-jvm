package Coypu.Drivers.Tests;

import Coypu.Configuration;
import Coypu.DriverScope;
import Coypu.Finders.CssFinder;
import Coypu.Finders.IFrameFinder;
import Coypu.MissingHtmlException;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_using_iframes_as_scope extends DriverSpecs
{
    @Test
    public void does_not_find_something_in_an_iframe()
    {
        try {
            driver().findButton("iframe1ButtonId", root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {}
    }

    @Test
    public void finds_elements_among_multiple_scopes()
    {
        DriverScope iframeOne = new DriverScope(new Configuration(), new IFrameFinder(driver(), "I am iframe one", root()), driver(),null,null,null);
        DriverScope iframeTwo = new DriverScope(new Configuration(), new IFrameFinder(driver(), "I am iframe two", root()), driver(),null,null,null);

        assertThat(driver().findButton("scoped button", iframeOne).getId(), is(equalTo("iframe1ButtonId")));
        assertThat(driver().findButton("scoped button", iframeTwo).getId(), is(equalTo("iframe2ButtonId")));
    }

    @Test
    public void finds_clears_scope_back_to_the_whole_window()
    {
        DriverScope iframeOne = new DriverScope(new Configuration(), new IFrameFinder(driver(), "I am iframe one", root()), driver(),null,null,null);
        assertThat(driver().findButton("scoped button", iframeOne).getId(), is(equalTo("iframe1ButtonId")));

        assertThat(driver().findButton("scoped button", root()).getId(), is(equalTo("scope1ButtonId")));
    }

    @Test
    public void can_fill_in_a_text_input_within_an_iframe()
    {
        DriverScope iframeOne = new DriverScope(new Configuration(), new IFrameFinder(driver(), "I am iframe one", root()), driver(), null, null, null);
        driver().set(driver().findField("text input in iframe", iframeOne), "filled in");

        assertThat(driver().findField("text input in iframe", iframeOne).getValue(), is(equalTo("filled in")));
    }

    @Test
    public void can_scope_around_an_iframe()
    {
        DriverScope body = new DriverScope(new Configuration(), new CssFinder(driver(), "body", root()), driver(), null, null, null);
        DriverScope iframeOne = new DriverScope(new Configuration(), new IFrameFinder(driver(), "I am iframe one", body), driver(), null, null, null);

        assertThat(driver().findButton("scoped button", iframeOne).getId(), is(equalTo("iframe1ButtonId")));

        assertThat(driver().findButton("scoped button", body).getId(), is(equalTo("scope1ButtonId")));
    }

    @Test
    public void can_scope_inside_an_iframe()
    {
        DriverScope iframeOne = new DriverScope(new Configuration(), new IFrameFinder(driver(), "I am iframe one", root()), driver(), null, null, null);
        DriverScope iframeForm = new DriverScope(new Configuration(), new CssFinder(driver(), "form", iframeOne), driver(), null, null, null);

        driver().findField("text input in iframe", iframeForm);
    }
}
