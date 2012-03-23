package Coypu;

import Coypu.Queries.Query;

///<summary>
/// A possible state for the current page
///</summary>
public class State
{
    private Query<Boolean> condition;
    private boolean conditionWasMet;

    ///<summary>
    /// Describe a possible state for the page with a condition to identify this state.
    ///</summary>
    ///<param name="condition">How to identify this state</param>
    public State(Query<Boolean> condition)
    {
        this.condition = condition;
    }

    public boolean ConditionWasMet()
    {
        return conditionWasMet;
    }

    public boolean CheckCondition() throws MissingHtmlException, TimeoutException {
        condition.Run();
        return conditionWasMet = condition.Result();
    }
}
