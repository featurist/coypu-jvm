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
    public void Finds_a_particular_button_by_its_text()
    {
        assertThat(Driver().FindButton("first button", Root()).Id(), is(equalTo("firstButtonId")));
        assertThat(Driver().FindButton("second button", Root()).Id(), is(equalTo("secondButtonId")));
    }

    @Test
    public void Finds_a_particular_button_by_its_id()
    {
        assertThat(Driver().FindButton("firstButtonId", Root()).Text(), is(equalTo("first button")));
        assertThat(Driver().FindButton("thirdButtonId", Root()).Text(), is(equalTo("third button")));
    }

    @Test
    public void Finds_a_particular_button_by_its_name()
    {
        assertThat(Driver().FindButton("secondButtonName", Root()).Text(), is(equalTo("second button")));
        assertThat(Driver().FindButton("thirdButtonName", Root()).Text(), is(equalTo("third button")));
    }

    @Test
    public void Finds_a_particular_input_button_by_its_value()
    {
        assertThat(Driver().FindButton("first input button", Root()).Id(), is(equalTo("firstInputButtonId")));
        assertThat(Driver().FindButton("second input button", Root()).Id(), is(equalTo("secondInputButtonId")));
    }

    @Test
    public void Finds_a_particular_input_button_by_its_id()
    {
        assertThat(Driver().FindButton("firstInputButtonId", Root()).Value(), is(equalTo("first input button")));
        assertThat(Driver().FindButton("thirdInputButtonId", Root()).Value(), is(equalTo("third input button")));
    }

    @Test
    public void Finds_a_particular_input_button_by_id_ends_with()
    {
        assertThat(Driver().FindButton("rdInputButtonId", Root()).Value(), is(equalTo("third input button")));
    }

    @Test
    public void Finds_a_particular_input_button_by_its_name()
    {
        assertThat(Driver().FindButton("secondInputButtonId", Root()).Value(), is(equalTo("second input button")));
        assertThat(Driver().FindButton("thirdInputButtonName", Root()).Value(), is(equalTo("third input button")));
    }

    @Test
    public void Finds_a_particular_submit_button_by_its_value()
    {
        assertThat(Driver().FindButton("first submit button", Root()).Id(), is(equalTo("firstSubmitButtonId")));
        assertThat(Driver().FindButton("second submit button", Root()).Id(), is(equalTo("secondSubmitButtonId")));
    }

    @Test
    public void Finds_a_particular_submit_button_by_its_id()
    {
        assertThat(Driver().FindButton("firstSubmitButtonId", Root()).Value(), is(equalTo("first submit button")));
        assertThat(Driver().FindButton("thirdSubmitButtonId", Root()).Value(), is(equalTo("third submit button")));
    }

    @Test
    public void Finds_a_particular_submit_button_by_its_name()
    {
        assertThat(Driver().FindButton("secondSubmitButtonName", Root()).Value(), is(equalTo("second submit button")));
        assertThat(Driver().FindButton("thirdSubmitButtonName", Root()).Value(), is(equalTo("third submit button")));
    }

    @Test
    public void Finds_image_buttons()
    {
        assertThat(Driver().FindButton("firstImageButtonId", Root()).Value(), is(equalTo("first image button")));
        assertThat(Driver().FindButton("secondImageButtonId", Root()).Value(), is(equalTo("second image button")));
    }

    @Test
    public void Does_not_find_text_inputs()
    {
        try{
            Driver().FindButton("firstTextInputId", Root());
            fail("Expected MissingHtmlException");        
        }
        catch(MissingHtmlException ex) {
        }
    }

    @Test
    public void Does_not_find_hidden_inputs()
    {   
        try{
            Driver().FindButton("firstHiddenInputId", Root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {
        }
    }

    @Test
    public void Does_not_find_invisible_inputs()
    {
        try{
            Driver().FindButton("firstInvisibleInputId", Root());
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {
        }
    }

    @Test
    public void Finds_img_elements_with_role_button_by_alt_text()
    {
        try{
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {
        }
        assertThat(Driver().FindButton("I'm an image with the role of button", Root()).Id(), is(equalTo("roleImageButtonId")));
    }

    @Test
    public void Finds_any_elements_with_role_button_by_text()
    {
        assertThat(Driver().FindButton("I'm a span with the role of button", Root()).Id(), is(equalTo("roleSpanButtonId")));
    }

    @Test
    public void Finds_an_image_button_with_both_types_of_quote_in_my_value()
    {
        ElementFound button = Driver().FindButton("I'm an image button with \"both\" types of quote in my value", Root());
        assertThat(button.Id(), is(equalTo("buttonWithBothQuotesId")));
    }
    @Test
    public void Finds_a_particular_input_button_by_id_before_finding_by_id_ends_with()
    {
        assertThat(Driver().FindButton("otherButtonId", Root()).Id(), is(equalTo("otherButtonId")));
    }
}
