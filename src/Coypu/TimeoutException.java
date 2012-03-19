package Coypu;

public class TimeoutException extends Exception
{
    public TimeoutException(Exception inner)
    {
        super();
        super.initCause(inner);
    }
}
