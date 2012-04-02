package coypu.driverTests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_fields_id extends DriverSpecs
{
    @Test
    public void finds_field()
    {
        assertThat(driver().findField("containerLabeledTextInputFieldId", root()).getValue(), is(equalTo("text input field two val")));
    }

    @Test
    public void finds_textarea()
    {
        assertThat(driver().findField("containerLabeledTextareaFieldId", root()).getValue(), is(equalTo("textarea field two val")));
    }

    @Test
    public void finds_select()
    {
        assertThat(driver().findField("containerLabeledSelectFieldId", root()).getName(), is(equalTo("containerLabeledSelectFieldName")));
    }

    @Test
    public void finds_checkbox()
    {
        assertThat(driver().findField("containerLabeledCheckboxFieldId", root()).getValue(), is(equalTo("checkbox field two val")));
    }

    @Test
    public void finds_radio()
    {
        assertThat(driver().findField("containerLabeledRadioFieldId", root()).getValue(), is(equalTo("radio field two val")));
    }

    @Test
    public void finds_password()
    {
        assertThat(driver().findField("containerLabeledPasswordFieldId", root()).getName(), is(equalTo("containerLabeledPasswordFieldName")));
    }

    @Test
    public void finds_file()
    {
        assertThat(driver().findField("containerLabeledFileFieldId", root()).getName(), is(equalTo("containerLabeledFileFieldName")));
    }
}