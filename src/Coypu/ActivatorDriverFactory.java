package Coypu;

import java.lang.reflect.Type;
import java.rmi.activation.Activator;

public class ActivatorDriverFactory implements DriverFactory
{
    public static int OpenDrivers;

    public Driver NewWebDriver(Type driverType, Coypu.Drivers.Browser browser)
    {
        try
        {
            Driver driver = (Driver) Activator.CreateInstance(driverType, browser);

            OpenDrivers++;

            return driver;
        }
        catch (TargetInvocationException e)
        {
            throw e.InnerException;
        }
    }
}
