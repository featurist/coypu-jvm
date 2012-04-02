package coypu.driverTests;

import coypu.ElementFound;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_selecting_options extends DriverSpecs
{
    @Test
    public void sets_text_of_selected_option()
    {
        ElementFound textField = driver().findField("containerLabeledSelectFieldId", root());
        assertThat(textField.getSelectedOption(), is(equalTo("select two option one")));

        driver().select(textField, "select2value2");

        textField = driver().findField("containerLabeledSelectFieldId", root());
        assertThat(textField.getSelectedOption(), is(equalTo("select two option two")));
    }
}
