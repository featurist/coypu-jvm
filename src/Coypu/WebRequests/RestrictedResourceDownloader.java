package Coypu.WebRequests;

import java.util.List;

public interface RestrictedResourceDownloader
{
    void SetCookies(List<Cookie> getBrowserCookies);
    void DownloadFile(String resource, String saveAs);
}
