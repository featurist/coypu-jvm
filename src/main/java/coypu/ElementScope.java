//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:13
//

package coypu;

import coypu.Actions.CheckAction;
import coypu.Actions.ClickAction;
import coypu.Actions.DriverAction;
import coypu.Actions.FillIn;
import coypu.Actions.Hover;
import coypu.Actions.SendKeys;
import coypu.Actions.Uncheck;
import coypu.DriverScope;
import coypu.Element;
import coypu.ElementFound;
import coypu.ElementScope;
import coypu.Finders.ElementFinder;
import coypu.Options;
import coypu.Queries.ElementExistsQuery;
import coypu.Queries.ElementMissingQuery;
import coypu.Queries.HasNoValueQuery;
import coypu.Queries.HasValueQuery;
import coypu.Queries.Query;

public abstract class ElementScope  extends DriverScope implements Element
{
    public ElementScope(ElementFinder elementFinder, DriverScope outerScope) throws Exception {
        super(elementFinder, outerScope);
    }

    public abstract void try(DriverAction action) throws Exception ;

    public abstract boolean try(Query<Boolean> query) throws Exception ;

    public String getId() throws Exception {
        return now().getId();
    }

    public String getText() throws Exception {
        return now().getText();
    }

    public String getValue() throws Exception {
        return now().getValue();
    }

    public String getName() throws Exception {
        return now().getName();
    }

    public String getOuterHTML() throws Exception {
        return now().getOuterHTML();
    }

    public String getInnerHTML() throws Exception {
        return now().getInnerHTML();
    }

    public String getTitle() throws Exception {
        return now().getTitle();
    }

    public String getSelectedOption() throws Exception {
        return now().getSelectedOption();
    }

    public boolean getSelected() throws Exception {
        return now().getSelected();
    }

    public Object getNative() throws Exception {
        return now().getNative();
    }

    public String get___idx(String attributeName) throws Exception {
        ElementFound elementFound = now();
        return elementFound.get___idx(attributeName);
    }

    public ElementScope click(Options options) throws Exception {
        try(new ClickAction(this, driver, merge(options)));
        return this;
    }

    public ElementScope fillInWith(String value, Options options) throws Exception {
        try(new FillIn(driver, this, value, merge(options)));
        return this;
    }

    public ElementScope hover(Options options) throws Exception {
        try(new Hover(this, driver, merge(options)));
        return this;
    }

    public ElementScope sendKeys(String keys, Options options) throws Exception {
        try(new SendKeys(keys, this, driver, merge(options)));
        return this;
    }

    public ElementScope check(Options options) throws Exception {
        try(new CheckAction(driver, this, merge(options)));
        return this;
    }

    public ElementScope uncheck(Options options) throws Exception {
        try(new Uncheck(driver, this, merge(options)));
        return this;
    }

    public boolean exists() throws Exception {
        return try(new ElementExistsQuery(this));
    }

    public boolean missing() throws Exception {
        return try(new ElementMissingQuery(this));
    }

    public boolean hasValue(String text, Options options) throws Exception {
        return try(new HasValueQuery(this, text, merge(options)));
    }

    public boolean hasNoValue(String text, Options options) throws Exception {
        return try(new HasNoValueQuery(this, text, merge(options)));
    }

}


