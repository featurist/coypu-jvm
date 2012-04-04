package coypu;

import coypu.Actions.Click;
import coypu.Finders.ElementFinder;
import coypu.Queries.ElementExistsQuery;
import coypu.Queries.ElementMissingQuery;

public class DeferredElementScope extends DriverScope implements ElementScope {
    public DeferredElementScope(ElementFinder elementFinder, DriverScope outer) {
        super(elementFinder, outer);
    }

    @Override
    public String getId() {
        return now().getId();
    }

    @Override
    public String getText() {
        return now().getText();
    }

    @Override
    public String getValue() {
        return now().getValue();
    }

    @Override
    public String getName() {
        return now().getName();
    }

    @Override
    public String getSelectedOption() {
        return now().getSelectedOption();
    }

    @Override
    public boolean getSelected() {
        return now().getSelected();
    }

    @Override
    public Object getNative() {
        return now().getNative();
    }

    @Override
    public String getAttribute(String attributeName) {
        return now().getAttribute(attributeName);
    }

    @Override
    public DeferredElementScope click() {
        return click(sessionConfiguration);
    }

    @Override
    public DeferredElementScope click(Options options) {
        retryUntilTimeout(new Click(this, driver, setOptions(options)));
        return this;
    }

    @Override
    public boolean exists() {
        return exists(sessionConfiguration);
    }

    @Override
    public boolean exists(Options options) {
        return robustWrapper.robustly(new ElementExistsQuery(this, setOptions(options)));
    }

    @Override
    public boolean missing() {
        return missing(sessionConfiguration);
    }

    @Override
    public boolean missing(Options options) {
        return robustWrapper.robustly(new ElementMissingQuery(this, setOptions(options)));
    }
}
