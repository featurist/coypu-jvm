package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_fields_by_placeholder extends DriverSpecs
{
    @Test
    public void Finds_text_field_by_placeholder()
    {
        assertThat(Driver().FindField("text input field with a placeholder", Root()).Id(), is(equalTo("textInputFieldWithPlaceholder")));
        assertThat(Driver().FindField("textarea field with a placeholder", Root()).Id(), is(equalTo("textareaFieldWithPlaceholder")));
    }
}
