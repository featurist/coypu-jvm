package coypu.driverTests;

import coypu.SessionConfiguration;
import coypu.DriverScope;
import coypu.Finders.DocumentElementFinder;
import coypu.MissingHtmlException;
import org.junit.Test;

import static junit.framework.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class When_forced_to_consider_invisible_elements extends DriverSpecs
{
    private static DriverScope rootConsideringInvisibleElements()
    {
        SessionConfiguration sessionConfiguration = new SessionConfiguration();
        sessionConfiguration.ConsiderInvisibleElements = true;

        DriverScope rootConsideringInvisibleElements = new DriverScope(sessionConfiguration, new DocumentElementFinder(driver()), null, null, null, null);
        return rootConsideringInvisibleElements;
    }

    @Test
    public void does_find_hidden_inputs()
    {
        assertThat(driver().findField("firstHiddenInputId", rootConsideringInvisibleElements()).getValue(), is(equalTo("first hidden input")));

        try {
            driver().findField("firstHiddenInputId", root());
            fail("expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {}
    }


    @Test
    public void does_find_invisible_elements()
    {
        assertThat(driver().findButton("firstInvisibleInputId", rootConsideringInvisibleElements()).getName(), is(equalTo("firstInvisibleInputName")));

        try {
            driver().findButton("firstInvisibleInputId", root());
            fail("expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {}

    }
}
