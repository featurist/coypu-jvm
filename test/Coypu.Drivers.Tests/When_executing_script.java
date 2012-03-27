package Coypu.Drivers.Tests;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_executing_script extends DriverSpecs
{
    @Test
    public void Runs_the_script_in_the_browser()
    {
        assertThat(Driver().FindButton("firstButtonId", Root()).Text(), is(equalTo("first button")));

        Driver().ExecuteScript("document.getElementById('firstButtonId').innerHTML = 'script executed';", Root());

        assertThat(Driver().FindButton("firstButtonId", Root()).Text(), is(equalTo("script executed")));
    }


    @Test
    public void Returns_the_result()
    {
        String result = Driver().ExecuteScript("return document.getElementById('firstButtonId').innerHTML;", Root());
        assertThat(result, is(equalTo("first button")));
    }
}
