package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_fields_by_value extends DriverSpecs
{
    @Test
    public void Finds_radio_button_by_value()
    {
        assertThat(Driver().FindField("radio field one val", Root()).Name(), is(equalTo("forLabeledRadioFieldName")));
        assertThat(Driver().FindField("radio field two val", Root()).Name(), is(equalTo("containerLabeledRadioFieldName")));
    }

}
