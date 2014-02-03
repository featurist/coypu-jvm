//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.Options;

public class LambdaPredicateQuery  extends PredicateQuery 
{
    private final Func<Boolean> query = new Func<Boolean>();
    public LambdaPredicateQuery(Func<Boolean> query, Options options) throws Exception {
        super(options);
        this.query = query;
    }

    public boolean predicate() throws Exception {
        return query();
    }

}


