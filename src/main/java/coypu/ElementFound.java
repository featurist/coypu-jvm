//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:16
//

package coypu;

import coypu.Element;
import coypu.Options;

/**
* An element found by a Coypu Driver
*/
public interface ElementFound   extends Element
{
    /**
    * The native element is no longer attached to the DOM
    */
    boolean stale(Options options) throws Exception ;

}


