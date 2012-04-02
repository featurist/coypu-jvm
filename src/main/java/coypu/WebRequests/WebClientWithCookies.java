//package coypu.WebRequests;
//
//public class WebClientWithCookies extends WebClient implements RestrictedResourceDownloader
//{
//    private IEnumerable<Cookie> requestCookies;
//    private final WebRequestCookieInjector webRequestCookieInjector;
//
//    public WebClientWithCookies()
//    {
//        webRequestCookieInjector = new WebRequestCookieInjector();
//    }
//
//    public void setCookies(List<Cookie> cookies)
//    {
//        requestCookies = cookies;
//    }
//
//    protected WebRequest getWebRequest(Uri address)
//    {
//        return webRequestCookieInjector.injectCookies(base.getWebRequest(address), requestCookies);
//    }
//}
