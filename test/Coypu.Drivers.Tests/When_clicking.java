package Coypu.Drivers.Tests;

import Coypu.Element;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_clicking extends DriverSpecs
{
    @Test
    public void Clicks_the_underlying_element()
    {
        Element element = Driver().FindButton("clickMeTest", Root());
        assertThat(Driver().FindButton("clickMeTest", Root()).Value(), is(equalTo("Click me")));

        Driver().Click(element);
        assertThat(Driver().FindButton("clickMeTest", Root()).Value(), is(equalTo("Click me - clicked")));
    }
}
