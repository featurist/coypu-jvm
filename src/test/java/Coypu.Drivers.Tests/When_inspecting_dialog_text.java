package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_inspecting_dialog_text extends DriverSpecs
{
    @Test
    public void finds_exact_text_in_alert()
    {
        try {
            driver().click(driver().findLink("Trigger an alert", root()));

            assertThat(driver().hasDialog("You have triggered an alert and this is the text.", root()), is(true));
            assertThat(driver().hasDialog("You have triggered a different alert and this is the different text.", root()), is(false));
        }
        finally {
            driver().dispose();
        }
    }
    @Test
    public void finds_exact_text_in_confirm()
    {
        try {
            driver().click(driver().findLink("Trigger a confirm", root()));

            assertThat(driver().hasDialog("You have triggered a confirm and this is the text.", root()), is(true));
            assertThat(driver().hasDialog("You have triggered a different confirm and this is the different text.", root()), is(false));
        }
        finally {
            driver().dispose();
        }
    }
}
