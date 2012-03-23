package Coypu;

import java.net.URI;
import java.net.URISyntaxException;

public class FullyQualifiedUrlBuilder implements UrlBuilder
{
    public String GetFullyQualifiedUrl(String virtualPath, Configuration configuration)
    {
        try {
            new URI(virtualPath);
            return virtualPath;
        }
        catch (URISyntaxException ex) {
            virtualPath = virtualPath.replaceFirst("/","");
            String scheme = configuration.SSL ? "https" : "http";

            return configuration.Port == 80
                    ? String.format("{0}://{1}/{2}", scheme, configuration.GetAppHost(), virtualPath)
                    : String.format("{0}://{1}:{2}/{3}", scheme, configuration.GetAppHost(), configuration.Port, virtualPath);
        }
    }
}
