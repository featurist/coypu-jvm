package coypu.driverTests;

import coypu.Element;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_choosing extends DriverSpecs
{
    @Test
    public void chooses_radio_button_from_list()
    {
        Element radioButton1 = driver().findField("chooseRadio1", root());
        assertThat(radioButton1.getSelected(), is(false));

        // Choose 1
        driver().choose(radioButton1);

        Element radioButton2 = driver().findField("chooseRadio2", root());
        assertThat(radioButton2.getSelected(), is(false));

        // Choose 2
        driver().choose(radioButton2);

        // New choice is now selected
        radioButton2 = driver().findField("chooseRadio2", root());
        assertThat(radioButton2.getSelected(), is(true));

        // Originally selected is no longer selected
        radioButton1 = driver().findField("chooseRadio1", root());
        assertThat(radioButton1.getSelected(), is(false));
    }


    @Test
    public void fires_onclick_event()
    {
        Element radio = driver().findField("chooseRadio2", root());
        assertThat(radio.getValue(), is(equalTo("Radio buttons - 2nd value")));

        driver().choose(radio);

        assertThat(driver().findField("chooseRadio2", root()).getValue(), is(equalTo("Radio buttons - 2nd value - clicked")));
    }
}
