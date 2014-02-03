//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu;

import coypu.Options;
import coypu.Queries.LambdaQuery;
import coypu.Queries.Query;
import CS2JNet.System.TimeSpan;

/**
* A possible state for the current page
*/
public class State   
{
    private final Query<Boolean> condition;
    /**
    * Describe a possible state for the page with a condition to identify this state.
    * 
    *  @param condition How to identify this state
    */
    public State(Query<Boolean> condition) throws Exception {
        this.condition = condition;
    }

    /**
    * Describe a possible state for the page with a condition to identify this state.
    * 
    *  @param condition How to identify this state
    */
    public State(Func<Boolean> condition) throws Exception {
        this.condition = new LambdaQuery<Boolean>(condition, true, new Options());
    }

    private boolean __ConditionWasMet;
    public boolean getConditionWasMet() {
        return __ConditionWasMet;
    }

    public void setConditionWasMet(boolean value) {
        __ConditionWasMet = value;
    }

    public boolean checkCondition() throws Exception {
        return setConditionWasMet(condition.run());
    }

}


