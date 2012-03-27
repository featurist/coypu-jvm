//
//
//
//package Coypu.Drivers.Tests
//{
//    class When_setting_fields extends DriverSpecs
//    {
//
//  @Test
//  public void Sets_value_of_text_input_field()
//
//            {
//                Element textField = Driver().FindField("containerLabeledTextInputFieldName", Root());
//                Driver().Set(textField, "New text input value");
//
//                textField.Value, is(equalTo("New text input value");
//
//                Element findAgain = Driver().FindField("containerLabeledTextInputFieldName", Root());
//                findAgain.Value, is(equalTo("New text input value");
//            }
//
//
//    @Test
//    public void Sets_value_of_textarea_field()
//
//            {
//                Element textField = Driver().FindField("containerLabeledTextareaFieldName", Root());
//                Driver().Set(textField, "New textarea value");
//
//                textField.Value, is(equalTo("New textarea value");
//
//                Element findAgain = Driver().FindField("containerLabeledTextareaFieldName", Root());
//                findAgain.Value, is(equalTo("New textarea value");
//            }
//
//
//    @Test
//    public void Selects_option_by_text_or_value()
//
//            {
//                Element textField = Driver().FindField("containerLabeledSelectFieldId", Root());
//                textField.Value, is(equalTo("select2value1");
//
//                Driver().Select(textField, "select two option two");
//
//                Element findAgain = Driver().FindField("containerLabeledSelectFieldId", Root());
//                findAgain.Value, is(equalTo("select2value2");
//
//                Driver().Select(textField, "select2value1");
//
//                Element andAgain = Driver().FindField("containerLabeledSelectFieldId", Root());
//                andAgain.Value, is(equalTo("select2value1");
//            }
//
//
//    @Test
//    public void Fires_change_event_when_selecting_an_option()
//
//            {
//                Element textField = Driver().FindField("containerLabeledSelectFieldId", Root());
//                textField.Name, is(equalTo("containerLabeledSelectFieldName");
//
//                Driver().Select(textField, "select two option two");
//
//                Driver().FindField("containerLabeledSelectFieldId", Root()).Name, is(equalTo("containerLabeledSelectFieldName - changed");
//            }
//
//
//        }
//    }
