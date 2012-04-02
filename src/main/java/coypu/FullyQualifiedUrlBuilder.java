package coypu;

import java.net.URI;
import java.net.URISyntaxException;

public class FullyQualifiedUrlBuilder implements UrlBuilder {
    public String getFullyQualifiedUrl(String virtualPath, SessionConfiguration sessionConfiguration) {
        try {
            if (new URI(virtualPath).getScheme() == null)
                return configured(virtualPath, sessionConfiguration);

            return virtualPath;
        } catch (URISyntaxException ex) {
            return configured(virtualPath, sessionConfiguration);
        }
    }

    private String configured(String virtualPath, SessionConfiguration sessionConfiguration) {
        if (virtualPath.startsWith("/"))
            virtualPath = virtualPath.substring(1);

        String scheme = sessionConfiguration.SSL ? "https" : "http";

        return sessionConfiguration.Port == 80
                ? String.format("%1$s://%2$s/%3$s", scheme, sessionConfiguration.getAppHost(), virtualPath)
                : String.format("%1$s://%2$s:%3$s/%4$s", scheme, sessionConfiguration.getAppHost(), sessionConfiguration.Port, virtualPath);
    }
}
