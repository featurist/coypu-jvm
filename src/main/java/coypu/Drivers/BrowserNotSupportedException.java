package coypu.Drivers;

import java.lang.reflect.Type;

/// <summary>
/// Thrown when your chosen browser is not supported by your chosen driver
///
public class BrowserNotSupportedException extends Exception {
    public BrowserNotSupportedException(Browser browser, Type driverType,Throwable inner) {
        super(String.format("%1$s is not supported by %2$s", browser.toString(), driverType.toString()),inner);
    }
}
