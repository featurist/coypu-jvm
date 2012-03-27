package Coypu.Drivers.Tests;

import Coypu.Element;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_choosing extends DriverSpecs
{
    @Test
    public void Chooses_radio_button_from_list()
    {
        Element radioButton1 = Driver().FindField("chooseRadio1", Root());
        assertThat(radioButton1.Selected(), is(false));

        // Choose 1
        Driver().Choose(radioButton1);

        Element radioButton2 = Driver().FindField("chooseRadio2", Root());
        assertThat(radioButton2.Selected(), is(false));

        // Choose 2
        Driver().Choose(radioButton2);

        // New choice is now selected
        radioButton2 = Driver().FindField("chooseRadio2", Root());
        assertThat(radioButton2.Selected(), is(true));

        // Originally selected is no longer selected
        radioButton1 = Driver().FindField("chooseRadio1", Root());
        assertThat(radioButton1.Selected(), is(false));
    }


    @Test
    public void Fires_onclick_event()
    {
        Element radio = Driver().FindField("chooseRadio2", Root());
        assertThat(radio.Value(), is(equalTo("Radio buttons - 2nd value")));

        Driver().Choose(radio);

        assertThat(Driver().FindField("chooseRadio2", Root()).Value(), is(equalTo("Radio buttons - 2nd value - clicked")));
    }
}
