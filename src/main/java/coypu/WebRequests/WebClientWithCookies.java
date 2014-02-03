//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:19
//

package coypu.WebRequests;

import coypu.WebRequests.RestrictedResourceDownloader;
import coypu.WebRequests.WebRequestCookieInjector;
import CS2JNet.System.Collections.LCC.IEnumerable;
import java.net.URI;

public class WebClientWithCookies  extends WebClient implements RestrictedResourceDownloader
{
    private IEnumerable<Cookie> requestCookies;
    private final WebRequestCookieInjector webRequestCookieInjector;
    public WebClientWithCookies() throws Exception {
        webRequestCookieInjector = new WebRequestCookieInjector();
    }

    public void setCookies(IEnumerable<Cookie> cookies) throws Exception {
        requestCookies = cookies;
    }

    protected ** NONE System.Net.WebRequest ** getWebRequest(URI address) throws Exception {
        return webRequestCookieInjector.InjectCookies(super.GetWebRequest(address), requestCookies);
    }

}


