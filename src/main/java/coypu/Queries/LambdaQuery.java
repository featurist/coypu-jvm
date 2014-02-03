//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:18
//

package coypu.Queries;

import coypu.Options;

public class LambdaQuery <T>  implements Query<T>
{
    private final Func<T> _query = new Func<T>();
    private Options __Options;
    public Options getOptions() {
        return __Options;
    }

    public void setOptions(Options value) {
        __Options = value;
    }

    private T __ExpectedResult;
    public T getExpectedResult() {
        return __ExpectedResult;
    }

    public void setExpectedResult(T value) {
        __ExpectedResult = value;
    }

    public LambdaQuery(Func<T> query) throws Exception {
        _query = query;
    }

    public LambdaQuery(Func<T> query, Options options) throws Exception {
        this(query);
        setOptions(options);
    }

    public LambdaQuery(Func<T> query, T expectedResult, Options options) throws Exception {
        this(query, options);
        setExpectedResult(expectedResult);
    }

    public T run() throws Exception {
        return _query();
    }

}


