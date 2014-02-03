//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:19
//

package coypu.WebRequests;

import CS2JNet.System.Collections.LCC.IEnumerable;

public interface RestrictedResourceDownloader   
{
    void setCookies(IEnumerable<Cookie> getBrowserCookies) throws Exception ;

    void downloadFile(String resource, String saveAs) throws Exception ;

}


