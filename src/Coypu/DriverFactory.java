package Coypu;

import java.lang.reflect.Type;

public interface DriverFactory
{
    Driver NewWebDriver(Class driverType, Coypu.Drivers.Browser browser);
}
