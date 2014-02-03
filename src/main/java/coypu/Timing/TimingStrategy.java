//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:19
//

package coypu.Timing;

import coypu.Actions.BrowserAction;
import coypu.Options;
import coypu.Queries.PredicateQuery;
import coypu.Queries.Query;
import CS2JNet.System.TimeSpan;

public interface TimingStrategy   
{
    <T>T synchronise(Query<T> query) throws Exception ;

    void tryUntil(BrowserAction tryThis, PredicateQuery until, Options options) throws Exception ;

    boolean getZeroTimeout() throws Exception ;

    void setZeroTimeout(boolean value) throws Exception ;

    void setOverrideTimeout(TimeSpan timeout) throws Exception ;

    void clearOverrideTimeout() throws Exception ;

}


