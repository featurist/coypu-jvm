package Coypu;

import java.lang.reflect.Type;

public interface DriverFactory
{
    Driver NewWebDriver(Type driverType, Coypu.Drivers.Browser browser);
}
