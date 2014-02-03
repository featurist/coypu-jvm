//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:19
//

package coypu.WebRequests;

import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.Net.CookieContainer;
import java.net.HttpURLConnection;

public class WebRequestCookieInjector   
{
    public ** NONE System.Net.WebRequest ** injectCookies(** NONE System.Net.WebRequest ** webRequest, IEnumerable<Cookie> cookies) throws Exception {
        ** NONE System.Net.WebRequest ** request = webRequest;
        return request instanceof HttpURLConnection ? addCookiesToCookieContainer((HttpURLConnection)request,cookies) : request;
    }

    public static HttpURLConnection addCookiesToCookieContainer(HttpURLConnection httpRequest, IEnumerable<Cookie> cookies) throws Exception {
        httpRequest.CookieContainer = new CookieContainer();
        for (Cookie cookie : cookies)
            httpRequest.CookieContainer.Add(cookie);
        return httpRequest;
    }

}


