//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu;

import coypu.Drivers.Browser;
import coypu.Drivers.Selenium.SeleniumWebDriver;
import coypu.Options;
import CS2JNet.System.StringSupport;
import java.net.URI;

/**
* Global configuration settings
*/
public class SessionConfiguration  extends Options 
{
    static final String DEFAULT_APP_HOST = "localhost";
    static final int DEFAULT_PORT = 80;
    private String appHost;
    /**
    * New default configuration
    */
    public SessionConfiguration() throws Exception {
        setAppHost(DEFAULT_APP_HOST);
        setPort(DEFAULT_PORT);
        setSSL(false);
        setBrowser(Browser.Firefox);
        setDriver(SeleniumWebDriver.class);
    }

    /**
    * Specifies the browser you would like to controlDefault: Firefox
    */
    private Browser __Browser;
    public Browser getBrowser() {
        return __Browser;
    }

    public void setBrowser(Browser value) {
        __Browser = value;
    }

    /**
    * Specifies the driver you would like to use to control the browserDefault: SeleniumWebDriver
    */
    private Class __Driver;
    public Class getDriver() {
        return __Driver;
    }

    public void setDriver(Class value) {
        __Driver = value;
    }

    /**
    * The host of the website you are testing, e.g. 'github.com'Default: localhost
    */
    public String getAppHost() throws Exception {
        return appHost;
    }

    public void setAppHost(String value) throws Exception {
        if (URI.IsWellFormedUriString(value, UriKind.Absolute))
        {
            Uri uri = new URI(value);
            setSSL(StringSupport.equals(uri.Scheme, "https"));
            value = uri.Host;
        }
         
        appHost = value == null ? null : StringSupport.TrimEnd(value, new char[] {'/'});
    }

    /**
    * The port of the website you are testingDefault: 80
    */
    private int __Port;
    public int getPort() {
        return __Port;
    }

    public void setPort(int value) {
        __Port = value;
    }

    /**
    * Whether to use the HTTPS protocol to connect to website you are testingDefault: false
    */
    private boolean __SSL;
    public boolean getSSL() {
        return __SSL;
    }

    public void setSSL(boolean value) {
        __SSL = value;
    }

}


