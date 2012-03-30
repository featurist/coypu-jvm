package Coypu.Tests.TestDoubles;

import Coypu.WebRequests.Cookie;

import java.util.List;

public class DownloadedFile
{
    public String resource;
    public String saveAs;
    public List<Cookie> cookies;

    public DownloadedFile(String resource, String saveAs, List<Cookie> cookies)
    {
        this.resource = resource;
        this.saveAs = saveAs;
        this.cookies = cookies;
    }
}
