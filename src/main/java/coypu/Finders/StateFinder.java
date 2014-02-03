//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:17
//

package coypu.Finders;

import coypu.Options;
import coypu.Queries.LambdaPredicateQuery;
import coypu.Scope;
import coypu.State;
import coypu.Timing.TimingStrategy;

public class StateFinder   
{
    private final TimingStrategy timingStrategy;
    public StateFinder(TimingStrategy timingStrategy) throws Exception {
        this.timingStrategy = timingStrategy;
    }

    public State findState(State[] states, Scope scope, Options options) throws Exception {
        LambdaPredicateQuery query = new LambdaPredicateQuery(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "() => {
            Boolean was = timingStrategy.getZeroTimeout();
            timingStrategy.setZeroTimeout(true);
            try
            {
                return ((Func<Boolean>)(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "() => {
                    return states.Any(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(s) => {
                        return s.CheckCondition();
                    }" */);
                }" */))();
            }
            finally
            {
                timingStrategy.setZeroTimeout(was);
            }
        }" */, options);
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ foundState = timingStrategy.Synchronise(query);
        if (!foundState)
            throw new MissingHtmlException("None of the given states was reached within the configured timeout.");
         
        return states.First(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(e) => {
            return e.ConditionWasMet;
        }" */);
    }

}


