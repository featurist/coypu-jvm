package coypu.unitTests.When_interacting_with_the_browser;

import coypu.DriverScope;
import org.junit.Test;

import static coypu.unitTests.When_interacting_with_the_browser.IsEmpty.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class When_interacting_with_modal_dialogs extends BrowserInteractionTests
{
    @Test
    public void acceptDialog_should_make_robust_call_to_underlying_driver()
    {
        browserSession.acceptModalDialog();

        assertThat(driver.ModalDialogsAccepted, is(empty()));
        runQueryAndCheckTiming();
        assertThat(driver.ModalDialogsAccepted.get(0), is(sameInstance((DriverScope)browserSession)));
    }

    @Test
    public void cancelDialog_should_make_robust_call_to_underlying_driver()
    {
        browserSession.cancelModalDialog();

        assertThat(driver.ModalDialogsCancelled, is(empty()));
        runQueryAndCheckTiming();
        assertThat(driver.ModalDialogsCancelled.get(0), is(sameInstance((DriverScope)browserSession)));
    }

}
