package Coypu.Drivers.Tests;

import Coypu.ActivatorDriverFactory;
import Coypu.Configuration;
import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.Drivers.Browser;
import Coypu.Drivers.Selenium.SeleniumWebDriver;
import Coypu.Finders.DocumentElementFinder;
import org.junit.Before;

public class DriverSpecs
{
    private static DriverScope root;
    private static Coypu.Driver driver;

    private final static Browser browser = Browser.Firefox;
    private static Class driverType = SeleniumWebDriver.class;

    @Before
    public void SetUp()
    {
        Driver().Visit(GetTestHTMLPathLocation());
    }

    private String GetTestHTMLPathLocation()
    {
        return "file://localhost/Users/adrian/Documents/dev/coypu.java/test/Coypu.Drivers.Tests/html/InteractionTestsPage.htm";
    }

    protected DriverScope Root()
    {
        if (root != null)
            return root;

        root = new DriverScope(new Configuration(), new DocumentElementFinder(Driver()), null, null, null, null);
        return root;
    }

    private static void EnsureDriver()
    {
        if (driver != null && !driver.Disposed())
        {
            if (driverType == driver.getClass())
                return;

            driver.Dispose();
        }


        driver = new ActivatorDriverFactory().NewWebDriver(driverType,browser);
        root = null;
    }

    public static Driver Driver()
    {
        EnsureDriver();
        return driver;
    }
}
