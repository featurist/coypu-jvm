


package Coypu.Drivers.Tests
{
    class When_finding_fields_by_value extends DriverSpecs
    {
        @Test
        public void Finds_radio_button_by_value()
        {
            Driver().FindField("radio field one val", Root()).Name, is(equalTo("forLabeledRadioFieldName");
            Driver().FindField("radio field two val", Root()).Name, is(equalTo("containerLabeledRadioFieldName");
        }

    }
}
