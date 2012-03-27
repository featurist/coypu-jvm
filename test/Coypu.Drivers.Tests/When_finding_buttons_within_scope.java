package Coypu.Drivers.Tests;

import Coypu.Configuration;
import Coypu.DriverScope;
import Coypu.Finders.IdFinder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_finding_buttons_within_scope extends DriverSpecs
{
    private DriverScope scope1;
    private DriverScope scope2;

    @Before
    public void SetUpScope()
    {
        scope1 = new DriverScope(new Configuration(), new IdFinder(Driver(), "scope1", Root()), Driver(),null,null,null);
        scope2 = new DriverScope(new Configuration(), new IdFinder(Driver(), "scope2", Root()), Driver(),null,null,null);
    }

    @Test
    public void Finds_button_by_name()
    {
        assertThat(Driver().FindButton("scopedButtonName", scope1).Id(), is(equalTo("scope1ButtonId")));
        assertThat(Driver().FindButton("scopedButtonName", scope2).Id(), is(equalTo("scope2ButtonId")));
    }

    @Test
    public void Finds_input_button_by_value()
    {
        assertThat(Driver().FindButton("scoped input button", scope1).Id(), is(equalTo("scope1InputButtonId")));
        assertThat(Driver().FindButton("scoped input button", scope2).Id(), is(equalTo("scope2InputButtonId")));
    }

    @Test
    public void Finds_button_by_text()
    {
        assertThat(Driver().FindButton("scoped button", scope1).Id(), is(equalTo("scope1ButtonId")));
        assertThat(Driver().FindButton("scoped button", scope2).Id(), is(equalTo("scope2ButtonId")));
    }
}
