package Coypu.Tests.TestDoubles;

import Coypu.WebRequests.Cookie;
import Coypu.WebRequests.RestrictedResourceDownloader;

import java.util.ArrayList;
import java.util.List;

public class SpyRestrictedResourceDownloader implements RestrictedResourceDownloader
{
    private final List<DownloadedFile> downloadedFiles = new ArrayList<DownloadedFile>();
    private List<Cookie> cookies;

    public List<DownloadedFile> downloadedFiles()
    {
        return downloadedFiles;
    }

    public void setCookies(List<Cookie> cookies)
    {
        this.cookies = cookies;
    }

    public List<Cookie> cookies() {
        return cookies;
    }

    public void downloadFile(String resource, String saveAs)
    {
        downloadedFiles.add(new DownloadedFile(resource, saveAs, cookies));
    }
}

