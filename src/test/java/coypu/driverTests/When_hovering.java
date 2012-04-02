package coypu.driverTests;

import coypu.Element;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_hovering extends DriverSpecs
{
    @Test
    public void mouses_over_the_underlying_element()

    {
        Element element = driver().findId("hoverOnMeTest", root());
        assertThat(driver().findId("hoverOnMeTest", root()).getText(), is(equalTo("Hover on me")));

        driver().hover(element);

        assertThat(driver().findId("hoverOnMeTest", root()).getText(), is(equalTo("Hover on me - hovered")));
    }


}
