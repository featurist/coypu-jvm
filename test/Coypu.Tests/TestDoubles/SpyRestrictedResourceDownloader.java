package Coypu.Tests.TestDoubles;

import Coypu.WebRequests.Cookie;
import Coypu.WebRequests.RestrictedResourceDownloader;

import java.util.ArrayList;
import java.util.List;

public class SpyRestrictedResourceDownloader implements RestrictedResourceDownloader
{
    private final List<DownloadedFile> downloadedFiles = new ArrayList<DownloadedFile>();
    private List<Cookie> cookies;

    public List<DownloadedFile> DownloadedFiles()
    {
        return downloadedFiles;
    }

    public void SetCookies(List<Cookie> cookies)
    {
        this.cookies = cookies;
    }

    public List<Cookie> Cookies() {
        return cookies;
    }

    public void DownloadFile(String resource, String saveAs)
    {
        downloadedFiles.add(new DownloadedFile(resource, saveAs, cookies));
    }
}

