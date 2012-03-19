package Coypu.Drivers;

import java.lang.reflect.Type;

/// <summary>
/// Thrown when your chosen browser is not supported by your chosen driver
/// </summary>
public class BrowserNotSupportedException extends Exception
{
    public BrowserNotSupportedException(Browser browser, Type driverType)
    {
        super(String.format("{0} is not supported by {1}", browser, driverType.Name));
    }
}
