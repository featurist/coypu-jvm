package Coypu.Drivers.Tests;

import Coypu.Configuration;
import Coypu.DriverScope;
import Coypu.ElementFound;
import Coypu.Finders.WindowFinder;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_elements_are_stale extends DriverSpecs
{
    @Test
    public void Stale_element_removed_from_DOM()
    {
        ElementFound elementWithinScope1 = Driver().FindFieldset("Scope 1", Root());
        assertThat(elementWithinScope1.Stale(), is(false));

        Driver().Click(Driver().FindButton("empty scope1", Root()));

        assertThat(elementWithinScope1.Stale(), is(true));
    }

    @Test
    public void Stale_element_became_invisible()
    {
        ElementFound elementWithinScope1 = Driver().FindFieldset("Scope 1", Root());
        assertThat(elementWithinScope1.Stale(), is(false));

        Driver().Click(Driver().FindButton("hide scope1", Root()));

        assertThat(elementWithinScope1.Stale(), is(true));
    }

    @Test
    public void Stale_iframe()
    {
        ElementFound frame = Driver().FindIFrame("iframe1", Root());
        assertThat(frame.Stale(), is(false));

        Driver().Click(Driver().FindButton("destroy frames",Root()));

        assertThat(frame.Stale(), is(true));
    }

    @Test
    public void Stale_iframe_becomes_invisible()
    {
        ElementFound frame = Driver().FindIFrame("iframe1", Root());
        assertThat(frame.Stale(), is(false));

        Driver().Click(Driver().FindButton("hide frames", Root()));

        assertThat(frame.Stale(), is(true));
    }

    @Test
    public void Stale_window_closed()
    {
        Driver().Click(Driver().FindLink("Open pop up window", Root()));

        DriverScope popUpScope = new DriverScope(new Configuration(), new WindowFinder(Driver(), "Pop Up Window", Root()), Driver(), null, null, null);

        ElementFound popUpWindow = popUpScope.Now();
        assertThat(popUpWindow.Stale(), is(false));

        Driver().Click(Driver().FindButton("close", popUpScope));

        assertThat(popUpWindow.Stale(), is(true));
    }
}
