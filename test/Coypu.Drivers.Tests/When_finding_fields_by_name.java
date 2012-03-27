


package Coypu.Drivers.Tests
{
    class When_finding_fields_by_name extends DriverSpecs {


    @Test
        public void Finds_text_input()
        {
            Driver().FindField("containerLabeledTextInputFieldName", Root()).Value(), is(equalTo("text input field two val");
        }

        @Test
        public void Finds_textarea()
        {
            Driver().FindField("containerLabeledTextareaFieldName", Root()).Value(), is(equalTo("textarea field two val");
        }

        @Test
        public void Finds_select()
        {
            Driver().FindField("containerLabeledSelectFieldName", Root()).Id(), is(equalTo("containerLabeledSelectFieldId");
        }

        @Test
        public void Finds_checkbox()
        {
            Driver().FindField("containerLabeledCheckboxFieldName", Root()).Value(), is(equalTo("checkbox field two val");
        }

        @Test
        public void Finds_radio_button()
        {
            Driver().FindField("containerLabeledRadioFieldName", Root()).Value(), is(equalTo("radio field two val");
        }

        @Test
        public void Finds_password_input()
        {
            Driver().FindField("containerLabeledPasswordFieldName", Root()).Id(), is(equalTo("containerLabeledPasswordFieldId");
        }

        @Test
        public void Finds_file_input()
        {
            Driver().FindField("containerLabeledFileFieldName", Root()).Id(), is(equalTo("containerLabeledFileFieldId");
        }

    }
}