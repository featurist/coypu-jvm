package coypu.driverTests;

import coypu.Element;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_checking extends DriverSpecs
{
    @Test
    public void checks_an_unchecked_checkbox()
    {
        Element checkbox = driver().findField("uncheckedBox", root());
        assertThat(checkbox.getSelected(), is(false));

        driver().check(checkbox);

        Element findAgain = driver().findField("uncheckedBox", root());
        assertThat(findAgain.getSelected(), is(true));
    }


    @Test
    public void leaves_a_checked_checkbox_checked()
    {
        Element checkbox = driver().findField("checkedBox", root());
        assertThat(checkbox.getSelected(), is(true));

        driver().check(checkbox);

        Element findAgain = driver().findField("checkedBox", root());
        assertThat(findAgain.getSelected(), is(true));
    }


    @Test
    public void unchecks_a_checked_checkbox()
    {
        Element checkbox = driver().findField("checkedBox", root());
        assertThat(checkbox.getSelected(), is(true));

        driver().uncheck(checkbox);

        Element findAgain = driver().findField("checkedBox", root());
        assertThat(findAgain.getSelected(), is(false));
    }


    @Test
    public void leaves_an_unchecked_checkbox_unchecked()
    {
        Element checkbox = driver().findField("uncheckedBox", root());
        assertThat(checkbox.getSelected(), is(false));

        driver().uncheck(checkbox);

        Element findAgain = driver().findField("uncheckedBox", root());
        assertThat(findAgain.getSelected(), is(false));
    }


    @Test
    public void fires_onclick_event_on_check()
    {
        Element checkbox = driver().findField("uncheckedBox", root());
        assertThat(checkbox.getValue(), is(equalTo("unchecked")));

        driver().check(checkbox);

        assertThat(driver().findField("uncheckedBox", root()).getValue(), is(equalTo("unchecked - clicked")));
    }


    @Test
    public void fires_onclick_event_on_uncheck()
    {
        Element checkbox = driver().findField("checkedBox", root());
        assertThat(checkbox.getValue(), is(equalTo("checked")));

        driver().uncheck(checkbox);

        assertThat(driver().findField("checkedBox", root()).getValue(), is(equalTo("checked - clicked")));
    }
}
