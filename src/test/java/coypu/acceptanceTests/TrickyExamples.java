package coypu.acceptanceTests;
/// <summary>
/// Simple examples for each API method - to show usage and check everything is wired up properly
///

import coypu.*;
import coypu.Drivers.Browser;
import coypu.Drivers.Selenium.SeleniumWebDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrickyExamples
{
    private static BrowserSession browser;

    @BeforeClass
    public static void setUpFixture()
    {
        SessionConfiguration sessionConfiguration = new SessionConfiguration();
                                    sessionConfiguration.Timeout = TimeSpan.fromMilliseconds(2000);
                                    sessionConfiguration.Driver = SeleniumWebDriver.class;
                                    sessionConfiguration.Browser = Browser.Firefox;
        browser = new BrowserSession(sessionConfiguration);

    }

    @AfterClass
    public static void tearDown()
    {
        browser.dispose();
    }

    private void visitTestPage(String page)
    {
        browser.visit(ApiExamples.TestPage(page));
    }

    @Test
    public void scope_becomes_stale()  {
        visitTestPage("tricky.htm");

        ElementScope section1 = browser.findSection("section 1");
        assertThat(section1.findLink("the link").exists(), is(true));

        String originalLocation = browser.location();

        visitTestPage("iFrame1.htm");

        assertThat(section1.findLink("the link").missing(), is(true));

        browser.executeScript("window.setTimeout(function() {window.location.href = '" + originalLocation + "'},1000);");

        assertThat(section1.findLink("the link").exists(), is(true));
    }

    @Test
    public void scope_becomes_stale_iframe()  {
        visitTestPage("InteractionTestsPage.htm");

        String originalLocation = browser.location();

        ElementScope iframe = browser.findIFrame("iframe1");
        ElementScope button = iframe.findButton("scoped button");

        assertThat(iframe.hasContent("I am iframe one"), is(true));

        visitTestPage("tricky.htm");

        assertThat(iframe.missing(), is(true));
        assertThat(button.missing(), is(true));

        browser.executeScript("window.setTimeout(function() {window.location.href = '" + originalLocation + "'},1000);");

        assertThat(iframe.hasContent("I am iframe one"), is(true));
        button.click();
    }

    @Test
    public void scope_becomes_stale_window()  {
        visitTestPage("InteractionTestsPage.htm");

        browser.clickLink("Open pop up window");

        BrowserWindow popUp = browser.findWindow("Pop Up Window");
        ElementScope button = popUp.findButton("scoped button");

        assertThat(button.exists(),is(true));
        assertThat(popUp.hasContent("I am a pop up window"),is(true));

        popUp.executeScript("self.close()");

        assertThat(button.missing(),is(true));

        browser.clickLink("Open pop up window");

        assertThat(popUp.hasContent("I am a pop up window"),is(true));
        button.click();
    }


}