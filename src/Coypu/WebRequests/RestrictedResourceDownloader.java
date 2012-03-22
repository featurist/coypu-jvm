package Coypu.WebRequests;

import org.openqa.selenium.Cookie;

public interface RestrictedResourceDownloader
{
    void SetCookies(Iterable<Cookie> getBrowserCookies);
    void DownloadFile(String resource, String saveAs);
}