//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:17
//

package coypu;

import coypu.SessionConfiguration;
import coypu.UrlBuilder;
import CS2JNet.System.StringSupport;
import java.net.URI;

public class FullyQualifiedUrlBuilder   implements UrlBuilder
{
    public String getFullyQualifiedUrl(String virtualPath, SessionConfiguration SessionConfiguration) throws Exception {
        if (URI.IsWellFormedUriString(virtualPath, UriKind.Absolute))
            return virtualPath;
         
        virtualPath = StringSupport.TrimStart(virtualPath, new char[] {'/'});
        String scheme = SessionConfiguration.getSSL() ? "https" : "http";
        return SessionConfiguration.getPort() == 80 ? String.format(StringSupport.CSFmtStrToJFmtStr("{0}://{1}/{2}"),scheme,SessionConfiguration.getAppHost(),virtualPath) : String.Format("{0}://{1}:{2}/{3}", scheme, SessionConfiguration.getAppHost(), SessionConfiguration.getPort(), virtualPath);
    }

}


