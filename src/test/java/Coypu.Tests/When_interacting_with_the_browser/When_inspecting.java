package Coypu.Tests.When_interacting_with_the_browser;

import Coypu.Options;
import Coypu.TimeSpan;
import org.junit.Before;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_inspecting extends BrowserInteractionTests {

    protected String locator;
    protected TimeSpan individualTimeout;
    protected Options options;

    @Before
    public void setUp() {
        locator = "Find me " + UUID.randomUUID();
        individualTimeout = TimeSpan.fromMilliseconds(6543);
        options = new Options();
        options.Timeout = individualTimeout;
    }

    protected void assertFoundRobustly(boolean stubResult, TimeSpan individualTimeout, boolean actualImmediateResult) {
        assertThat("Result was not found robustly", actualImmediateResult, is(equalTo(!stubResult)));

        Object queryResult = runQueryAndCheckTiming(individualTimeout);

        assertThat(queryResult, is(equalTo((Object) stubResult)));
    }

    protected void assertFoundRobustlyReversed(boolean stubResult, boolean actualImmediateResult) {
        assertThat("Result was not found robustly", actualImmediateResult, is(equalTo(!stubResult)));

        Object queryResult = runQueryAndCheckTiming();

        assertThat(queryResult, is(equalTo((Object) !stubResult)));
    }
}
