//
//
//package Coypu.Drivers.Tests
//{
//    class When_inspecting_dialog_text extends DriverSpecs
//    {
//        @Test
//        public void Finds_exact_text_in_alert()
//        {
//            using (Driver)
//            {
//                Driver().Click(Driver().FindLink("Trigger an alert", Root()));
//                Driver().HasDialog("You have triggered an alert and this is the text.", Root()), is(true);
//                Driver().HasDialog("You have triggered a different alert and this is the different text.", Root()), is(false);
//            }
//        }
//        @Test
//        public void Finds_exact_text_in_confirm()
//        {
//            using (Driver)
//            {
//                Driver().Click(Driver().FindLink("Trigger a confirm", Root()));
//                Driver().HasDialog("You have triggered a confirm and this is the text.", Root()), is(true);
//                Driver().HasDialog("You have triggered a different confirm and this is the different text.", Root()), is(false);
//            }
//        }
//    }
//}
