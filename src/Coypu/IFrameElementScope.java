package Coypu;

import Coypu.Finders.ElementFinder;
import Coypu.Robustness.RobustWrapper;
import com.sun.jndi.toolkit.url.Uri;

import java.net.URI;

public class IFrameElementScope extends RobustElementScope
{
    public IFrameElementScope(ElementFinder elementFinder, DriverScope outerScope, Options options)
    {
        super(elementFinder, outerScope, options);
    }

    public String Location() throws MissingHtmlException {
        FindXPath("/*",options).Now();
        return driver.Location();
    }
}
