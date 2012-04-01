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
    public void setUpScope()
    {
        scope1 = new DriverScope(new Configuration(), new IdFinder(driver(), "scope1", root()), driver(),null,null,null);
        scope2 = new DriverScope(new Configuration(), new IdFinder(driver(), "scope2", root()), driver(),null,null,null);
    }

    @Test
    public void finds_button_by_name()
    {
        assertThat(driver().findButton("scopedButtonName", scope1).getId(), is(equalTo("scope1ButtonId")));
        assertThat(driver().findButton("scopedButtonName", scope2).getId(), is(equalTo("scope2ButtonId")));
    }

    @Test
    public void finds_input_button_by_value()
    {
        assertThat(driver().findButton("scoped input button", scope1).getId(), is(equalTo("scope1InputButtonId")));
        assertThat(driver().findButton("scoped input button", scope2).getId(), is(equalTo("scope2InputButtonId")));
    }

    @Test
    public void finds_button_by_text()
    {
        assertThat(driver().findButton("scoped button", scope1).getId(), is(equalTo("scope1ButtonId")));
        assertThat(driver().findButton("scoped button", scope2).getId(), is(equalTo("scope2ButtonId")));
    }
}
