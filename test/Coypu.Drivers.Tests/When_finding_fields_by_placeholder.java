


package Coypu.Drivers.Tests
{
    class When_finding_fields_by_placeholder extends DriverSpecs
    {
        @Test
        public void Finds_text_field_by_placeholder()
        {
            Driver().FindField("text input field with a placeholder", Root()).Id(), is(equalTo("textInputFieldWithPlaceholder");
            Driver().FindField("textarea field with a placeholder", Root()).Id(), is(equalTo("textareaFieldWithPlaceholder");
        }
    }
}