package coypu;

import coypu.Drivers.Browser;

import java.lang.reflect.InvocationTargetException;

public class ActivatorDriverFactory implements DriverFactory {
    public static int OpenDrivers;

    public Driver newWebDriver(Class driverType, coypu.Drivers.Browser browser) {
        try {
            Driver driver = (Driver) driverType.getDeclaredConstructor(Browser.class).newInstance(browser);

            OpenDrivers++;

            return driver;
        } catch (NoSuchMethodException e) {
            throw new DriverStartupException(e);
        } catch (InstantiationException e) {
            throw new DriverStartupException(e);
        } catch (IllegalAccessException e) {
            throw new DriverStartupException(e);
        } catch (InvocationTargetException e) {
            throw new DriverStartupException(e);
        }
    }
}
