package Coypu.AcceptanceTests;

import Coypu.BrowserSession;
import Coypu.Configuration;
import Coypu.MissingHtmlException;
import Coypu.TimeSpan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Location
{
    //private SinatraSite sinatraSite;
    private BrowserSession browser;


    @Before
    public void setUp()
    {
        //sinatraSite = new SinatraSite(String.format("Sites\\%1$s.rb", "site_with_secure_resources"));

        Configuration configuration = new Configuration();
        configuration.Timeout = TimeSpan.fromMilliseconds(1000);
        configuration.Port = 4567;
        browser = new BrowserSession(configuration);

        //browser.visit("/");
    }

    @After
    public void tearDown()
    {
        browser.dispose();
        //sinatraSite.dispose();
    }

    @Test
    public void it_exposes_the_current_page_location() throws URISyntaxException, MissingHtmlException {
        browser.visit("/");
        assertThat(browser.location(), is(equalTo("http://localhost:4567/")));

        browser.visit("/auto_login");
        assertThat(browser.location(), is(equalTo("http://localhost:4567/auto_login")));
    }

    private void reloadTestPage()
    {
        browser.visit("file://localhost/Users/adrian/Documents/dev/coypu-jvm/src/test/Coypu.AcceptanceTests/html/InteractionTestsPage.htm");
    }

    @Test
    public void it_exposes_the_location_of_an_iframe_scope()  {
        reloadTestPage();
        assertThat(browser.findIFrame("iframe1",null).location().endsWith("iFrame1.htm"), is(true));
    }
}
