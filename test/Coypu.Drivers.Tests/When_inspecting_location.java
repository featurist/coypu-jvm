//
//
//
//package Coypu.Drivers.Tests
//{
//    class When_inspecting_location extends DriverSpecs
//    {
//        @Test
//        public void Gets_the_current_browser_location()
//
//        {
//            Driver().Visit("http://localhost:4567");
//            Assert.That(Driver().Location, Is.EqualTo(new Uri("http://localhost:4567/")));
//
//            Driver().Visit("http://localhost:4567/auto_login");
//            Assert.That(Driver().Location, Is.EqualTo(new Uri("http://localhost:4567/auto_login")));
//        }
//
//
//        @Test
//        public void Not_just_when_set_by_visit()
//
//        {
//            Driver().Visit("http://localhost:4567/auto_login");
//            Driver().ExecuteScript("document.location.href = 'http://localhost:4567/resource/js_redirect'", Root());
//
//            Assert.That(Driver().Location, Is.EqualTo(new Uri("http://localhost:4567/resource/js_redirect")));
//        }
//    }
//}
