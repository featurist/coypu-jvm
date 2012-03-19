package Coypu.WebRequests;

public interface RestrictedResourceDownloader
{
    void SetCookies(Enumerable<Cookie> getBrowserCookies);
    void DownloadFile(String resource, String saveAs);
}