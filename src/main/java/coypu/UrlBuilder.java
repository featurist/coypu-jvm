package coypu;

public interface UrlBuilder {
    String getFullyQualifiedUrl(String virtualPath, SessionConfiguration sessionConfiguration);
}
