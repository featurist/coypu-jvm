package Coypu;

public class TimeSpan
{
    private final double milliseconds;

    private TimeSpan(double milliseconds) {

        this.milliseconds = milliseconds;
    }

    public static TimeSpan FromSeconds(double seconds)
    {
        return new TimeSpan(seconds / 1000);
    }

    public static TimeSpan FromMilliseconds(double milliseconds)
    {
        return new TimeSpan(milliseconds);
    }

    public static TimeSpan Zero() {
        return new TimeSpan(0);
    }
}
