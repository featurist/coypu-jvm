


package Coypu.Drivers.Tests
{
    class When_finding_fields_by_container_label extends DriverSpecs
    {
        @Test
        public void Finds_text_input()
        {
            Driver().FindField("text input field in a label container", Root()).Id(), is(equalTo("containerLabeledTextInputFieldId");
        }

        @Test
        public void Finds_password()
        {
            Driver().FindField("password field in a label container", Root()).Id(), is(equalTo("containerLabeledPasswordFieldId");
        }

        @Test
        public void Finds_checkbox()
        {
            Driver().FindField("checkbox field in a label container", Root()).Id(), is(equalTo("containerLabeledCheckboxFieldId");
        }

        @Test
        public void Finds_radio()
        {
            Driver().FindField("radio field in a label container", Root()).Id(), is(equalTo("containerLabeledRadioFieldId");
        }

        @Test
        public void Finds_select()
        {
            Driver().FindField("select field in a label container", Root()).Id(), is(equalTo("containerLabeledSelectFieldId");
        }

        @Test
        public void Finds_textarea()
        {
            Driver().FindField("textarea field in a label container", Root()).Id(), is(equalTo("containerLabeledTextareaFieldId");
        }

        @Test
        public void Finds_file_field()
        {
            Driver().FindField("file field in a label container", Root()).Id(), is(equalTo("containerLabeledFileFieldId");
        }
    }
}