package Coypu;

import Coypu.Actions.Click;
import Coypu.Finders.ElementFinder;
import Coypu.Queries.ElementExistsQuery;
import Coypu.Queries.ElementMissingQuery;

public class  ElementScope extends DriverScope implements Element {
    public ElementScope(ElementFinder elementFinder, DriverScope outer) {
        super(elementFinder, outer);
    }

    public String getId() {
        return now().getId();
    }

    public String getText() {
        return now().getText();
    }

    public String getValue() {
        return now().getValue();
    }

    public String getName() {
        return now().getName();
    }

    public String getSelectedOption() {
        return now().getSelectedOption();
    }

    public boolean getSelected() {
        return now().getSelected();
    }

    public Object getNative() {
        return now().getNative();
    }

    public String getAttribute(String attributeName) {
        return now().getAttribute(attributeName);
    }

    public ElementScope click() {
        return click(configuration);
    }

    public ElementScope click(Options options) {
        retryUntilTimeout(new Click(this, driver, setOptions(options)));
        return this;
    }

    public boolean exists() {
        return exists(configuration);
    }

    public boolean exists(Options options) {
        return robustWrapper.robustly(new ElementExistsQuery(this, setOptions(options)));
    }

    public boolean missing() {
        return missing(configuration);
    }

    public boolean missing(Options options) {
        return robustWrapper.robustly(new ElementMissingQuery(this, setOptions(options)));
    }
}
