package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_fields_by_for_attribute extends DriverSpecs
{
    @Test
    public void Finds_text_input()
    {
        assertThat(Driver().FindField("text input field linked by for", Root()).Id(), is(equalTo("forLabeledTextInputFieldId")));
    }

    @Test
    public void Finds_password_field()
    {
        assertThat(Driver().FindField("password field linked by for", Root()).Id(), is(equalTo("forLabeledPasswordFieldId")));
    }

    @Test
    public void Finds_select_field()
    {
        assertThat(Driver().FindField("select field linked by for", Root()).Id(), is(equalTo("forLabeledSelectFieldId")));
    }

    @Test
    public void Finds_checkbox()
    {
        assertThat(Driver().FindField("checkbox field linked by for", Root()).Id(), is(equalTo("forLabeledCheckboxFieldId")));
    }

    @Test
    public void Finds_radio_button()
    {
        assertThat(Driver().FindField("radio field linked by for", Root()).Id(), is(equalTo("forLabeledRadioFieldId")));
    }

    @Test
    public void Finds_textarea()
    {
        assertThat(Driver().FindField("textarea field linked by for", Root()).Id(), is(equalTo("forLabeledTextareaFieldId")));
    }

    @Test
    public void Finds_file_input()
    {
        assertThat(Driver().FindField("file field linked by for", Root()).Id(), is(equalTo("forLabeledFileFieldId")));
    }
}
