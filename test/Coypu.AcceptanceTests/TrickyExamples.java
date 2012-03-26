package Coypu.AcceptanceTests;
/// <summary>
/// Simple examples for each API method - to show usage and check everything is wired up properly
/// </summary>

import Coypu.*;
import Coypu.Drivers.Browser;
import Coypu.Drivers.Selenium.SeleniumWebDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrickyExamples
{
    private static BrowserSession browser;
    private static Options tempOptions;

    @BeforeClass
    public static void SetUpFixture()
    {
        Configuration configuration = new Configuration();
                                    configuration.Timeout = TimeSpan.FromMilliseconds(2000);
                                    configuration.Driver = SeleniumWebDriver.class;
                                    configuration.Browser = Browser.Firefox;
        browser = new BrowserSession(configuration);
        
        tempOptions = null;

    }

    @AfterClass
    public static void TearDown()
    {
        browser.Dispose();
    }

    private void VisitTestPage(String page)
    {
        browser.Visit("file://localhost/Users/adrian/Documents/dev/coypu.java/test/Coypu.AcceptanceTests/html/" + page);
    }

    @Test
    public void Scope_becomes_stale()  {
        VisitTestPage("tricky.htm");

        ElementScope section1 = browser.FindSection("section 1", tempOptions);
        assertThat(section1.FindLink("the link", tempOptions).Exists(tempOptions), is(true));

        String originalLocation = browser.Location();

        VisitTestPage("iFrame1.htm");

        assertThat(section1.FindLink("the link", tempOptions).Missing(tempOptions), is(true));

        browser.ExecuteScript("window.setTimeout(function() {window.location.href = '" + originalLocation + "'},1000);");

        assertThat(section1.FindLink("the link", tempOptions).Exists(tempOptions), is(true));
    }

    @Test
    public void Scope_becomes_stale_iframe()  {
        VisitTestPage("InteractionTestsPage.htm");

        String originalLocation = browser.Location();

        ElementScope iframe = browser.FindIFrame("iframe1", tempOptions);
        ElementScope button = iframe.FindButton("scoped button", tempOptions);

        assertThat(iframe.HasContent("I am iframe one", tempOptions), is(true));

        VisitTestPage("tricky.htm");

        assertThat(iframe.Missing(tempOptions), is(true));
        assertThat(button.Missing(tempOptions), is(true));

        browser.ExecuteScript("window.setTimeout(function() {window.location.href = '" + originalLocation + "'},1000);");

        assertThat(iframe.HasContent("I am iframe one", tempOptions), is(true));
        button.Click(tempOptions);
    }

    @Test
    public void Scope_becomes_stale_window()  {
        VisitTestPage("InteractionTestsPage.htm");

        browser.ClickLink("Open pop up window", tempOptions);

        BrowserWindow popUp = browser.FindWindow("Pop Up Window", tempOptions);
        ElementScope button = popUp.FindButton("scoped button", tempOptions);

        assertThat(button.Exists(tempOptions),is(true));
        assertThat(popUp.HasContent("I am a pop up window", tempOptions),is(true));

        popUp.ExecuteScript("self.close()");

        assertThat(button.Missing( tempOptions),is(true));

        browser.ClickLink("Open pop up window", tempOptions);

        assertThat(popUp.HasContent("I am a pop up window", tempOptions),is(true));
        button.Click( tempOptions);
    }


}