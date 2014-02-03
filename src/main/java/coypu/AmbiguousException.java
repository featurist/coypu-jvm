//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:12
//

package coypu;


public class AmbiguousException  extends Exception 
{
    public AmbiguousException(String message) throws Exception {
        super(message);
    }

    public AmbiguousException(String message, Exception innerException) throws Exception {
        super(message, innerException);
    }

    public AmbiguousException(SerializationInfo info, StreamingContext context) throws Exception {
        super(info, context);
    }

}


