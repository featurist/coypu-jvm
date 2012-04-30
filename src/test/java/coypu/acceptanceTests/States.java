package coypu.acceptanceTests;

import coypu.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class States
{
    private static SessionConfiguration sessionConfiguration;
    private static BrowserSession browser;

    @BeforeClass
    public static void setUpFixture()
    {
        sessionConfiguration = new SessionConfiguration();
        sessionConfiguration.Timeout = TimeSpan.fromMilliseconds(1000);
        browser = new BrowserSession(sessionConfiguration);
    }

    @AfterClass
    public static void tearDown()
    {
        browser.dispose();
    }

    @Before
    public void setUp()
    {
        reloadTestPage();
    }

    private void showStateAsync(String id, int delayMilliseconds)  {
        browser.executeScript(
                String.format("setTimeout(function() {{document.getElementById('%1$s').style.display = 'block'}},%2$s)",
                        id, delayMilliseconds));
    }


    private void reloadTestPage()
    {
        browser.visit(ApiExamples.testPage("states.htm"));
    }

    //
    private State[] getThreeStates() {
        State state1 = new State() {
            public Boolean predicate() {
                return browser.hasContent("State one reached");
            }
        };
        State state2 = new State() {
            public Boolean predicate() {
                return browser.hasContent("State two reached");
            }
        };
        State state3  = new State() {
            public Boolean predicate() {
                return browser.hasContent("State three reached");
            }
        };

        return new State[]{state1, state2, state3};
    }

    @Test
    public void page_reaches_first_of_three_possible_states() {
        showStateAsync("state1", 500);
        State[] states = getThreeStates();

        State foundState = browser.findState(states);

        assertThat(foundState, is(sameInstance(states[0])));
    }

    @Test
    public void page_reaches_second_of_three_possible_states() {
        showStateAsync("state2", 500);
        State[] states = getThreeStates();

        State foundState = browser.findState(states);

        assertThat(foundState, is(sameInstance(states[1])));
    }

    @Test
    public void page_reaches_third_of_three_possible_states(){
        showStateAsync("state3", 500);
        State[] states = getThreeStates();

        State foundState = browser.findState(states);

        assertThat(foundState, is(sameInstance(states[2])));
    }

    @Test
    public void page_reaches_none_of_three_possible_states()
    {
        State[] states = getThreeStates();

        try{
            browser.findState(states);
            fail("Expected MissingHtmlException");
        }
        catch(MissingHtmlException ex) {
        }
    }
}
