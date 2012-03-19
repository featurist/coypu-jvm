package Coypu.WebRequests;
public class WebRequestCookieInjector
{
    public WebRequest InjectCookies(WebRequest webRequest, Enumerable<Cookie> cookies)
    {
        WebRequest request = webRequest;

        return request is HttpWebRequest
                   ? AddCookiesToCookieContainer((HttpWebRequest) request, cookies)
                   : request;
    }

    public static HttpWebRequest AddCookiesToCookieContainer(HttpWebRequest httpRequest, Enumerable<Cookie> cookies)
    {
        httpRequest.CookieContainer = new CookieContainer();

        foreach (Cookie cookie in cookies)
            httpRequest.CookieContainer.Add(cookie);

        return httpRequest;
    }
}
