package coypu;

import java.lang.reflect.Type;

public interface DriverFactory {
    Driver newWebDriver(Class driverType, coypu.Drivers.Browser browser);
}
