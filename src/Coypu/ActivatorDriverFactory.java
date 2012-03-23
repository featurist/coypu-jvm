package Coypu;

import Coypu.Drivers.Browser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.rmi.activation.Activator;

public class ActivatorDriverFactory implements DriverFactory
{
    public static int OpenDrivers;

    public Driver NewWebDriver(Class driverType, Coypu.Drivers.Browser browser)
    {
        try
        {
            Driver driver = (Driver) driverType.getDeclaredConstructor(Browser.class).newInstance(browser);

            OpenDrivers++;

            return driver;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
