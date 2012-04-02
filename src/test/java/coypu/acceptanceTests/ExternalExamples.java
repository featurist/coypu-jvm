package coypu.acceptanceTests;

import coypu.BrowserSession;
import coypu.SessionConfiguration;
import coypu.Drivers.Selenium.SeleniumWebDriver;
import coypu.TimeSpan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExternalExamples
{
    private SessionConfiguration sessionConfiguration;
    private BrowserSession browser;

    @Before
    public void setUp()
    {
        sessionConfiguration = new SessionConfiguration();
        sessionConfiguration.setAppHost("www.google.com");
        sessionConfiguration.Driver = SeleniumWebDriver.class;

        sessionConfiguration.Timeout = TimeSpan.fromSeconds(10);

        browser = new BrowserSession(sessionConfiguration);
    }
    
    @After
    public void tearDown()
    {
        browser.dispose();
    }

    @Test
    public void retries_Autotrader()  {
        browser.visit("http://www.autotrader.co.uk/used-cars");

        browser.fillIn("postcode",null).with("N1 1AA");

        browser.findField("make",null).click(null);

        browser.select("citroen",null).from("make");
        browser.select("c4_grand_picasso",null).from("model");

        browser.select("National",null).from("radius");
        browser.select("diesel",null).from("fuel-type");
        browser.select("up_to_7_years_old",null).from("maximum-age");
        browser.select("up_to_60000_miles",null).from("maximum-mileage");

        browser.fillIn("Add keyword",null).with("vtr");
    }

//
//    [Test, Explicit]
//    public void visibility_NewTwitterLogin()
//    {
//        browser.visit("http://www.twitter.com");
//
//        browser.fillIn("session[username_or_email]").with("coyputester2");
//        browser.fillIn("session[password]").with("Nappybara");
//    }
//
//    [Test,
//     ignore("Make checkboxes on carbuzz are jumping around after you click each one. Re-enable when that is fixed")]
//    public void findingStuff_CarBuzz()
//    {
//        browser.visit("http://carbuzz.heroku.com/car_search");
//
//        Console.writeLine(browser.has(browser.findSection("Make")));
//        Console.writeLine(browser.hasNo(browser.findSection("Bake")));
//
//        browser.check("Audi");
//        browser.check("BMW");
//        browser.check("Mercedes");
//
//        assertThat(browser.hasContentMatch(new Regex(@"\b83 car reviews found")));
//
//        browser.findSection("Seats").click();
//        browser.clickButton("4");
//
//        assertThat(browser.hasContentMatch(new Regex(@"\b28 car reviews found")));
//    }
//
//    @Test
//    public void htmlUnitDriver()
//    {
//        sessionConfiguration.AppHost = "www.google.com";
//        sessionConfiguration.Browser = Browser.HtmlUnit;
//
//        try
//        {
//            using (var htmlUnit = new BrowserSession(sessionConfiguration))
//            {
//                htmlUnit.visit("/");
//            }
//            Assert.fail("Expected an exception attempting to connect to HtmlUnit driver");
//        }
//        catch (WebDriverException e)
//        {
//            assertThat(e.Message, Is.stringContaining("No connection could be made because the target machine actively refused it 127.0.0.1:4444"));
//        }
//    }
}
