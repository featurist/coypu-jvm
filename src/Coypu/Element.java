package Coypu  ;
/// <summary>
/// An HTML element
/// </summary>
public interface Element {

    /// <summary>
    /// The value of the 'id' attribute
    /// </summary>
    String Id();
    /// <summary>
    /// The inner text of the element
    /// </summary>
    String Text();
    /// <summary>
    /// The value of the 'value' attribute
    /// </summary>
    String Value();
    /// <summary>
    /// The value of the 'name' attribute
    /// </summary>
    String Name();
    /// <summary>
    /// The selected option - applies to select elements only
    /// </summary>
    String SelectedOption();
    /// <summary>
    /// Whether the element is selected
    /// </summary>
    boolean Selected();
    /// <summary>
    /// The native element returned by your chosen driver
    /// </summary>
    Object Native();

    /// <summary>
    /// The attributes of the HTML element
    /// </summary>
    String Attribute(String attributeName);
}
