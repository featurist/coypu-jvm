package Coypu.Tests.TestDoubles;

import Coypu.Driver;
import Coypu.DriverFactory;

public class StubDriverFactory implements DriverFactory
{
    private final Driver driver;

    public StubDriverFactory(Driver driver)
    {
        this.driver = driver;
    }

    public Driver newWebDriver(Class driverType, Coypu.Drivers.Browser browser)
    {
        return driver;
    }
}


