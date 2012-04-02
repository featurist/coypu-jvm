package coypu.driverTests;

import coypu.SessionConfiguration;
import coypu.DriverScope;
import coypu.ElementFound;
import coypu.Finders.WindowFinder;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_elements_are_stale extends DriverSpecs
{
    @Test
    public void stale_element_removed_from_DOM()
    {
        ElementFound elementWithinScope1 = driver().findFieldset("Scope 1", root());
        assertThat(elementWithinScope1.stale(), is(false));

        driver().click(driver().findButton("empty scope1", root()));

        assertThat(elementWithinScope1.stale(), is(true));
    }

    @Test
    public void stale_element_became_invisible()
    {
        ElementFound elementWithinScope1 = driver().findFieldset("Scope 1", root());
        assertThat(elementWithinScope1.stale(), is(false));

        driver().click(driver().findButton("hide scope1", root()));

        assertThat(elementWithinScope1.stale(), is(true));
    }

    @Test
    public void stale_iframe()
    {
        ElementFound frame = driver().findIFrame("iframe1", root());
        assertThat(frame.stale(), is(false));

        driver().click(driver().findButton("destroy frames",root()));

        assertThat(frame.stale(), is(true));
    }

    @Test
    public void stale_iframe_becomes_invisible()
    {
        ElementFound frame = driver().findIFrame("iframe1", root());
        assertThat(frame.stale(), is(false));

        driver().click(driver().findButton("hide frames", root()));

        assertThat(frame.stale(), is(true));
    }

    @Test
    public void stale_window_closed()
    {
        driver().click(driver().findLink("Open pop up window", root()));

        DriverScope popUpScope = new DriverScope(new SessionConfiguration(), new WindowFinder(driver(), "Pop Up Window", root()), driver(), null, null, null);

        ElementFound popUpWindow = popUpScope.now();
        assertThat(popUpWindow.stale(), is(false));

        driver().click(driver().findButton("close", popUpScope));

        assertThat(popUpWindow.stale(), is(true));
    }
}
