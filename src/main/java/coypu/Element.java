//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:16
//

package coypu;


/**
* An HTML element
*/
public interface Element   
{
    /**
    * The value of the 'id' attribute
    */
    String getId() throws Exception ;

    /**
    * The inner text of the element
    */
    String getText() throws Exception ;

    /**
    * The value of the 'value' attribute
    */
    String getValue() throws Exception ;

    /**
    * The value of the 'name' attribute
    */
    String getName() throws Exception ;

    /**
    * The selected option - applies to select elements only
    */
    String getSelectedOption() throws Exception ;

    /**
    * Whether the element is selected
    */
    boolean getSelected() throws Exception ;

    /**
    * The native element returned by your chosen driver
    */
    Object getNative() throws Exception ;

    /**
    * The attributes of the HTML element
    */
    String get___idx(String attributeName) throws Exception ;

    /**
    * The outer HTML of the element
    */
    String getOuterHTML() throws Exception ;

    /**
    * The inner HTML of the element
    */
    String getInnerHTML() throws Exception ;

    /**
    * The title of the element
    */
    String getTitle() throws Exception ;

}


