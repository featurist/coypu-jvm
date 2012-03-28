package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_fields_id extends DriverSpecs
{
    @Test
    public void Finds_field()
    {
        assertThat(Driver().FindField("containerLabeledTextInputFieldId", Root()).Value(), is(equalTo("text input field two val")));
    }

    @Test
    public void Finds_textarea()
    {
        assertThat(Driver().FindField("containerLabeledTextareaFieldId", Root()).Value(), is(equalTo("textarea field two val")));
    }

    @Test
    public void Finds_select()
    {
        assertThat(Driver().FindField("containerLabeledSelectFieldId", Root()).Name(), is(equalTo("containerLabeledSelectFieldName")));
    }

    @Test
    public void Finds_checkbox()
    {
        assertThat(Driver().FindField("containerLabeledCheckboxFieldId", Root()).Value(), is(equalTo("checkbox field two val")));
    }

    @Test
    public void Finds_radio()
    {
        assertThat(Driver().FindField("containerLabeledRadioFieldId", Root()).Value(), is(equalTo("radio field two val")));
    }

    @Test
    public void Finds_password()
    {
        assertThat(Driver().FindField("containerLabeledPasswordFieldId", Root()).Name(), is(equalTo("containerLabeledPasswordFieldName")));
    }

    @Test
    public void Finds_file()
    {
        assertThat(Driver().FindField("containerLabeledFileFieldId", Root()).Name(), is(equalTo("containerLabeledFileFieldName")));
    }
}