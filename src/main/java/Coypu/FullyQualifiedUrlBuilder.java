package Coypu;

import java.net.URI;
import java.net.URISyntaxException;

public class FullyQualifiedUrlBuilder implements UrlBuilder {
    public String getFullyQualifiedUrl(String virtualPath, Configuration configuration) {
        try {
            if (new URI(virtualPath).getScheme() == null)
                return configured(virtualPath, configuration);

            return virtualPath;
        } catch (URISyntaxException ex) {
            return configured(virtualPath, configuration);
        }
    }

    private String configured(String virtualPath, Configuration configuration) {
        if (virtualPath.startsWith("/"))
            virtualPath = virtualPath.substring(1);

        String scheme = configuration.SSL ? "https" : "http";

        return configuration.Port == 80
                ? String.format("%1$s://%2$s/%3$s", scheme, configuration.getAppHost(), virtualPath)
                : String.format("%1$s://%2$s:%3$s/%4$s", scheme, configuration.getAppHost(), configuration.Port, virtualPath);
    }
}
