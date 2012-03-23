package Coypu;
/// <summary>
/// Thrown whenever some expected HTML cannot be found
/// </summary>
public class MissingHtmlException extends Exception
{
    public MissingHtmlException(String message)
    {
        super(message);
    }

}

