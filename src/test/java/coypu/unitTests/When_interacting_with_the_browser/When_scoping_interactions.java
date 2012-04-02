package coypu.unitTests.When_interacting_with_the_browser;

import coypu.SessionConfiguration;
import coypu.Element;
import coypu.ElementScope;
import coypu.unitTests.TestBuilders.TestSessionBuilder;
import coypu.unitTests.TestDoubles.ImmediateSingleExecutionFakeRobustWrapper;
import coypu.unitTests.TestDoubles.StubElement;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class When_scoping_interactions extends BrowserInteractionTests
{
    @Test
    public void it_sets_the_scope_on_the_driver()
    {
        browserSession = TestSessionBuilder.build(new SessionConfiguration(), driver, new ImmediateSingleExecutionFakeRobustWrapper(), fakeWaiter, null, stubUrlBuilder);
        StubElement section = new StubElement();
        Element expectedLink = new StubElement();
        driver.stubSection("some section", section, browserSession);

        ElementScope innerScope = browserSession.findSection("some section");

        driver.stubLink("some link", expectedLink, innerScope);

        Element actualLink = innerScope.findLink("some link").now();

        assertThat(actualLink, is(sameInstance(expectedLink)));

    }
}
