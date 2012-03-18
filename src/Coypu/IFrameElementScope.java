package Coypu;

import Coypu.Finders.ElementFinder;
import Coypu.Robustness.RobustWrapper;
import com.sun.jndi.toolkit.url.Uri;

public class IFrameElementScope extends RobustElementScope
{
    public IFrameElementScope(ElementFinder elementFinder, DriverScope outerScope, RobustWrapper robustWrapper, Options options)
    {
        super(elementFinder, outerScope, robustWrapper,options);
    }

    public Uri Location() throws MissingHtmlException
    {
        FindXPath("/*",options).Now();
        return driver.Location();
    }
}