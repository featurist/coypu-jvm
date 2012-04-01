package Coypu.Drivers.Tests;

import Coypu.ActivatorDriverFactory;
import Coypu.Configuration;
import Coypu.Driver;
import Coypu.DriverScope;
import Coypu.Drivers.Browser;
import Coypu.Drivers.Selenium.SeleniumWebDriver;
import Coypu.Drivers.Tests.Sites.SinatraSite;
import Coypu.Finders.DocumentElementFinder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class DriverSpecs
{
    private static DriverScope root;
    private static Coypu.Driver driver;

    private final static Browser browser = Browser.Firefox;
    private static Class driverType = SeleniumWebDriver.class;
    private static SinatraSite sinatraSite;

    @Before
    public void setUp()
    {
        driver().visit(getTestHTMLPathLocation());
    }

    @BeforeClass
    public static void startSinatra()
    {
        sinatraSite = new SinatraSite("..\\..\\..\\Coypu.AcceptanceTests\\sites\\site_with_secure_resources.rb");
    }

    @AfterClass
    public static void tearDown()
    {
        sinatraSite.dispose();

        Driver driver = driver();
        if (driver != null && !driver.disposed())
            driver.dispose();
    }

    private String getTestHTMLPathLocation()
    {
        return "file://localhost/Users/adrian/Documents/dev/coypu-jvm/src/test/Coypu.Drivers.Tests/html/InteractionTestsPage.htm";
    }

    protected DriverScope root()
    {
        if (root != null)
            return root;

        root = new DriverScope(new Configuration(), new DocumentElementFinder(driver()), null, null, null, null);
        return root;
    }

    private static void ensureDriver()
    {
        if (driver != null && !driver.disposed())
        {
            if (driverType == driver.getClass())
                return;

            driver.dispose();
        }


        driver = new ActivatorDriverFactory().newWebDriver(driverType,browser);
        root = null;
    }

    public static Driver driver()
    {
        ensureDriver();
        return driver;
    }
}
