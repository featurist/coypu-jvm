package Coypu.AcceptanceTests;

import Coypu.BrowserSession;
import Coypu.Configuration;
import Coypu.Drivers.Selenium.SeleniumWebDriver;
import Coypu.TimeSpan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExternalExamples
{
    private Configuration configuration;
    private BrowserSession browser;

    @Before
    public void SetUp()
    {
        configuration = new Configuration();
        configuration.SetAppHost("www.google.com");
        configuration.Driver = SeleniumWebDriver.class;

        configuration.Timeout = TimeSpan.FromSeconds(10);

        browser = new BrowserSession(configuration);
    }
    
    @After
    public void TearDown()
    {
        browser.Dispose();
    }

    @Test
    public void Retries_Autotrader()  {
        browser.Visit("http://www.autotrader.co.uk/used-cars");

        browser.FillIn("postcode",null).With("N1 1AA");

        browser.FindField("make",null).Click(null);

        browser.Select("citroen",null).From("make");
        browser.Select("c4_grand_picasso",null).From("model");

        browser.Select("National",null).From("radius");
        browser.Select("diesel",null).From("fuel-type");
        browser.Select("up_to_7_years_old",null).From("maximum-age");
        browser.Select("up_to_60000_miles",null).From("maximum-mileage");

        browser.FillIn("Add keyword",null).With("vtr");
    }

//
//    [Test, Explicit]
//    public void Visibility_NewTwitterLogin()
//    {
//        browser.Visit("http://www.twitter.com");
//
//        browser.FillIn("session[username_or_email]").With("coyputester2");
//        browser.FillIn("session[password]").With("Nappybara");
//    }
//
//    [Test,
//     Ignore("Make checkboxes on carbuzz are jumping around after you click each one. Re-enable when that is fixed")]
//    public void FindingStuff_CarBuzz()
//    {
//        browser.Visit("http://carbuzz.heroku.com/car_search");
//
//        Console.WriteLine(browser.Has(browser.FindSection("Make")));
//        Console.WriteLine(browser.HasNo(browser.FindSection("Bake")));
//
//        browser.Check("Audi");
//        browser.Check("BMW");
//        browser.Check("Mercedes");
//
//        assertThat(browser.HasContentMatch(new Regex(@"\b83 car reviews found")));
//
//        browser.FindSection("Seats").Click();
//        browser.ClickButton("4");
//
//        assertThat(browser.HasContentMatch(new Regex(@"\b28 car reviews found")));
//    }
//
//    @Test
//    public void HtmlUnitDriver()
//    {
//        configuration.AppHost = "www.google.com";
//        configuration.Browser = Browser.HtmlUnit;
//
//        try
//        {
//            using (var htmlUnit = new BrowserSession(configuration))
//            {
//                htmlUnit.Visit("/");
//            }
//            Assert.Fail("Expected an exception attempting to connect to HtmlUnit driver");
//        }
//        catch (WebDriverException e)
//        {
//            assertThat(e.Message, Is.StringContaining("No connection could be made because the target machine actively refused it 127.0.0.1:4444"));
//        }
//    }
}
