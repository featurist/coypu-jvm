


package Coypu.Drivers.Tests
{
    class When_finding_sections_as_divs extends DriverSpecs
    {
        @Test
        public void Finds_by_h1_text()
        {
            Driver().FindSection("Div Section One h1", Root()).Id(), is(equalTo("divSectionOne");
            Driver().FindSection("Div Section Two h1", Root()).Id(), is(equalTo("divSectionTwo");
        }

        @Test
        public void Finds_by_h2_text()
        {
            Driver().FindSection("Div Section One h2", Root()).Id(), is(equalTo("divSectionOne");
            Driver().FindSection("Div Section Two h2", Root()).Id(), is(equalTo("divSectionTwo");
        }

        @Test
        public void Finds_by_h3_text()
        {
            Driver().FindSection("Div Section One h3", Root()).Id(), is(equalTo("divSectionOne");
            Driver().FindSection("Div Section Two h3", Root()).Id(), is(equalTo("divSectionTwo");
        }

        @Test
        public void Finds_by_h6_text()
        {
            Driver().FindSection("Div Section One h6", Root()).Id(), is(equalTo("divSectionOne");
            Driver().FindSection("Div Section Two h6", Root()).Id(), is(equalTo("divSectionTwo");
        }


        @Test
        public void Finds_by_h2_text_within_child_link()
        {
            Driver().FindSection("Div Section One h2 with link", Root()).Id(), is(equalTo("divSectionOneWithLink");
            Driver().FindSection("Div Section Two h2 with link", Root()).Id(), is(equalTo("divSectionTwoWithLink");
        }


        @Test
        public void Finds_by_div_by_id()
        {
            Driver().FindSection("divSectionOne", Root()).Native, is(equalTo(Driver().FindSection("Div Section One h1", Root()).Native);
            Driver().FindSection("divSectionTwo", Root()).Native, is(equalTo(Driver().FindSection("Div Section Two h1", Root()).Native);
        }
    }
}