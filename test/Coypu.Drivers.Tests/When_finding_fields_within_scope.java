package Coypu.Drivers.Tests;

import Coypu.Configuration;
import Coypu.DriverScope;
import Coypu.Finders.IdFinder;
import Coypu.MissingHtmlException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class When_finding_fields_within_scope extends DriverSpecs
{
    private DriverScope scope1;
    private DriverScope scope2;

    @Before
    public void SetUpScope()
    {
        scope1 = new DriverScope(new Configuration(),new IdFinder(Driver(), "scope1", Root()), Driver(), null, null, null);
        scope2 = new DriverScope(new Configuration(), new IdFinder(Driver(), "scope2", Root()), Driver(), null, null, null);
    }

    @Test
    public void Finds_text_input_by_for()
    {
        assertThat(Driver().FindField("scoped text input field linked by for", scope1).Id(), is(equalTo("scope1TextInputFieldId")));
        assertThat(Driver().FindField("scoped text input field linked by for", scope2).Id(), is(equalTo("scope2TextInputFieldId")));
    }

    @Test
    public void Finds_text_input_in_container_label()
    {
        assertThat(Driver().FindField("scoped text input field in a label container", scope1).Id(), is(equalTo("scope1ContainerLabeledTextInputFieldId")));
        assertThat(Driver().FindField("scoped text input field in a label container", scope2).Id(), is(equalTo("scope2ContainerLabeledTextInputFieldId")));
    }

    @Test
    public void Finds_text_input_by_placeholder()
    {
        assertThat(Driver().FindField("scoped text input field with a placeholder", scope1).Id(), is(equalTo("scope1TextInputFieldWithPlaceholder")));
        assertThat(Driver().FindField("scoped text input field with a placeholder", scope2).Id(), is(equalTo("scope2TextInputFieldWithPlaceholder")));
    }

    @Test
    public void Finds_text_input_by_name()
    {
        assertThat(Driver().FindField("containerLabeledTextInputFieldName", scope1).Id(), is(equalTo("scope1ContainerLabeledTextInputFieldId")));
        assertThat(Driver().FindField("containerLabeledTextInputFieldName", scope2).Id(), is(equalTo("scope2ContainerLabeledTextInputFieldId")));
    }

    @Test
    public void Finds_radio_button_by_value()
    {
        assertThat(Driver().FindField("scoped radio field one val", scope1).Id(), is(equalTo("scope1RadioFieldId")));
        assertThat(Driver().FindField("scoped radio field one val", scope2).Id(), is(equalTo("scope2RadioFieldId")));
    }

    @Test
    public void Does_not_find_text_input_by_id_outside_scope()
    {
        try{
            Driver().FindField("containerLabeledTextInputFieldId", scope1);
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}

        try{
            Driver().FindField("containerLabeledTextInputFieldId", scope2);
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}

    }
}
