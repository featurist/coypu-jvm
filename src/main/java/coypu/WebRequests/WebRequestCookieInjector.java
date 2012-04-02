//package coypu.WebRequests;
//public class WebRequestCookieInjector
//{
//    public WebRequest injectCookies(WebRequest webRequest, List<Cookie> cookies)
//    {
//        WebRequest request = webRequest;
//
//        return request is HttpWebRequest
//                   ? addCookiesToCookieContainer((HttpWebRequest) request, cookies)
//                   : request;
//    }
//
//    public static HttpWebRequest addCookiesToCookieContainer(HttpWebRequest httpRequest, List<Cookie> cookies)
//    {
//        httpRequest.CookieContainer = new CookieContainer();
//
//        foreach (Cookie cookie in cookies)
//            httpRequest.CookieContainer.add(cookie);
//
//        return httpRequest;
//    }
//}
