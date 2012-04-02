package coypu.unitTests.TestDoubles;

import coypu.SessionConfiguration;
import coypu.UrlBuilder;

import java.util.Dictionary;
import java.util.Hashtable;

public class StubUrlBuilder implements UrlBuilder
{
    private final Dictionary<String, String> urls = new Hashtable<String, String>();

    public String getFullyQualifiedUrl(String virtualPath, SessionConfiguration sessionConfiguration)
    {
        return urls.get(virtualPath);
    }

    public void setStubUrl(String virtualPath, String url)
    {
        urls.put(virtualPath, url);
    }
}
