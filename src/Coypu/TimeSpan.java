package Coypu;

public class TimeSpan
{
    private final long milliseconds;

    private TimeSpan(long milliseconds) {

        this.milliseconds = milliseconds;
    }

    public static TimeSpan FromSeconds(long seconds)
    {
        return new TimeSpan(seconds * 1000);
    }

    public static TimeSpan FromMilliseconds(long milliseconds)
    {
        return new TimeSpan(milliseconds);
    }

    public static TimeSpan Zero()
    {
        return new TimeSpan(0);
    }

    public long getMilliseconds()
    {
        return milliseconds;
    }
}

