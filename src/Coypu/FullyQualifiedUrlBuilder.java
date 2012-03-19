package Coypu;

public class FullyQualifiedUrlBuilder implements UrlBuilder
{
    public String GetFullyQualifiedUrl(String virtualPath, Configuration configuration)
    {
        if (Uri.IsWellFormedUriString(virtualPath, UriKind.Absolute))
            return virtualPath;

        virtualPath = virtualPath.replaceFirst("/","");
        String scheme = configuration.SSL ? "https" : "http";

        return configuration.Port == 80
                   ? String.format("{0}://{1}/{2}", scheme, configuration.GetAppHost(), virtualPath)
                   : String.format("{0}://{1}:{2}/{3}", scheme, configuration.GetAppHost(), configuration.Port, virtualPath);
    }
}
