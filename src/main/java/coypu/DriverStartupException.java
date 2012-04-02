package coypu;

public class DriverStartupException extends RuntimeException {
    public DriverStartupException(Throwable inner) {
        super(inner);
    }
}
