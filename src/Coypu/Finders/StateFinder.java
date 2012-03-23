package Coypu.Finders;

import Coypu.MissingHtmlException;
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

    public State FindState(Options options, final State[] states) throws TimeoutException, MissingHtmlException {
        PredicateQuery query = new PredicateQuery(options) {
            @Override
            public void Run() throws MissingHtmlException {
                boolean was = robustWrapper.GetZeroTimeout();
                robustWrapper.SetZeroTimeout(true);
                try
                {
                    for (State state : states) {
                        if (state.CheckCondition())
                       result = true;
                       return;
                    }
                    result = false;
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
