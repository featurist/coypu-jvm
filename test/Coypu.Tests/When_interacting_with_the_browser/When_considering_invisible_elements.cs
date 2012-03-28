using NUnit.Framework;

package Coypu.Tests.When_interacting_with_the_browser
{
    [TestFixture]
    public class When_considering_invisible_elements : BrowserInteractionTests
    {
        @Test
        public void It_sets_the_driver_scope_to_consider_invisible_elements()
        {
            assertThat(browserSession.ConsiderInvisibleElements, Is.False);

            browserSession.ClickButton("invisible", new Options{ConsiderInvisibleElements = true});

            assertThat(browserSession.ConsiderInvisibleElements, Is.True);

            browserSession.ClickButton("visible");

            assertThat(browserSession.ConsiderInvisibleElements, Is.False);
        }
    }
}