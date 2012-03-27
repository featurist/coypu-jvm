package Coypu.Drivers.Tests;

import Coypu.Configuration;
import Coypu.DriverScope;
import Coypu.Finders.DocumentElementFinder;
import Coypu.MissingHtmlException;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_forced_to_consider_invisible_elements extends DriverSpecs
{
    private static DriverScope RootConsideringInvisibleElements()
    {
        Configuration configuration = new Configuration();
        configuration.ConsiderInvisibleElements = true;

        DriverScope rootConsideringInvisibleElements = new DriverScope(configuration, new DocumentElementFinder(Driver()), null, null, null, null);
        return rootConsideringInvisibleElements;
    }

    @Test
    public void Does_find_hidden_inputs()
    {
        assertThat(Driver().FindField("firstHiddenInputId", RootConsideringInvisibleElements()).Value(), is(equalTo("first hidden input")));

        try {
            Driver().FindField("firstHiddenInputId", Root());
            fail("expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {}
    }


    @Test
    public void Does_find_invisible_elements()
    {
        assertThat(Driver().FindButton("firstInvisibleInputId", RootConsideringInvisibleElements()).Name(), is(equalTo("firstInvisibleInputName")));

        try {
            Driver().FindButton("firstInvisibleInputId", Root());
            fail("expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {}

    }
}
