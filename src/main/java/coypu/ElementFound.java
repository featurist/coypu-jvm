package coypu;

/**
* An element found by a coypu Driver
*/
public interface ElementFound extends Element {
   /**
    *  The native element is no longer attached to the DOM
    */
    public boolean stale();
}
