package Coypu.Tests.TestDoubles;

import Coypu.Configuration;
import Coypu.UrlBuilder;

import java.util.Dictionary;
import java.util.Hashtable;

public class StubUrlBuilder implements UrlBuilder
{
    private final Dictionary<String, String> urls = new Hashtable<String, String>();

    public String GetFullyQualifiedUrl(String virtualPath, Configuration configuration)
    {
        return urls.get(virtualPath);
    }

    public void SetStubUrl(String virtualPath, String url)
    {
        urls.put(virtualPath, url);
    }
}
