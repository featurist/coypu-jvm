package Coypu.Drivers.Tests;

import Coypu.Configuration;
import Coypu.DriverScope;
import Coypu.Finders.WindowFinder;
import Coypu.MissingHtmlException;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_finding_windows extends DriverSpecs
{
    @Test
    public void finds_by_name()
    {
        driver().click(driver().findLink("Open pop up window", root()));
        assertThat(driver().findWindow("popUpWindowName", root()).getText().startsWith("I am a pop up window"),is(true));
        assertThat(driver().hasContent("Open pop up window", root()), is(true));
    }

    @Test
    public void finds_by_title()
    {
        driver().click(driver().findLink("Open pop up window", root()));
        assertThat(driver().findWindow("Pop Up Window", root()).getText().contains("I am a pop up window"), is(true));
        assertThat(driver().hasContent("Open pop up window", root()), is(true));
    }

    @Test
    public void finds_scoped_by_window()
    {
        driver().click(driver().findLink("Open pop up window", root()));
        
        DriverScope popUp = new DriverScope(new Configuration(), new WindowFinder(driver(), "Pop Up Window", root()), driver(), null, null, null);

        assertThat(driver().hasContent("I am a pop up window", popUp), is(true));
        assertThat(driver().hasContent("I am a pop up window", root()), is(false));
    }

    @Test
    public void errors_on_no_such_window()
    {
        driver().click(driver().findLink("Open pop up window", root()));

        try {
            driver().findWindow("Not A Window", root());
            fail("expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {}
    }
}
