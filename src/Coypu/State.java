package Coypu;

import Coypu.Queries.PredicateQuery;

///<summary>
/// A possible state for the current page
///</summary>
public class State
{
    private PredicateQuery condition;
    private boolean conditionWasMet;

    ///<summary>
    /// Describe a possible state for the page with a condition to identify this state.
    ///</summary>
    ///<param name="condition">How to identify this state</param>
    public State(PredicateQuery condition)
    {
        this.condition = condition;
    }

    public boolean ConditionWasMet()
    {
        return conditionWasMet;
    }

    public boolean CheckCondition()  {
        condition.Run();
        return conditionWasMet = condition.Result();
    }
}
