package Coypu.Drivers.Tests;

import Coypu.Driver;
import Coypu.Drivers.Tests.Sites.SinatraSite;
import org.junit.After;
import org.junit.Before;

public class AssmeblyTearDown
{
    private SinatraSite sinatraSite;

    @Before
    public void StartSinatra()
    {
        sinatraSite = new SinatraSite("..\\..\\..\\Coypu.AcceptanceTests\\sites\\site_with_secure_resources.rb");
    }

    @After
    public void TearDown()
    {
        sinatraSite.Dispose();

        Driver driver = Coypu.Drivers.Tests.DriverSpecs.Driver();
        if (driver != null && !driver.Disposed())
            driver.Dispose();
    }
}