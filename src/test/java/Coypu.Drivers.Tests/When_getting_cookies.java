package Coypu.Drivers.Tests;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;

public class When_getting_cookies extends DriverSpecs
{
    @Before
    public void setUpCookies()
    {
        driver().visit("http://localhost:4567/resource/cookie_test");
        driver().executeScript("document.cookie = 'cookie1=; expires=Fri, 27 Jul 2001 02:47:11 UTC; '", root());
        driver().executeScript("document.cookie = 'cookie1=; expires=Fri, 27 Jul 2001 02:47:11 UTC;  path=/resource'", root());
        driver().executeScript("document.cookie = 'cookie2=; expires=Fri, 27 Jul 2001 02:47:11 UTC; '", root());
        driver().visit("http://localhost:4567/resource/cookie_test");
    }

    @Test
    public void cookies_not_implemented() {
        fail("cookies are not implemented yet");
    }

//    @Test
//    public void gets_all_the_session_cookies()
//    {
//        driver().executeScript("document.cookie = 'cookie1=value1; '", root());
//        driver().executeScript("document.cookie = 'cookie2=value2; '", root());
//
//        var cookies = driver().getBrowserCookies().toArray();
//
//        assertThat(cookies.first(c => c.Name == "cookie1").Value(), is(equalTo("value1")));
//        assertThat(cookies.first(c => c.Name == "cookie2").Value(), is(equalTo("value2")));
//    }
//
//    @Test
//    public void gets_all_the_persistent_cookies()
//    {
//        var expires = DateTime.UtcNow.addDays(2);
//
//        driver().executeScript(string.format("document.cookie = 'cookie1=value11; expires={0} '", expires.toString("R")), root());
//        driver().executeScript(string.format("document.cookie = 'cookie2=value22; expires={0} '", expires.toString("R")), root());
//
//
//        var cookies = driver().getBrowserCookies().toArray();
//
//        assertThat(cookies.first(c => c.Name == "cookie1").Value(), Is.equalTo("value11"));
//        assertThat(cookies.first(c => c.Name == "cookie2").Value(), Is.equalTo("value22"));
//    }
//
//    // Internet Explorer fails this test - cookie information with path isn't available,
//    // unless it's a persistent cookie that's been retrieved from the cache (and even then
//    // the path Value seems to be wrong?)
//    @Test
//    public void gets_the_cookie_path()
//
//    {
//        driver().executeScript("document.cookie = 'cookie1=value1; path=/resource'", root());
//
//        var cookies = driver().getBrowserCookies().toArray();
//
//        assertThat(cookies.first(c => c.name == "cookie1").Path, Is.equalTo("/resource"));
//    }
}