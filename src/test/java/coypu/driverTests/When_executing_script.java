package coypu.driverTests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_executing_script extends DriverSpecs
{
    @Test
    public void runs_the_script_in_the_browser()
    {
        assertThat(driver().findButton("firstButtonId", root()).getText(), is(equalTo("first button")));

        driver().executeScript("document.getElementById('firstButtonId').innerHTML = 'script executed';", root());

        assertThat(driver().findButton("firstButtonId", root()).getText(), is(equalTo("script executed")));
    }


    @Test
    public void returns_the_result()
    {
        String result = driver().executeScript("return document.getElementById('firstButtonId').innerHTML;", root());
        assertThat(result, is(equalTo("first button")));
    }
}
