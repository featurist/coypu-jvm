package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class When_interacting_with_dialogs extends DriverSpecs
{
    @Test
    public void Accepts_alerts()
    {
        try
        {
            Driver().Click(Driver().FindLink("Trigger an alert", Root()));
            assertThat(Driver().HasDialog("You have triggered an alert and this is the text.", Root()), is(true));
            
            Driver().AcceptModalDialog(Root());
            assertThat(Driver().HasDialog("You have triggered an alert and this is the text.", Root()), is(false));
        }
        finally {
            Driver().Dispose();
        }
    }


    @Test
    public void Clears_dialog()
    {
        try{
            Driver().Click(Driver().FindLink("Trigger a confirm", Root()));
            assertThat(Driver().HasDialog("You have triggered a confirm and this is the text.", Root()), is(true));
            Driver().AcceptModalDialog(Root());
            assertThat(Driver().HasDialog("You have triggered a confirm and this is the text.", Root()), is(false));
        }
        finally {
            Driver().Dispose();
        }
    }

    @Test
    public void Returns_true()
    {
        try {
            Driver().Click(Driver().FindLink("Trigger a confirm", Root()));
            Driver().AcceptModalDialog(Root());
            assertThat(Driver().FindLink("Trigger a confirm - accepted", Root()), is(not(null)));
        }
        finally {
            Driver().Dispose();
        }
    }


    @Test
    public void Cancel_Clears_dialog()
    {
        try {
            Driver().Click(Driver().FindLink("Trigger a confirm", Root()));
            assertThat(Driver().HasDialog("You have triggered a confirm and this is the text.", Root()), is(true));

            Driver().CancelModalDialog(Root());
            assertThat(Driver().HasDialog("You have triggered a confirm and this is the text.", Root()), is(false));
        }
        finally {
            Driver().Dispose();
        }
    }

    @Test
    public void Cancel_Returns_false()
    {
        try {
            Driver().Click(Driver().FindLink("Trigger a confirm", Root()));
            Driver().CancelModalDialog(Root());
            assertThat(Driver().FindLink("Trigger a confirm - cancelled", Root()), is(not(null)));
        }
        finally {
            Driver().Dispose();
        }
    }
}
