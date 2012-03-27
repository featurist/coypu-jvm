package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_fields_by_container_label extends DriverSpecs
{
    @Test
    public void Finds_text_input()
    {
        assertThat(Driver().FindField("text input field in a label container", Root()).Id(), is(equalTo("containerLabeledTextInputFieldId")));
    }

    @Test
    public void Finds_password()
    {
        assertThat(Driver().FindField("password field in a label container", Root()).Id(), is(equalTo("containerLabeledPasswordFieldId")));
    }

    @Test
    public void Finds_checkbox()
    {
        assertThat(Driver().FindField("checkbox field in a label container", Root()).Id(), is(equalTo("containerLabeledCheckboxFieldId")));
    }

    @Test
    public void Finds_radio()
    {
        assertThat(Driver().FindField("radio field in a label container", Root()).Id(), is(equalTo("containerLabeledRadioFieldId")));
    }

    @Test
    public void Finds_select()
    {
        assertThat(Driver().FindField("select field in a label container", Root()).Id(), is(equalTo("containerLabeledSelectFieldId")));
    }

    @Test
    public void Finds_textarea()
    {
        assertThat(Driver().FindField("textarea field in a label container", Root()).Id(), is(equalTo("containerLabeledTextareaFieldId")));
    }

    @Test
    public void Finds_file_field()
    {
        assertThat(Driver().FindField("file field in a label container", Root()).Id(), is(equalTo("containerLabeledFileFieldId")));
    }
}
