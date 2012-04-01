package Coypu.Drivers.Tests;

import Coypu.ElementFound;
import Coypu.MissingHtmlException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class When_finding_buttons extends DriverSpecs
{
    @Test
    public void finds_a_particular_button_by_its_text()
    {
        assertThat(driver().findButton("first button", root()).getId(), is(equalTo("firstButtonId")));
        assertThat(driver().findButton("second button", root()).getId(), is(equalTo("secondButtonId")));
    }

    @Test
    public void finds_a_particular_button_by_its_id()
    {
        assertThat(driver().findButton("firstButtonId", root()).getText(), is(equalTo("first button")));
        assertThat(driver().findButton("thirdButtonId", root()).getText(), is(equalTo("third button")));
    }

    @Test
    public void finds_a_particular_button_by_its_name()
    {
        assertThat(driver().findButton("secondButtonName", root()).getText(), is(equalTo("second button")));
        assertThat(driver().findButton("thirdButtonName", root()).getText(), is(equalTo("third button")));
    }

    @Test
    public void finds_a_particular_input_button_by_its_value()
    {
        assertThat(driver().findButton("first input button", root()).getId(), is(equalTo("firstInputButtonId")));
        assertThat(driver().findButton("second input button", root()).getId(), is(equalTo("secondInputButtonId")));
    }

    @Test
    public void finds_a_particular_input_button_by_its_id()
    {
        assertThat(driver().findButton("firstInputButtonId", root()).getValue(), is(equalTo("first input button")));
        assertThat(driver().findButton("thirdInputButtonId", root()).getValue(), is(equalTo("third input button")));
    }

    @Test
    public void finds_a_particular_input_button_by_its_name()
    {
        assertThat(driver().findButton("secondInputButtonId", root()).getValue(), is(equalTo("second input button")));
        assertThat(driver().findButton("thirdInputButtonName", root()).getValue(), is(equalTo("third input button")));
    }

    @Test
    public void finds_a_particular_submit_button_by_its_value()
    {
        assertThat(driver().findButton("first submit button", root()).getId(), is(equalTo("firstSubmitButtonId")));
        assertThat(driver().findButton("second submit button", root()).getId(), is(equalTo("secondSubmitButtonId")));
    }

    @Test
    public void finds_a_particular_submit_button_by_its_id()
    {
        assertThat(driver().findButton("firstSubmitButtonId", root()).getValue(), is(equalTo("first submit button")));
        assertThat(driver().findButton("thirdSubmitButtonId", root()).getValue(), is(equalTo("third submit button")));
    }

    @Test
    public void finds_a_particular_submit_button_by_its_name()
    {
        assertThat(driver().findButton("secondSubmitButtonName", root()).getValue(), is(equalTo("second submit button")));
        assertThat(driver().findButton("thirdSubmitButtonName", root()).getValue(), is(equalTo("third submit button")));
    }

    @Test
    public void finds_image_buttons()
    {
        assertThat(driver().findButton("firstImageButtonId", root()).getValue(), is(equalTo("first image button")));
        assertThat(driver().findButton("secondImageButtonId", root()).getValue(), is(equalTo("second image button")));
    }

    @Test
    public void does_not_find_text_inputs()
    {
        try{
            driver().findButton("firstTextInputId", root());
            fail("Expected MissingHtmlException");        
        }
        catch(MissingHtmlException ex) {
        }
    }

    @Test
    public void does_not_find_hidden_inputs()
    {   
        try{
            driver().findButton("firstHiddenInputId", root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {
        }
    }

    @Test
    public void does_not_find_invisible_inputs()
    {
        try{
            driver().findButton("firstInvisibleInputId", root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {
        }
    }

    @Test
    public void finds_img_elements_with_role_button_by_alt_text()
    {
        assertThat(driver().findButton("I'm an image with the role of button", root()).getId(), is(equalTo("roleImageButtonId")));
    }

    @Test
    public void finds_any_elements_with_role_button_by_text()
    {
        assertThat(driver().findButton("I'm a span with the role of button", root()).getId(), is(equalTo("roleSpanButtonId")));
    }

    @Test
    public void finds_an_image_button_with_both_types_of_quote_in_my_value()
    {
        ElementFound button = driver().findButton("I'm an image button with \"both\" types of quote in my value", root());
        assertThat(button.getId(), is(equalTo("buttonWithBothQuotesId")));
    }
    @Test
    public void finds_a_particular_input_button_by_id_before_finding_by_id_ends_with()
    {
        assertThat(driver().findButton("otherButtonId", root()).getId(), is(equalTo("otherButtonId")));
    }
}
