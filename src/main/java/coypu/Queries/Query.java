//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.Options;

public interface Query <TReturn>  
{
    TReturn run() throws Exception ;

    TReturn getExpectedResult() throws Exception ;

    Options getOptions() throws Exception ;

}


