package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_fields_by_value extends DriverSpecs
{
    @Test
    public void finds_radio_button_by_value()
    {
        assertThat(driver().findField("radio field one val", root()).getName(), is(equalTo("forLabeledRadioFieldName")));
        assertThat(driver().findField("radio field two val", root()).getName(), is(equalTo("containerLabeledRadioFieldName")));
    }

}
