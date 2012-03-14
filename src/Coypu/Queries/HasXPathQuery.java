using System;

namespace Coypu.Queries
{
    internal class HasXPathQuery : DriverScopeQuery<Boolean>
    {
        private readonly Driver driver;
        private readonly string xpath;
        public override object ExpectedResult { get { return true; } }


        protected internal HasXPathQuery(Driver driver, DriverScope scope, string xpath, Options options)
            : base(scope, options)
        {
            this.driver = driver;
            this.driver = driver;
            this.xpath = xpath;
        }

        public override void Run()
        {
            Result = driver.HasXPath(xpath, DriverScope);
        }
    }
}