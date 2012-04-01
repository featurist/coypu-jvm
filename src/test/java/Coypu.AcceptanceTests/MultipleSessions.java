package Coypu.AcceptanceTests;

import Coypu.BrowserSession;
import Coypu.BrowserWindow;
import Coypu.Options;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class MultipleSessions
{
    @Test
    public void two_browser_sessions_can_be_controlled_independently()  {
        Options tempOptions = null;
        BrowserSession sessionOne = new BrowserSession();
         try {
            BrowserSession sessionTwo = new BrowserSession();
             try
             {
                visitTestPage(sessionOne);
                visitTestPage(sessionTwo);

                sessionOne.fillIn(sessionOne.findCss("input[type=text]",tempOptions),tempOptions).with("from session one");
                sessionTwo.fillIn(sessionTwo.findCss("input[type=text]",tempOptions),tempOptions).with("from session two");

                assertThat(sessionOne.findCss("input[type=text]",tempOptions).getValue(), is(equalTo("from session one")));
                assertThat(sessionTwo.findCss("input[type=text]",tempOptions).getValue(), is(equalTo("from session two")));
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
        browser.visit("file://localhost/Users/adrian/Documents/dev/coypu-jvm/src/test/Coypu.AcceptanceTests/html/InteractionTestsPage.htm");
    }
}
