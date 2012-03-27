



package Coypu.Drivers.Tests
{
    public class When_finding_buttons_within_scope extends DriverSpecs
    {
        private DriverScope scope1;
        private DriverScope scope2;

        [SetUp]
        public void SetUpScope()
        {
            scope1 = new DriverScope(new Configuration(), new IdFinder(Driver, "scope1", Root()), Driver,null,null,null);
            scope2 = new DriverScope(new Configuration(), new IdFinder(Driver, "scope2", Root()), Driver,null,null,null);
        }

        @Test
        public void Finds_button_by_name()
        {
            Driver().FindButton("scopedButtonName", scope1).Id(), is(equalTo("scope1ButtonId");
            Driver().FindButton("scopedButtonName", scope2).Id(), is(equalTo("scope2ButtonId");
        }

        @Test
        public void Finds_input_button_by_value()
        {
            Driver().FindButton("scoped input button", scope1).Id(), is(equalTo("scope1InputButtonId");
            Driver().FindButton("scoped input button", scope2).Id(), is(equalTo("scope2InputButtonId");
        }

        @Test
        public void Finds_button_by_text()
        {
            Driver().FindButton("scoped button", scope1).Id(), is(equalTo("scope1ButtonId");
            Driver().FindButton("scoped button", scope2).Id(), is(equalTo("scope2ButtonId");
        }
    }
}