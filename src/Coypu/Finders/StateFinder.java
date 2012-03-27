package Coypu.Finders;

import Coypu.Options;
import Coypu.Queries.PredicateQuery;
import Coypu.Robustness.RobustWrapper;
import Coypu.State;
import Coypu.TimeoutException;

public class StateFinder
{
    private RobustWrapper robustWrapper;

    public StateFinder(RobustWrapper robustWrapper)
    {
        this.robustWrapper = robustWrapper;
    }

    public State FindState(Options options, final State[] states) {
        PredicateQuery query = new PredicateQuery(options) {
            @Override
            public boolean Predicate()  {
                boolean was = robustWrapper.GetZeroTimeout();
                robustWrapper.SetZeroTimeout(true);
                try
                {
                    for (State state : states) {
                        if (state.CheckCondition())
                       return true;
                    }
                    return false;
                }
                finally
                {
                    robustWrapper.SetZeroTimeout(was);
                }
            }
        };
        
        robustWrapper.Robustly(query);

        for (State state : states) {
            if (state.ConditionWasMet())
               return state;
        }

        throw new TimeoutException("None of the given states was reached within the configured timeout.");

    }
}
