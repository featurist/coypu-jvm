package coypu;

/**
 * Created with IntelliJ IDEA.
 * User: adrian
 * Date: 04/04/2012
 * Time: 08:36
 * To change this template use File | Settings | File Templates.
 */
public interface ElementScope extends Scope, Element {
    String getId();

    String getText();

    String getValue();

    String getName();

    String getSelectedOption();

    boolean getSelected();

    Object getNative();

    String getAttribute(String attributeName);

    DeferredElementScope click();

    DeferredElementScope click(Options options);

    DeferredElementScope hover();

    DeferredElementScope hover(Options options);

    boolean exists();

    boolean exists(Options options);

    boolean missing();

    boolean missing(Options options);

}

