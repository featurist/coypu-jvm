package coypu.driverTests;

import coypu.Element;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_clicking extends DriverSpecs
{
    @Test
    public void clicks_the_underlying_element()
    {
        Element element = driver().findButton("clickMeTest", root());
        assertThat(driver().findButton("clickMeTest", root()).getValue(), is(equalTo("Click me")));

        driver().click(element);
        assertThat(driver().findButton("clickMeTest", root()).getValue(), is(equalTo("Click me - clicked")));
    }
}
