package coypu;

/**
* Thrown whenever some expected HTML cannot be found
*/
public class MissingHtmlException extends RuntimeException {
    public MissingHtmlException(String message) {
        super(message);
    }

}

