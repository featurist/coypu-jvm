package coypu;

import coypu.Drivers.Browser;
import coypu.Drivers.Selenium.SeleniumWebDriver;

/// <summary>
/// Global configuration settings
///
public class SessionConfiguration extends Options {
    final String DEFAULT_APP_HOST = "localhost";
    final int DEFAULT_PORT = 80;

    private String appHost;

   /**
    *  New default configuration
    */
    public SessionConfiguration() {
        appHost = DEFAULT_APP_HOST;
        Port = DEFAULT_PORT;
        SSL = false;
        Browser = coypu.Drivers.Browser.Firefox;
        Driver = SeleniumWebDriver.class;
    }

   /**
    *  Specifies the browser you would like to control
    *  <p>Default: Firefox
    */
    public Browser Browser;

   /**
    *  Specifies the driver you would like to use to control the browser
    *  <p>Default: SeleniumWebDriver
    */
    public Class Driver;


   /**
    *  The host of the website you are testing, e.g. 'github.com'
    *  <p>Default: localhost
    */
    public String getAppHost() {
        return appHost;
    }

    public void setAppHost(String value) {
        appHost = value == null ? null : value.replaceAll("/$", "");
    }


   /**
    *  The port of the website you are testing
    *  <p>Default: 80
    */
    public int Port;

   /**
    *  Whether to use the HTTPS protocol to connect to website you are testing
    *  <p>Default: false
    */
    public boolean SSL;
}
