


package Coypu.Drivers.Tests
{
    public class When_hovering extends DriverSpecs
    {
        @Test
        public void Mouses_over_the_underlying_element()

        {
            Element element = Driver().FindId("hoverOnMeTest", Root());
            Driver().FindId("hoverOnMeTest", Root()).Text, is(equalTo("Hover on me");
            Driver().Hover(element);
            Driver().FindId("hoverOnMeTest", Root()).Text, is(equalTo("Hover on me - hovered");
        }
    }
}