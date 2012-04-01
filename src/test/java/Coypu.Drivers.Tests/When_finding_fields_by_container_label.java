package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_fields_by_container_label extends DriverSpecs
{
    @Test
    public void finds_text_input()
    {
        assertThat(driver().findField("text input field in a label container", root()).getId(), is(equalTo("containerLabeledTextInputFieldId")));
    }

    @Test
    public void finds_password()
    {
        assertThat(driver().findField("password field in a label container", root()).getId(), is(equalTo("containerLabeledPasswordFieldId")));
    }

    @Test
    public void finds_checkbox()
    {
        assertThat(driver().findField("checkbox field in a label container", root()).getId(), is(equalTo("containerLabeledCheckboxFieldId")));
    }

    @Test
    public void finds_radio()
    {
        assertThat(driver().findField("radio field in a label container", root()).getId(), is(equalTo("containerLabeledRadioFieldId")));
    }

    @Test
    public void finds_select()
    {
        assertThat(driver().findField("select field in a label container", root()).getId(), is(equalTo("containerLabeledSelectFieldId")));
    }

    @Test
    public void finds_textarea()
    {
        assertThat(driver().findField("textarea field in a label container", root()).getId(), is(equalTo("containerLabeledTextareaFieldId")));
    }

    @Test
    public void finds_file_field()
    {
        assertThat(driver().findField("file field in a label container", root()).getId(), is(equalTo("containerLabeledFileFieldId")));
    }
}
