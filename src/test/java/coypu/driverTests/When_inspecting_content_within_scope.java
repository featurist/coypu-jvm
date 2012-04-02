package coypu.driverTests;

import coypu.SessionConfiguration;
import coypu.DriverScope;
import coypu.Finders.IdFinder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_inspecting_content_within_scope extends DriverSpecs
{
    private DriverScope scope1;
    private DriverScope scope2;

    @Before
    public void setUpScope()
    {
        scope1 = new DriverScope(new SessionConfiguration(), new IdFinder(driver(), "scope1", root()), driver(),null,null,null);
        scope2 = new DriverScope(new SessionConfiguration(), new IdFinder(driver(), "scope2", root()), driver(),null,null,null);
    }

    @Test
    public void finds_content_within_scope()
    {
        assertThat(driver().hasContent("Scope 1", scope1), is(true));
        assertThat(driver().hasContent("Scope 2", scope2), is(true));
    }

    @Test
    public void does_not_find_content_outside_scope()
    {
        assertThat(driver().hasContent("Scope 2", scope1), is(false));
        assertThat(driver().hasContent("Scope 1", scope2), is(false));
    }
}
