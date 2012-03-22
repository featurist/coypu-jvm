package Coypu.WebRequests;

public class WebClientWithCookies extends WebClient implements RestrictedResourceDownloader
{
    private IEnumerable<Cookie> requestCookies;
    private final WebRequestCookieInjector webRequestCookieInjector;

    public WebClientWithCookies()
    {
        webRequestCookieInjector = new WebRequestCookieInjector();
    }

    public void SetCookies(Iterable<Cookie> cookies)
    {
        requestCookies = cookies;
    }

    protected WebRequest GetWebRequest(Uri address)
    {
        return webRequestCookieInjector.InjectCookies(base.GetWebRequest(address), requestCookies);
    }
}
