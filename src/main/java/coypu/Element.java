package coypu;

/// <summary>
/// An HTML element
/// </summary>
public interface Element {

    /// <summary>
    /// The value of the 'id' Attribute
    /// </summary>
    String getId();

    /// <summary>
    /// The inner text of the element
    /// </summary>
    String getText();

    /// <summary>
    /// The value of the 'value' Attribute
    /// </summary>
    String getValue();

    /// <summary>
    /// The value of the 'name' Attribute
    /// </summary>
    String getName();

    /// <summary>
    /// The selected option - applies to select elements only
    /// </summary>
    String getSelectedOption();

    /// <summary>
    /// Whether the element is selected
    /// </summary>
    boolean getSelected();

    /// <summary>
    /// The native element returned by your chosen driver
    /// </summary>
    Object getNative();

    /// <summary>
    /// The attributes of the HTML element
    /// </summary>
    String getAttribute(String attributeName);
}
