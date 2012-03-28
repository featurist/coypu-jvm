package Coypu.Drivers.Tests;

import Coypu.ElementFound;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_setting_fields extends DriverSpecs
{
    @Test
    public void Sets_value_of_text_input_field()
    {
        ElementFound textField = Driver().FindField("containerLabeledTextInputFieldName", Root());
        Driver().Set(textField, "New text input value");

        assertThat(textField.Value(), is(equalTo("New text input value")));

        ElementFound findAgain = Driver().FindField("containerLabeledTextInputFieldName", Root());
        assertThat(findAgain.Value(), is(equalTo("New text input value")));
    }

    @Test
    public void Sets_value_of_textarea_field()
    {
        ElementFound textField = Driver().FindField("containerLabeledTextareaFieldName", Root());
        Driver().Set(textField, "New textarea value");

        assertThat(textField.Value(), is(equalTo("New textarea value")));

        ElementFound findAgain = Driver().FindField("containerLabeledTextareaFieldName", Root());
        assertThat(findAgain.Value(), is(equalTo("New textarea value")));
    }

    @Test
    public void Selects_option_by_text_or_value()
    {
        ElementFound textField = Driver().FindField("containerLabeledSelectFieldId", Root());
        assertThat(textField.Value(), is(equalTo("select2value1")));

        Driver().Select(textField, "select two option two");

        ElementFound findAgain = Driver().FindField("containerLabeledSelectFieldId", Root());
        assertThat(findAgain.Value(), is(equalTo("select2value2")));

        Driver().Select(textField, "select2value1");

        ElementFound andAgain = Driver().FindField("containerLabeledSelectFieldId", Root());
        assertThat(andAgain.Value(), is(equalTo("select2value1")));
    }

    @Test
    public void Fires_change_event_when_selecting_an_option()
    {
        ElementFound textField = Driver().FindField("containerLabeledSelectFieldId", Root());
        assertThat(textField.Name(), is(equalTo("containerLabeledSelectFieldName")));

        Driver().Select(textField, "select two option two");

        assertThat(Driver().FindField("containerLabeledSelectFieldId", Root()).Name(), is(equalTo("containerLabeledSelectFieldName - changed")));
    }
}
