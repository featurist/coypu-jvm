using System;
using System.Text.RegularExpressions;

namespace Coypu.Queries
{
    internal class HasNoContentMatchQuery : DriverScopeQuery<Boolean>
    {
        private readonly Driver driver;
        private readonly Regex text;
        public override object ExpectedResult { get { return true; } }

        protected internal HasNoContentMatchQuery(Driver driver, DriverScope scope, Regex text, Options options)
            : base(scope, options)
        {
            this.driver = driver;
            this.text = text;
        }

        public override void Run()
        {
            Result = !driver.HasContentMatch(text, DriverScope);
        }    
    }
}