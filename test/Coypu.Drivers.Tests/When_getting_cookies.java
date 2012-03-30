package Coypu.Drivers.Tests;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;

public class When_getting_cookies extends DriverSpecs
{
    @Before
    public void SetUpCookies()
    {
        Driver().Visit("http://localhost:4567/resource/cookie_test");
        Driver().ExecuteScript("document.cookie = 'cookie1=; expires=Fri, 27 Jul 2001 02:47:11 UTC; '", Root());
        Driver().ExecuteScript("document.cookie = 'cookie1=; expires=Fri, 27 Jul 2001 02:47:11 UTC;  path=/resource'", Root());
        Driver().ExecuteScript("document.cookie = 'cookie2=; expires=Fri, 27 Jul 2001 02:47:11 UTC; '", Root());
        Driver().Visit("http://localhost:4567/resource/cookie_test");
    }

    @Test
    public void cookies_not_implemented() {
        fail("cookies are not implemented yet");
    }

//    @Test
//    public void Gets_all_the_session_cookies()
//    {
//        Driver().ExecuteScript("document.cookie = 'cookie1=value1; '", Root());
//        Driver().ExecuteScript("document.cookie = 'cookie2=value2; '", Root());
//
//        var cookies = Driver().GetBrowserCookies().ToArray();
//
//        assertThat(cookies.First(c => c.Name == "cookie1").Value(), is(equalTo("value1")));
//        assertThat(cookies.First(c => c.Name == "cookie2").Value(), is(equalTo("value2")));
//    }
//
//    @Test
//    public void Gets_all_the_persistent_cookies()
//    {
//        var expires = DateTime.UtcNow.AddDays(2);
//
//        Driver().ExecuteScript(string.Format("document.cookie = 'cookie1=value11; expires={0} '", expires.ToString("R")), Root());
//        Driver().ExecuteScript(string.Format("document.cookie = 'cookie2=value22; expires={0} '", expires.ToString("R")), Root());
//
//
//        var cookies = Driver().GetBrowserCookies().ToArray();
//
//        assertThat(cookies.First(c => c.Name == "cookie1").Value(), Is.EqualTo("value11"));
//        assertThat(cookies.First(c => c.Name == "cookie2").Value(), Is.EqualTo("value22"));
//    }
//
//    // Internet Explorer fails this test - cookie information with path isn't available,
//    // unless it's a persistent cookie that's been retrieved from the cache (and even then
//    // the path value seems to be wrong?)
//    @Test
//    public void Gets_the_cookie_path()
//
//    {
//        Driver().ExecuteScript("document.cookie = 'cookie1=value1; path=/resource'", Root());
//
//        var cookies = Driver().GetBrowserCookies().ToArray();
//
//        assertThat(cookies.First(c => c.Name == "cookie1").Path, Is.EqualTo("/resource"));
//    }
}