package Coypu.Finders;

import Coypu.Options;
import Coypu.Robustness.RobustWrapper;
import Coypu.State;

public class StateFinder
{
    private RobustWrapper robustWrapper;

    public StateFinder(RobustWrapper robustWrapper)
    {
        this.robustWrapper = robustWrapper;
    }

    public State FindState(Options options, State[] states)
    {
        // TODO: Anonymous type rather than lambda
        var query = new LambdaPredicateQuery(() =>
        {
            var was = robustWrapper.ZeroTimeout;
            robustWrapper.ZeroTimeout = true;
            try
            {
                return ((Func<bool>)(() => states.Any(s => s.CheckCondition())))();
            }
            finally
            {
                robustWrapper.ZeroTimeout = was;
            }
        }, options);

        boolean foundState = robustWrapper.Robustly(query);

        if (!foundState)
            throw new MissingHtmlException("None of the given states was reached within the configured timeout.");

        return states.First(e => e.ConditionWasMet);
    }
}