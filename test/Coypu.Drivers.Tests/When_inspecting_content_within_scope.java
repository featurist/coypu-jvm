package Coypu.Drivers.Tests;

import Coypu.Configuration;
import Coypu.DriverScope;
import Coypu.Finders.IdFinder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class When_inspecting_content_within_scope extends DriverSpecs
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
    public void Finds_content_within_scope()
    {
        assertThat(Driver().HasContent("scope 1", scope1), is(true));
        assertThat(Driver().HasContent("scope 2", scope2), is(true));
    }

    @Test
    public void Does_not_find_content_outside_scope()
    {
        assertThat(Driver().HasContent("scope 2", scope1), is(false));
        assertThat(Driver().HasContent("scope 1", scope2), is(false));
    }
}
