package coypu.driverTests;

import coypu.ElementFound;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_setting_fields extends DriverSpecs
{
    @Test
    public void sets_value_of_text_input_field()
    {
        ElementFound textField = driver().findField("containerLabeledTextInputFieldName", root());
        driver().set(textField, "New text input value");

        assertThat(textField.getValue(), is(equalTo("New text input value")));

        ElementFound findAgain = driver().findField("containerLabeledTextInputFieldName", root());
        assertThat(findAgain.getValue(), is(equalTo("New text input value")));
    }

    @Test
    public void sets_value_of_textarea_field()
    {
        ElementFound textField = driver().findField("containerLabeledTextareaFieldName", root());
        driver().set(textField, "New textarea value");

        assertThat(textField.getValue(), is(equalTo("New textarea value")));

        ElementFound findAgain = driver().findField("containerLabeledTextareaFieldName", root());
        assertThat(findAgain.getValue(), is(equalTo("New textarea value")));
    }

    @Test
    public void selects_option_by_text_or_value()
    {
        ElementFound textField = driver().findField("containerLabeledSelectFieldId", root());
        assertThat(textField.getValue(), is(equalTo("select2value1")));

        driver().select(textField, "select two option two");

        ElementFound findAgain = driver().findField("containerLabeledSelectFieldId", root());
        assertThat(findAgain.getValue(), is(equalTo("select2value2")));

        driver().select(textField, "select2value1");

        ElementFound andAgain = driver().findField("containerLabeledSelectFieldId", root());
        assertThat(andAgain.getValue(), is(equalTo("select2value1")));
    }

    @Test
    public void fires_change_event_when_selecting_an_option()
    {
        ElementFound textField = driver().findField("containerLabeledSelectFieldId", root());
        assertThat(textField.getName(), is(equalTo("containerLabeledSelectFieldName")));

        driver().select(textField, "select two option two");

        assertThat(driver().findField("containerLabeledSelectFieldId", root()).getName(), is(equalTo("containerLabeledSelectFieldName - changed")));
    }
}
