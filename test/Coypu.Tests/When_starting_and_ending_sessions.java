package Coypu.Tests;

import Coypu.BrowserSession;
import Coypu.Configuration;
import Coypu.Tests.TestDoubles.FakeDriver;
import Coypu.Tests.TestDoubles.StubDriver;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class When_starting_and_ending_sessions
{
    private Configuration configuration;

    @Before
    public void SetUp()
    {
        configuration = new Configuration();
        configuration.Driver = FakeDriver.class;
    }

    @Test
    public void Dispose_handles_a_disposed_session()
    {
        BrowserSession browserSession = new BrowserSession(configuration);

        browserSession.Dispose();
        browserSession.Dispose();
    }

    @Test
    public void A_session_gets_its_driver_from_config()
    {
        configuration.Driver = FakeDriver.class;
        BrowserSession browserSession = new BrowserSession(configuration);
        try {
            assertThat(browserSession.Driver(), is(instanceOf(FakeDriver.class)));
        }
        finally{
            browserSession.Dispose();
        }

        configuration.Driver = StubDriver.class;
        browserSession = new BrowserSession(configuration);
        try {
            assertThat(browserSession.Driver(), is(instanceOf(StubDriver.class)));
        }
        finally {
            browserSession.Dispose();
        }
    }

    @Test
    public void Session_exposes_native_driver_if_you_really_need_it()
    {
        BrowserSession session = new BrowserSession(configuration);
        try {
            assertThat(session.Native().toString(), is(equalTo("Native driver on fake driver")));
        }
        finally {
            session.Dispose();
        }
    }
}
