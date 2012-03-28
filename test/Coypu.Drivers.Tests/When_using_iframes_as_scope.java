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
    public void Does_not_find_something_in_an_iframe()
    {
        try {
            Driver().FindButton("iframe1ButtonId", Root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {}
    }

    @Test
    public void Finds_elements_among_multiple_scopes()
    {
        DriverScope iframeOne = new DriverScope(new Configuration(), new IFrameFinder(Driver(), "I am iframe one", Root()), Driver(),null,null,null);
        DriverScope iframeTwo = new DriverScope(new Configuration(), new IFrameFinder(Driver(), "I am iframe two", Root()), Driver(),null,null,null);

        assertThat(Driver().FindButton("scoped button", iframeOne).Id(), is(equalTo("iframe1ButtonId")));
        assertThat(Driver().FindButton("scoped button", iframeTwo).Id(), is(equalTo("iframe2ButtonId")));
    }

    @Test
    public void Finds_clears_scope_back_to_the_whole_window()
    {
        DriverScope iframeOne = new DriverScope(new Configuration(), new IFrameFinder(Driver(), "I am iframe one", Root()), Driver(),null,null,null);
        assertThat(Driver().FindButton("scoped button", iframeOne).Id(), is(equalTo("iframe1ButtonId")));

        assertThat(Driver().FindButton("scoped button", Root()).Id(), is(equalTo("scope1ButtonId")));
    }

    @Test
    public void Can_fill_in_a_text_input_within_an_iframe()
    {
        DriverScope iframeOne = new DriverScope(new Configuration(), new IFrameFinder(Driver(), "I am iframe one", Root()), Driver(), null, null, null);
        Driver().Set(Driver().FindField("text input in iframe", iframeOne), "filled in");

        assertThat(Driver().FindField("text input in iframe", iframeOne).Value(), is(equalTo("filled in")));
    }

    @Test
    public void Can_scope_around_an_iframe()
    {
        DriverScope body = new DriverScope(new Configuration(), new CssFinder(Driver(), "body", Root()), Driver(), null, null, null);
        DriverScope iframeOne = new DriverScope(new Configuration(), new IFrameFinder(Driver(), "I am iframe one", body), Driver(), null, null, null);

        assertThat(Driver().FindButton("scoped button", iframeOne).Id(), is(equalTo("iframe1ButtonId")));

        assertThat(Driver().FindButton("scoped button", body).Id(), is(equalTo("scope1ButtonId")));
    }

    @Test
    public void Can_scope_inside_an_iframe()
    {
        DriverScope iframeOne = new DriverScope(new Configuration(), new IFrameFinder(Driver(), "I am iframe one", Root()), Driver(), null, null, null);
        DriverScope iframeForm = new DriverScope(new Configuration(), new CssFinder(Driver(), "form", iframeOne), Driver(), null, null, null);

        Driver().FindField("text input in iframe", iframeForm);
    }
}