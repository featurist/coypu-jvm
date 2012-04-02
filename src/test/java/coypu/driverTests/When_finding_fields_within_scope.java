package coypu.driverTests;

import coypu.SessionConfiguration;
import coypu.DriverScope;
import coypu.Finders.IdFinder;
import coypu.MissingHtmlException;
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
    public void setUpScope()
    {
        scope1 = new DriverScope(new SessionConfiguration(),new IdFinder(driver(), "scope1", root()), driver(), null, null, null);
        scope2 = new DriverScope(new SessionConfiguration(), new IdFinder(driver(), "scope2", root()), driver(), null, null, null);
    }

    @Test
    public void finds_text_input_by_for()
    {
        assertThat(driver().findField("scoped text input field linked by for", scope1).getId(), is(equalTo("scope1TextInputFieldId")));
        assertThat(driver().findField("scoped text input field linked by for", scope2).getId(), is(equalTo("scope2TextInputFieldId")));
    }

    @Test
    public void finds_text_input_in_container_label()
    {
        assertThat(driver().findField("scoped text input field in a label container", scope1).getId(), is(equalTo("scope1ContainerLabeledTextInputFieldId")));
        assertThat(driver().findField("scoped text input field in a label container", scope2).getId(), is(equalTo("scope2ContainerLabeledTextInputFieldId")));
    }

    @Test
    public void finds_text_input_by_placeholder()
    {
        assertThat(driver().findField("scoped text input field with a placeholder", scope1).getId(), is(equalTo("scope1TextInputFieldWithPlaceholder")));
        assertThat(driver().findField("scoped text input field with a placeholder", scope2).getId(), is(equalTo("scope2TextInputFieldWithPlaceholder")));
    }

    @Test
    public void finds_text_input_by_name()
    {
        assertThat(driver().findField("containerLabeledTextInputFieldName", scope1).getId(), is(equalTo("scope1ContainerLabeledTextInputFieldId")));
        assertThat(driver().findField("containerLabeledTextInputFieldName", scope2).getId(), is(equalTo("scope2ContainerLabeledTextInputFieldId")));
    }

    @Test
    public void finds_radio_button_by_value()
    {
        assertThat(driver().findField("scoped radio field one val", scope1).getId(), is(equalTo("scope1RadioFieldId")));
        assertThat(driver().findField("scoped radio field one val", scope2).getId(), is(equalTo("scope2RadioFieldId")));
    }

    @Test
    public void does_not_find_text_input_by_id_outside_scope()
    {
        try{
            driver().findField("containerLabeledTextInputFieldId", scope1);
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}

        try{
            driver().findField("containerLabeledTextInputFieldId", scope2);
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex){}

    }
}
