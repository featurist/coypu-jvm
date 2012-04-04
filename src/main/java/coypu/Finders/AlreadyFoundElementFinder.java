package coypu.Finders;

import coypu.ElementFound;

public class AlreadyFoundElementFinder implements ElementFinder {
    private ElementFound found;

    public AlreadyFoundElementFinder(ElementFound alreadyFound) {
        this.found = alreadyFound;
    }

    @Override
    public ElementFound find() {
        return found;
    }
}
