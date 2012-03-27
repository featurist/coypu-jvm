package Coypu.Drivers.Tests;

import Coypu.Element;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_hovering extends DriverSpecs
{
    @Test
    public void Mouses_over_the_underlying_element()

    {
        Element element = Driver().FindId("hoverOnMeTest", Root());
        assertThat(Driver().FindId("hoverOnMeTest", Root()).Text(), is(equalTo("Hover on me")));

        Driver().Hover(element);

        assertThat(Driver().FindId("hoverOnMeTest", Root()).Text(), is(equalTo("Hover on me - hovered")));
    }


}
