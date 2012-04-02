package coypu.driverTests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_fields_by_placeholder extends DriverSpecs
{
    @Test
    public void finds_text_field_by_placeholder()
    {
        assertThat(driver().findField("text input field with a placeholder", root()).getId(), is(equalTo("textInputFieldWithPlaceholder")));
        assertThat(driver().findField("textarea field with a placeholder", root()).getId(), is(equalTo("textareaFieldWithPlaceholder")));
    }
}
