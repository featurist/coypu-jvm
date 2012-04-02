package coypu.unitTests;

import coypu.BrowserSession;
import coypu.SessionConfiguration;
import coypu.unitTests.TestDoubles.FakeDriver;
import coypu.unitTests.TestDoubles.StubDriver;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class When_starting_and_ending_sessions
{
    private SessionConfiguration sessionConfiguration;

    @Before
    public void setUp()
    {
        sessionConfiguration = new SessionConfiguration();
        sessionConfiguration.Driver = FakeDriver.class;
    }

    @Test
    public void dispose_handles_a_disposed_session()
    {
        BrowserSession browserSession = new BrowserSession(sessionConfiguration);

        browserSession.dispose();
        browserSession.dispose();
    }

    @Test
    public void a_session_gets_its_driver_from_config()
    {
        sessionConfiguration.Driver = FakeDriver.class;
        BrowserSession browserSession = new BrowserSession(sessionConfiguration);
        try {
            assertThat(browserSession.driver(), is(instanceOf(FakeDriver.class)));
        }
        finally{
            browserSession.dispose();
        }

        sessionConfiguration.Driver = StubDriver.class;
        browserSession = new BrowserSession(sessionConfiguration);
        try {
            assertThat(browserSession.driver(), is(instanceOf(StubDriver.class)));
        }
        finally {
            browserSession.dispose();
        }
    }

    @Test
    public void session_exposes_native_driver_if_you_really_need_it()
    {
        BrowserSession session = new BrowserSession(sessionConfiguration);
        try {
            assertThat(session.getNative().toString(), is(equalTo("Native driver on fake driver")));
        }
        finally {
            session.dispose();
        }
    }
}
