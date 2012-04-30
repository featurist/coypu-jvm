package coypu.driverTests;

import coypu.ActivatorDriverFactory;
import coypu.SessionConfiguration;
import coypu.Driver;
import coypu.DriverScope;
import coypu.Drivers.Browser;
import coypu.Drivers.Selenium.SeleniumWebDriver;
import coypu.acceptanceTests.ApiExamples;
import coypu.driverTests.Sites.SinatraSite;
import coypu.Finders.DocumentElementFinder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class DriverSpecs
{
    private static DriverScope root;
    private static coypu.Driver driver;

    private final static Browser browser = Browser.Firefox;
    private static Class driverType = SeleniumWebDriver.class;
    private static SinatraSite sinatraSite;

    @Before
    public void setUp()
    {
        driver().visit(testPage());
    }

    protected String testPage() {
        return ApiExamples.InteractionTestsPage();
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

    protected DriverScope root()
    {
        if (root != null)
            return root;

        root = new DriverScope(new SessionConfiguration(), new DocumentElementFinder(driver()), null, null, null, null);
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
