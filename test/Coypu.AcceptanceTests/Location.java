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
    public void SetUp()
    {
        //sinatraSite = new SinatraSite(String.format("sites\\%1$s.rb", "site_with_secure_resources"));

        Configuration configuration = new Configuration();
        configuration.Timeout = TimeSpan.FromMilliseconds(1000);
        //configuration.Port = 4567;
        browser = new BrowserSession(configuration);

        //browser.Visit("/");
    }

    @After
    public void TearDown()
    {
        browser.Dispose();
        //sinatraSite.Dispose();
    }

    @Test
    public void It_exposes_the_current_page_location() throws URISyntaxException, MissingHtmlException {
        browser.Visit("/");
        assertThat(browser.Location(), is(equalTo("http://localhost:4567/")));

        browser.Visit("/auto_login");
        assertThat(browser.Location(), is(equalTo("http://localhost:4567/auto_login")));
    }

    private void ReloadTestPage()
    {
        browser.Visit("file://localhost/Users/adrian/Documents/dev/coypu.java/test/Coypu.AcceptanceTests/html/InteractionTestsPage.htm");
    }

    @Test
    public void It_exposes_the_location_of_an_iframe_scope()  {
        ReloadTestPage();
        assertThat(browser.FindIFrame("iframe1",null).Location().endsWith("iFrame1.htm"), is(true));
    }
}
