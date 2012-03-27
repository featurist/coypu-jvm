


package Coypu.Drivers.Tests
{
    class When_finding_fieldsets extends DriverSpecs
    {
        @Test
        public void Finds_by_legend_text()
        {
            Driver().FindFieldset("Scope 1", Root()).Id(), is(equalTo("fieldsetScope1");
            Driver().FindFieldset("Scope 2", Root()).Id(), is(equalTo("fieldsetScope2");
        }

        @Test
        public void Finds_by_id()
        {
            Driver().FindFieldset("fieldsetScope1", Root()).Native, is(equalTo(Driver().FindFieldset("Scope 1", Root()).Native);
            Driver().FindFieldset("fieldsetScope2", Root()).Native, is(equalTo(Driver().FindFieldset("Scope 2", Root()).Native);
        }

        @Test
        public void Finds_only_fieldsets()
        {
            Assert.Throws<MissingHtmlException>(() => Driver().FindFieldset("scope1TextInputFieldId", Root()));
            Assert.Throws<MissingHtmlException>(() => Driver().FindFieldset("sectionOne", Root()));
        }
    }
}