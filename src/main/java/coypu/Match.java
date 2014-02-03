//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:17
//

package coypu;


public enum Match
{
    /**
    * With Match you can control how Coypu behaves when multiple elements match a query.
    *  {@code Match.First}
    *  just takes the first match and ignores any others
    *  {@code Match.Single}
    *  throws 
    *  {@code Coypu.AmbiguousException}
    *  if there is more than one match
    * Just picks the first element that matches
    */
    First,
    /**
    * Raises an error if more than one element matches
    */
    Single
}

