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
    public static void setUpFixture()
    {
        Configuration configuration = new Configuration();
                                    configuration.Timeout = TimeSpan.fromMilliseconds(2000);
                                    configuration.Driver = SeleniumWebDriver.class;
                                    configuration.Browser = Browser.Firefox;
        browser = new BrowserSession(configuration);
        
        tempOptions = null;

    }

    @AfterClass
    public static void tearDown()
    {
        browser.dispose();
    }

    private void visitTestPage(String page)
    {
        browser.visit("file://localhost/Users/adrian/Documents/dev/coypu-jvm/src/test/Coypu.AcceptanceTests/html/" + page);
    }

    @Test
    public void scope_becomes_stale()  {
        visitTestPage("tricky.htm");

        ElementScope section1 = browser.findSection("section 1", tempOptions);
        assertThat(section1.findLink("the link", tempOptions).exists(tempOptions), is(true));

        String originalLocation = browser.location();

        visitTestPage("iFrame1.htm");

        assertThat(section1.findLink("the link", tempOptions).missing(tempOptions), is(true));

        browser.executeScript("window.setTimeout(function() {window.location.href = '" + originalLocation + "'},1000);");

        assertThat(section1.findLink("the link", tempOptions).exists(tempOptions), is(true));
    }

    @Test
    public void scope_becomes_stale_iframe()  {
        visitTestPage("InteractionTestsPage.htm");

        String originalLocation = browser.location();

        ElementScope iframe = browser.findIFrame("iframe1", tempOptions);
        ElementScope button = iframe.findButton("scoped button", tempOptions);

        assertThat(iframe.hasContent("I am iframe one", tempOptions), is(true));

        visitTestPage("tricky.htm");

        assertThat(iframe.missing(tempOptions), is(true));
        assertThat(button.missing(tempOptions), is(true));

        browser.executeScript("window.setTimeout(function() {window.location.href = '" + originalLocation + "'},1000);");

        assertThat(iframe.hasContent("I am iframe one", tempOptions), is(true));
        button.click(tempOptions);
    }

    @Test
    public void scope_becomes_stale_window()  {
        visitTestPage("InteractionTestsPage.htm");

        browser.clickLink("Open pop up window", tempOptions);

        BrowserWindow popUp = browser.findWindow("Pop Up Window", tempOptions);
        ElementScope button = popUp.findButton("scoped button", tempOptions);

        assertThat(button.exists(tempOptions),is(true));
        assertThat(popUp.hasContent("I am a pop up window", tempOptions),is(true));

        popUp.executeScript("self.close()");

        assertThat(button.missing( tempOptions),is(true));

        browser.clickLink("Open pop up window", tempOptions);

        assertThat(popUp.hasContent("I am a pop up window", tempOptions),is(true));
        button.click( tempOptions);
    }


}