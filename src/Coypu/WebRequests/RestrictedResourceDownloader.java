package Coypu.WebRequests;

public interface RestrictedResourceDownloader
{
    void SetCookies(Iterable<Cookie> getBrowserCookies);
    void DownloadFile(String resource, String saveAs);
}