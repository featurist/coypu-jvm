package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_inspecting_dialog_text extends DriverSpecs
{
    @Test
    public void Finds_exact_text_in_alert()
    {
        try {
            Driver().Click(Driver().FindLink("Trigger an alert", Root()));

            assertThat(Driver().HasDialog("You have triggered an alert and this is the text.", Root()), is(true));
            assertThat(Driver().HasDialog("You have triggered a different alert and this is the different text.", Root()), is(false));
        }
        finally {
            Driver().Dispose();
        }
    }
    @Test
    public void Finds_exact_text_in_confirm()
    {
        try {
            Driver().Click(Driver().FindLink("Trigger a confirm", Root()));

            assertThat(Driver().HasDialog("You have triggered a confirm and this is the text.", Root()), is(true));
            assertThat(Driver().HasDialog("You have triggered a different confirm and this is the different text.", Root()), is(false));
        }
        finally {
            Driver().Dispose();
        }
    }
}
