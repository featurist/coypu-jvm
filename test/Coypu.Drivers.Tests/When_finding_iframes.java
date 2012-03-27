


package Coypu.Drivers.Tests
{
    class When_finding_iframes extends DriverSpecs
    {
        @Test
        public void Finds_by_header_text()
        {
            Driver().FindIFrame("I am iframe one", Root()).Id(), is(equalTo("iframe1");
            Driver().FindIFrame("I am iframe two", Root()).Id(), is(equalTo("iframe2");
        }

        @Test
        public void Finds_by_id()
        {
            Driver().FindIFrame("iframe1", Root()).Id(), is(equalTo("iframe1");
            Driver().FindIFrame("iframe2", Root()).Id(), is(equalTo("iframe2");
        }

        @Test
        public void Finds_by_title()
        {
            Driver().FindIFrame("iframe one title", Root()).Id(), is(equalTo("iframe1");
            Driver().FindIFrame("iframe two title", Root()).Id(), is(equalTo("iframe2");
        }
    }
}