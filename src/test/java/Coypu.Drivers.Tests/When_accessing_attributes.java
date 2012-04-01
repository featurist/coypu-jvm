package Coypu.Drivers.Tests;

import Coypu.Element;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_accessing_attributes extends DriverSpecs {

    @Test
    public void exposes_element_attributes()
    {
        Element formWithAttributesToTest = driver().findId("attributeTestForm", root());
        assertThat(formWithAttributesToTest.getAttribute("Id"), is(equalTo("attributeTestForm")));
        assertThat(formWithAttributesToTest.getAttribute("method"), is(equalTo("post")));
        assertThat(formWithAttributesToTest.getAttribute("action"), is(equalTo("http://somesite.com/action.htm")));
        assertThat(formWithAttributesToTest.getAttribute("target"), is(equalTo("_parent")));
    }

}
