package Coypu.AcceptanceTests;

import Coypu.BrowserSession;
import Coypu.BrowserWindow;
import Coypu.MissingHtmlException;
import Coypu.Options;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class MultipleSessions
{
    @Test
    public void Two_browser_sessions_can_be_controlled_independently()  {
        Options tempOptions = null;
        BrowserSession sessionOne = new BrowserSession();
         try {
            BrowserSession sessionTwo = new BrowserSession();
             try
             {
                VisitTestPage(sessionOne);
                VisitTestPage(sessionTwo);

                sessionOne.FillIn(sessionOne.FindCss("input[type=text]",tempOptions),tempOptions).With("from session one");
                sessionTwo.FillIn(sessionTwo.FindCss("input[type=text]",tempOptions),tempOptions).With("from session two");

                assertThat(sessionOne.FindCss("input[type=text]",tempOptions).Value(), is(equalTo("from session one")));
                assertThat(sessionTwo.FindCss("input[type=text]",tempOptions).Value(), is(equalTo("from session two")));
             }   
             finally {
                 sessionTwo.Dispose();
             }
         }
         finally {
             sessionOne.Dispose();
         }
    }

    private void VisitTestPage(BrowserWindow browser)
    {
        browser.Visit("file://localhost/Users/adrian/Documents/dev/coypu.java/test/Coypu.AcceptanceTests/html/InteractionTestsPage.htm");
    }
}
