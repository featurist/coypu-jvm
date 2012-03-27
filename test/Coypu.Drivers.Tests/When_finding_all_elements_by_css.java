



package Coypu.Drivers.Tests
{
    class When_finding_all_elements_by_css extends DriverSpecs
    {

  @Test
  public void Returns_empty_if_no_matches()
 
            {
                const string shouldNotFind = "#inspectingContent p.css-missing-test";
                Assert.That(Driver().FindAllCss(shouldNotFind,Root()), Is.Empty);
            }

            
    @Test
    public void Returns_all_matches_by_css()
  
            {
                const string shouldFind = "#inspectingContent ul#cssTest li";
                var all = Driver().FindAllCss(shouldFind,Root());
                all.Count(), is(equalTo(3);
                all.ElementAt(1).Text, is(equalTo("two");
                all.ElementAt(2).Text, is(equalTo("Me! Pick me!");
            }
        }
}