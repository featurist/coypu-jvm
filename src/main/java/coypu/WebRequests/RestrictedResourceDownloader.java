package coypu.WebRequests;

import java.util.List;

public interface RestrictedResourceDownloader
{
    void setCookies(List<Cookie> getBrowserCookies);
    void downloadFile(String resource, String saveAs);
}
