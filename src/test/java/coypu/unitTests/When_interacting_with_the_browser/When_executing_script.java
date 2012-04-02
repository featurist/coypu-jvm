package coypu.unitTests.When_interacting_with_the_browser;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_executing_script extends BrowserInteractionTests
{
    @Test
    public void visit_passes_message_directly_to_the_driver_returning_response_immediately()
    {
        String script = "document.getElementById('asdf').click();";
        String expectedReturnValue = "script return value";

        driver.stubExecuteScript(script, expectedReturnValue, browserSession);

        assertThat(browserSession.executeScript(script), is(equalTo(expectedReturnValue)));
    }
}
