package Coypu.Drivers.Tests;

import Coypu.Configuration;
import Coypu.DriverScope;
import Coypu.Finders.WindowFinder;
import Coypu.MissingHtmlException;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_windows extends DriverSpecs
{
    @Test
    public void Finds_by_name()
    {
        Driver().Click(Driver().FindLink("Open pop up window", Root()));
        assertThat(Driver().FindWindow("popUpWindowName", Root()).Text(), is(equalTo("I am a pop up window")));
        assertThat(Driver().HasContent("Open pop up window", Root()), is(true));
    }

    @Test
    public void Finds_by_title()
    {
        Driver().Click(Driver().FindLink("Open pop up window", Root()));
        assertThat(Driver().FindWindow("Pop Up Window", Root()).Text().contains("I am a pop up window"), is(true));
        assertThat(Driver().HasContent("Open pop up window", Root()), is(true));
    }

    @Test
    public void Finds_scoped_by_window()
    {
        Driver().Click(Driver().FindLink("Open pop up window", Root()));
        
        DriverScope popUp = new DriverScope(new Configuration(), new WindowFinder(Driver(), "Pop Up Window", Root()), Driver(), null, null, null);

        assertThat(Driver().HasContent("I am a pop up window", popUp), is(true));
        assertThat(Driver().HasContent("I am a pop up window", Root()), is(false));
    }

    @Test
    public void Errors_on_no_such_window()
    {
        Driver().Click(Driver().FindLink("Open pop up window", Root()));

        try {
            Driver().FindWindow("Not A Window", Root());
            fail("expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {}
    }
}
