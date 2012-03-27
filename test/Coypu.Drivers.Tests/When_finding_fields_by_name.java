package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_fields_by_name extends DriverSpecs {
    @Test
    public void Finds_text_input()
    {
        assertThat(Driver().FindField("containerLabeledTextInputFieldName", Root()).Value(), is(equalTo("text input field two val")));
    }

    @Test
    public void Finds_textarea()
    {
        assertThat(Driver().FindField("containerLabeledTextareaFieldName", Root()).Value(), is(equalTo("textarea field two val")));
    }

    @Test
    public void Finds_select()
    {
        assertThat(Driver().FindField("containerLabeledSelectFieldName", Root()).Id(), is(equalTo("containerLabeledSelectFieldId")));
    }

    @Test
    public void Finds_checkbox()
    {
        assertThat(Driver().FindField("containerLabeledCheckboxFieldName", Root()).Value(), is(equalTo("checkbox field two val")));
    }

    @Test
    public void Finds_radio_button()
    {
        assertThat(Driver().FindField("containerLabeledRadioFieldName", Root()).Value(), is(equalTo("radio field two val")));
    }

    @Test
    public void Finds_password_input()
    {
        assertThat(Driver().FindField("containerLabeledPasswordFieldName", Root()).Id(), is(equalTo("containerLabeledPasswordFieldId")));
    }

    @Test
    public void Finds_file_input()
    {
        assertThat(Driver().FindField("containerLabeledFileFieldName", Root()).Id(), is(equalTo("containerLabeledFileFieldId")));
    }
}
