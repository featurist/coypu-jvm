package Coypu.AcceptanceTests;

import Coypu.*;
import Coypu.Queries.PredicateQuery;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class States
{
    private static Configuration configuration;
    private static BrowserSession browser;
    private static Options tempOptions;

    @BeforeClass
    public static void SetUpFixture()
    {
        configuration = new Configuration();
        configuration.Timeout = TimeSpan.FromMilliseconds(1000);
        browser = new BrowserSession(configuration);
        tempOptions = null;
    }

    @AfterClass
    public static void TearDown()
    {
        browser.Dispose();
    }

    @Before
    public void SetUp()
    {
        ReloadTestPage();
    }

    private void ShowStateAsync(String id, int delayMilliseconds)  {
        browser.ExecuteScript(
                String.format("setTimeout(function() {{document.getElementById('%1$s').style.display = 'block'}},%2$s)",
                        id, delayMilliseconds));
    }


    private void ReloadTestPage()
    {
        browser.Visit("file://localhost/Users/adrian/Documents/dev/coypu.java/test/Coypu.AcceptanceTests/html/states.htm");
    }

    @Test
    public void Page_reaches_first_of_three_possible_states() {
        ShowStateAsync("state1", 500);

        State state1 = new State(new PredicateQuery() {
            public void Run() {
                browser.HasContent("State one reached",tempOptions);
            }
        });
        State state2 = new State(new PredicateQuery() {
            public void Run() {
                browser.HasContent("State two reached",tempOptions);
            }
        });
        State state3  = new State(new PredicateQuery() {
            public void Run() {
                browser.HasContent("State three reached",tempOptions);
            }
        });

        State foundState = browser.FindState(new State[] {state1, state2, state3},tempOptions);

        assertThat(foundState, is(new org.hamcrest.core.IsSame<State>(state1)));
    }
//
//    @Test
//    public void Page_reaches_second_of_three_possible_states()
//    {
//        ShowStateAsync("state2", 500);
//
//        var state1 = new State(new LambdaQuery<bool>(() => browser.HasContent("State one reached")));
//        var state2 = new State(new LambdaQuery<bool>(() => browser.HasContent("State two reached")));
//        var state3 = new State(new LambdaQuery<bool>(() => browser.HasContent("State three reached")));
//
//        State foundState = browser.FindState(state1, state2, state3);
//
//        assertThat(foundState, Is.SameAs(state2));
//    }
//
//
//    @Test
//    public void Page_reaches_third_of_three_possible_states()
//    {
//        ShowStateAsync("state3", 500);
//
//        var state1 = new State(new LambdaQuery<bool>(() => browser.HasContent("State one reached")));
//        var state2 = new State(new LambdaQuery<bool>(() => browser.HasContent("State two reached")));
//        var state3 = new State(new LambdaQuery<bool>(() => browser.HasContent("State three reached")));
//
//        State foundState = browser.FindState(state1, state2, state3);
//
//        assertThat(foundState, Is.SameAs(state3));
//    }
//
//    @Test
//    public void Page_reaches_none_of_three_possible_states()
//    {
//        var state1 = new State(new LambdaQuery<bool>(() => browser.HasContent("State one reached")));
//        var state2 = new State(new LambdaQuery<bool>(() => browser.HasContent("State two reached")));
//        var state3 = new State(new LambdaQuery<bool>(() => browser.HasContent("State three reached")));
//
//        Assert.Throws<MissingHtmlException>(() => browser.FindState(state1, state2, state3));
//    }
}
