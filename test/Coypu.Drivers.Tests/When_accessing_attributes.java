package Coypu.Drivers.Tests;

import Coypu.Element;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_accessing_attributes extends DriverSpecs {

    @Test
    public void Exposes_element_attributes()
    {
        Element formWithAttributesToTest = Driver().FindId("attributeTestForm", Root());
        assertThat(formWithAttributesToTest.Attribute("id"), is(equalTo("attributeTestForm")));
        assertThat(formWithAttributesToTest.Attribute("method"), is(equalTo("post")));
        assertThat(formWithAttributesToTest.Attribute("action"), is(equalTo("http://somesite.com/action.htm")));
        assertThat(formWithAttributesToTest.Attribute("target"), is(equalTo("_parent")));
    }

}
