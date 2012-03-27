


package Coypu.Drivers.Tests
{
    class When_finding_fields_by_for_attribute extends DriverSpecs
    {
        @Test
        public void Finds_text_input()
        {
            Driver().FindField("text input field linked by for", Root()).Id(), is(equalTo("forLabeledTextInputFieldId");
        }

        @Test
        public void Finds_password_field()
        {
            Driver().FindField("password field linked by for", Root()).Id(), is(equalTo("forLabeledPasswordFieldId");
        }

        @Test
        public void Finds_select_field()
        {
            Driver().FindField("select field linked by for", Root()).Id(), is(equalTo("forLabeledSelectFieldId");
        }

        @Test
        public void Finds_checkbox()
        {
            Driver().FindField("checkbox field linked by for", Root()).Id(), is(equalTo("forLabeledCheckboxFieldId");
        }

        @Test
        public void Finds_radio_button()
        {
            Driver().FindField("radio field linked by for", Root()).Id(), is(equalTo("forLabeledRadioFieldId");
        }

        @Test
        public void Finds_textarea()
        {
            Driver().FindField("textarea field linked by for", Root()).Id(), is(equalTo("forLabeledTextareaFieldId");
        }

        @Test
        public void Finds_file_input()
        {
            Driver().FindField("file field linked by for", Root()).Id(), is(equalTo("forLabeledFileFieldId");
        }
    }
}