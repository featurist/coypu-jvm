package Coypu.WebRequests;
public interface RequestCookieInjector
{
    WebRequest InjectCookies(WebRequest httpRequest, Enumerable<Cookie> enumerable);
}
