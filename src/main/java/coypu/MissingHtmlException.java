//
// Translated by CS2J (http://www.cs2j.com): 03/02/2014 09:15:17
//

package coypu;


/**
* Thrown whenever some expected HTML cannot be found
*/
public class MissingHtmlException  extends Exception 
{
    public MissingHtmlException(String message) throws Exception {
        super(message);
    }

    public MissingHtmlException(String message, Exception innerException) throws Exception {
        super(message, innerException);
    }

    public MissingHtmlException(SerializationInfo info, StreamingContext context) throws Exception {
        super(info, context);
    }

}


