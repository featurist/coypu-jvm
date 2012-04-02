package coypu.acceptanceTests;

import coypu.BrowserSession;
import coypu.BrowserWindow;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class MultipleSessions
{
    @Test
    public void two_browser_sessions_can_be_controlled_independently()  {
        BrowserSession sessionOne = new BrowserSession();
         try {
            BrowserSession sessionTwo = new BrowserSession();
             try
             {
                visitTestPage(sessionOne);
                visitTestPage(sessionTwo);

                sessionOne.fillIn(sessionOne.findCss("input[type=text]")).with("from session one");
                sessionTwo.fillIn(sessionTwo.findCss("input[type=text]")).with("from session two");

                assertThat(sessionOne.findCss("input[type=text]").getValue(), is(equalTo("from session one")));
                assertThat(sessionTwo.findCss("input[type=text]").getValue(), is(equalTo("from session two")));
             }   
             finally {
                 sessionTwo.dispose();
             }
         }
         finally {
             sessionOne.dispose();
         }
    }

    private void visitTestPage(BrowserWindow browser)
    {
        browser.visit(ApiExamples.InteractionTestsPage());
    }
}
