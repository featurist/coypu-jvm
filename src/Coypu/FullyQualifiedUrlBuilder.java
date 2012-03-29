package Coypu;

import java.net.URI;
import java.net.URISyntaxException;

public class FullyQualifiedUrlBuilder implements UrlBuilder {
    public String GetFullyQualifiedUrl(String virtualPath, Configuration configuration) {
        try {
            new URI(virtualPath);
            return virtualPath;
        } catch (URISyntaxException ex) {
            virtualPath = virtualPath.replaceFirst("/", "");
            String scheme = configuration.SSL ? "https" : "http";

            return configuration.Port == 80
                    ? String.format("%1$s://%2$s/%3$s", scheme, configuration.GetAppHost(), virtualPath)
                    : String.format("%1$s://%2$s:%3$s/%4$s", scheme, configuration.GetAppHost(), configuration.Port, virtualPath);
        }
    }
}
