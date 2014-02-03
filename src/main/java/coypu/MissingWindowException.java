//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:17
//

package coypu;


/**
* Thrown whenever an expected browser window cannot be found
*/
public class MissingWindowException  extends Exception 
{
    public MissingWindowException(String message) throws Exception {
        super(message);
    }

    public MissingWindowException(String message, Exception innerException) throws Exception {
        super(message, innerException);
    }

    public MissingWindowException(SerializationInfo info, StreamingContext context) throws Exception {
        super(info, context);
    }

}


