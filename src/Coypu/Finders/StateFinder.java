package Coypu.Finders;

import Coypu.MissingHtmlException;
import Coypu.Options;
import Coypu.Queries.PredicateQuery;
import Coypu.Queries.Query;
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

    public State FindState(Options options, final State[] states) throws MissingHtmlException, TimeoutException {
        PredicateQuery query = new PredicateQuery(options) {
            @Override
            public void Run() throws MissingHtmlException, TimeoutException {
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

        throw new MissingHtmlException("None of the given states was reached within the configured timeout.");

    }
}
