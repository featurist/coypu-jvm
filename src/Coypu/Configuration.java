package Coypu;

import Coypu.Drivers.Browser;
import Coypu.Drivers.Selenium.SeleniumWebDriver;

import java.lang.reflect.Type;

/// <summary>
/// Global configuration settings
/// </summary>
public class Configuration extends Options
{
    final String DEFAULT_APP_HOST = "localhost";
    final int DEFAULT_PORT = 80;
    
    private String appHost;

    /// <summary>
    /// New default configuration
    /// </summary>
    public Configuration()
    {
        appHost = DEFAULT_APP_HOST;
        Port = DEFAULT_PORT;
        SSL = false;
        Browser = Coypu.Drivers.Browser.Firefox;
        Driver = SeleniumWebDriver.class;
    }

    /// <summary>
    /// <para>Specifies the browser you would like to control</para>
    /// <para>Default: Firefox</para>
    /// </summary>
    public Browser Browser;

    /// <summary>
    /// <para>Specifies the driver you would like to use to control the browser</para> 
    /// <para>Default: SeleniumWebDriver</para>
    /// </summary>
    public Class Driver;


    /// <summary>
    /// <para>The host of the website you are testing, e.g. 'github.com'</para>
    /// <para>Default: localhost</para>
    /// </summary>
    public String GetAppHost()
    {
        return appHost;
    }

    public void SetAppHost(String value)
    {
        appHost = value == null ? null : value.replaceAll("/$", "");
    }


    /// <summary>
    /// <para>The port of the website you are testing</para>
    /// <para>Default: 80</para>
    /// </summary>
    public int Port;

    /// <summary>
    /// <para>Whether to use the HTTPS protocol to connect to website you are testing</para>
    /// <para>Default: false</para>
    /// </summary>
    public boolean SSL;
}
