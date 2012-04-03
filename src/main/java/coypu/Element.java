package coypu;

/**
* An HTML element
*/
public interface Element {

   /**
    *  The value of the 'id' Attribute
    */
    String getId();

   /**
    *  The inner text of the element
    */
    String getText();

   /**
    *  The value of the 'value' Attribute
    */
    String getValue();

   /**
    *  The value of the 'name' Attribute
    */
    String getName();

   /**
    *  The selected option - applies to select elements only
    */
    String getSelectedOption();

   /**
    *  Whether the element is selected
    */
    boolean getSelected();

   /**
    *  The native element returned by your chosen driver
    */
    Object getNative();

   /**
    *  The attributes of the HTML element
    */
    String getAttribute(String attributeName);
}
