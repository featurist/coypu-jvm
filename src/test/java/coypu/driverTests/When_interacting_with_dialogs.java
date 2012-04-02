package coypu.driverTests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_interacting_with_dialogs extends DriverSpecs
{
    @Test
    public void accepts_alerts()
    {
        try
        {
            driver().click(driver().findLink("Trigger an alert", root()));
            assertThat(driver().hasDialog("You have triggered an alert and this is the text.", root()), is(true));
            
            driver().acceptModalDialog(root());
            assertThat(driver().hasDialog("You have triggered an alert and this is the text.", root()), is(false));
        }
        finally {
            driver().dispose();
        }
    }


    @Test
    public void clears_dialog()
    {
        try{
            driver().click(driver().findLink("Trigger a confirm", root()));
            assertThat(driver().hasDialog("You have triggered a confirm and this is the text.", root()), is(true));
            driver().acceptModalDialog(root());
            assertThat(driver().hasDialog("You have triggered a confirm and this is the text.", root()), is(false));
        }
        finally {
            driver().dispose();
        }
    }

    @Test
    public void returns_true()
    {
        try {
            driver().click(driver().findLink("Trigger a confirm", root()));
            driver().acceptModalDialog(root());

            driver().findLink("Trigger a confirm - accepted", root());
        }
        finally {
            driver().dispose();
        }
    }


    @Test
    public void cancel_Clears_dialog()
    {
        try {
            driver().click(driver().findLink("Trigger a confirm", root()));
            assertThat(driver().hasDialog("You have triggered a confirm and this is the text.", root()), is(true));

            driver().cancelModalDialog(root());
            assertThat(driver().hasDialog("You have triggered a confirm and this is the text.", root()), is(false));
        }
        finally {
            driver().dispose();
        }
    }

    @Test
    public void cancel_Returns_false()
    {
        try {
            driver().click(driver().findLink("Trigger a confirm", root()));
            driver().cancelModalDialog(root());

            driver().findLink("Trigger a confirm - cancelled", root());
        }
        finally {
            driver().dispose();
        }
    }
}
