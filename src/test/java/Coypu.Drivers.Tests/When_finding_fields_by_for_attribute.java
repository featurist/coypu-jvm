package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_fields_by_for_attribute extends DriverSpecs
{
    @Test
    public void finds_text_input()
    {
        assertThat(driver().findField("text input field linked by for", root()).getId(), is(equalTo("forLabeledTextInputFieldId")));
    }

    @Test
    public void finds_password_field()
    {
        assertThat(driver().findField("password field linked by for", root()).getId(), is(equalTo("forLabeledPasswordFieldId")));
    }

    @Test
    public void finds_select_field()
    {
        assertThat(driver().findField("select field linked by for", root()).getId(), is(equalTo("forLabeledSelectFieldId")));
    }

    @Test
    public void finds_checkbox()
    {
        assertThat(driver().findField("checkbox field linked by for", root()).getId(), is(equalTo("forLabeledCheckboxFieldId")));
    }

    @Test
    public void finds_radio_button()
    {
        assertThat(driver().findField("radio field linked by for", root()).getId(), is(equalTo("forLabeledRadioFieldId")));
    }

    @Test
    public void finds_textarea()
    {
        assertThat(driver().findField("textarea field linked by for", root()).getId(), is(equalTo("forLabeledTextareaFieldId")));
    }

    @Test
    public void finds_file_input()
    {
        assertThat(driver().findField("file field linked by for", root()).getId(), is(equalTo("forLabeledFileFieldId")));
    }
}
