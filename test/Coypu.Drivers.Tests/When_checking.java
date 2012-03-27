package Coypu.Drivers.Tests;

import Coypu.Element;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_checking extends DriverSpecs
{
    @Test
    public void Checks_an_unchecked_checkbox()
    {
        Element checkbox = Driver().FindField("uncheckedBox", Root());
        assertThat(checkbox.Selected(), is(false));

        Driver().Check(checkbox);

        Element findAgain = Driver().FindField("uncheckedBox", Root());
        assertThat(findAgain.Selected(), is(true));
    }


    @Test
    public void Leaves_a_checked_checkbox_checked()
    {
        Element checkbox = Driver().FindField("checkedBox", Root());
        assertThat(checkbox.Selected(), is(true));

        Driver().Check(checkbox);

        Element findAgain = Driver().FindField("checkedBox", Root());
        assertThat(findAgain.Selected(), is(true));
    }


    @Test
    public void Unchecks_a_checked_checkbox()
    {
        Element checkbox = Driver().FindField("checkedBox", Root());
        assertThat(checkbox.Selected(), is(true));

        Driver().Uncheck(checkbox);

        Element findAgain = Driver().FindField("checkedBox", Root());
        assertThat(findAgain.Selected(), is(false));
    }


    @Test
    public void Leaves_an_unchecked_checkbox_unchecked()
    {
        Element checkbox = Driver().FindField("uncheckedBox", Root());
        assertThat(checkbox.Selected(), is(false));

        Driver().Uncheck(checkbox);

        Element findAgain = Driver().FindField("uncheckedBox", Root());
        assertThat(findAgain.Selected(), is(false));
    }


    @Test
    public void Fires_onclick_event_on_check()
    {
        Element checkbox = Driver().FindField("uncheckedBox", Root());
        assertThat(checkbox.Value(), is(equalTo("unchecked")));

        Driver().Check(checkbox);

        assertThat(Driver().FindField("uncheckedBox", Root()).Value(), is(equalTo("unchecked - clicked")));
    }


    @Test
    public void Fires_onclick_event_on_uncheck()
    {
        Element checkbox = Driver().FindField("checkedBox", Root());
        assertThat(checkbox.Value(), is(equalTo("checked")));

        Driver().Uncheck(checkbox);

        assertThat(Driver().FindField("checkedBox", Root()).Value(), is(equalTo("checked - clicked")));
    }
}
