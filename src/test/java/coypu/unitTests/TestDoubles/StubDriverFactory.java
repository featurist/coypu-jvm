package coypu.unitTests.TestDoubles;

import coypu.Driver;
import coypu.DriverFactory;

public class StubDriverFactory implements DriverFactory
{
    private final Driver driver;

    public StubDriverFactory(Driver driver)
    {
        this.driver = driver;
    }

    public Driver newWebDriver(Class driverType, coypu.Drivers.Browser browser)
    {
        return driver;
    }
}


