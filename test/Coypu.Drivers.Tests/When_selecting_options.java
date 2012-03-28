package Coypu.Drivers.Tests;

import Coypu.ElementFound;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_selecting_options extends DriverSpecs
{
    @Test
    public void Sets_text_of_selected_option()
    {
        ElementFound textField = Driver().FindField("containerLabeledSelectFieldId", Root());
        assertThat(textField.SelectedOption(), is(equalTo("select two option one")));

        Driver().Select(textField, "select2value2");

        textField = Driver().FindField("containerLabeledSelectFieldId", Root());
        assertThat(textField.SelectedOption(), is(equalTo("select two option two")));
    }
}
