package coypu;

public interface DriverFactory {
    Driver newWebDriver(Class driverType, coypu.Drivers.Browser browser);
}
