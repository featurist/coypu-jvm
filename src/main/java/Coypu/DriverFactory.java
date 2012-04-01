package Coypu;

import java.lang.reflect.Type;

public interface DriverFactory {
    Driver newWebDriver(Class driverType, Coypu.Drivers.Browser browser);
}
