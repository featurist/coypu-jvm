using System.Linq;
using NUnit.Framework;

package Coypu.Tests.When_interacting_with_the_browser
{
    [TestFixture]
    public class When_interacting_with_modal_dialogs : BrowserInteractionTests
    {
        @Test
        public void AcceptDialog_should_make_robust_call_to_underlying_driver()
        {
            browserSession.AcceptModalDialog();

            assertThat(driver.ModalDialogsAccepted.Any(), Is.False);
            RunQueryAndCheckTiming();
            assertThat(driver.ModalDialogsAccepted.Single(), Is.SameAs(browserSession));
        }

        @Test
        public void CancelDialog_should_make_robust_call_to_underlying_driver()
        {
            browserSession.CancelModalDialog();

            assertThat(driver.ModalDialogsCancelled.Any(), Is.False);
            RunQueryAndCheckTiming();
            assertThat(driver.ModalDialogsCancelled.Single(), Is.SameAs(browserSession));
        }

    }
}