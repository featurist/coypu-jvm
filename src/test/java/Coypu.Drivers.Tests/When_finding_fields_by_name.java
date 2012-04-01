package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_fields_by_name extends DriverSpecs {
    @Test
    public void finds_text_input()
    {
        assertThat(driver().findField("containerLabeledTextInputFieldName", root()).getValue(), is(equalTo("text input field two val")));
    }

    @Test
    public void finds_textarea()
    {
        assertThat(driver().findField("containerLabeledTextareaFieldName", root()).getValue(), is(equalTo("textarea field two val")));
    }

    @Test
    public void finds_select()
    {
        assertThat(driver().findField("containerLabeledSelectFieldName", root()).getId(), is(equalTo("containerLabeledSelectFieldId")));
    }

    @Test
    public void finds_checkbox()
    {
        assertThat(driver().findField("containerLabeledCheckboxFieldName", root()).getValue(), is(equalTo("checkbox field two val")));
    }

    @Test
    public void finds_radio_button()
    {
        assertThat(driver().findField("containerLabeledRadioFieldName", root()).getValue(), is(equalTo("radio field two val")));
    }

    @Test
    public void finds_password_input()
    {
        assertThat(driver().findField("containerLabeledPasswordFieldName", root()).getId(), is(equalTo("containerLabeledPasswordFieldId")));
    }

    @Test
    public void finds_file_input()
    {
        assertThat(driver().findField("containerLabeledFileFieldName", root()).getId(), is(equalTo("containerLabeledFileFieldId")));
    }
}
