



package Coypu.Drivers.Tests
{
    class When_selecting_options extends DriverSpecs
    {
            
  @Test
  public void Sets_text_of_selected_option()

            {
                Element textField = Driver().FindField("containerLabeledSelectFieldId", Root());
                textField.SelectedOption, is(equalTo("select two option one");

                Driver().Select(textField, "select2value2");

                textField = Driver().FindField("containerLabeledSelectFieldId", Root());
                textField.SelectedOption, is(equalTo("select two option two");
            }
        }
    
}