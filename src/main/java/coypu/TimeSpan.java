package coypu;

public class TimeSpan {
    private final long milliseconds;

    private TimeSpan(long milliseconds) {

        this.milliseconds = milliseconds;
    }

    public static TimeSpan fromSeconds(long seconds) {
        return new TimeSpan(seconds * 1000);
    }

    public static TimeSpan fromMilliseconds(long milliseconds) {
        return new TimeSpan(milliseconds);
    }

    public static TimeSpan zero() {
        return new TimeSpan(0);
    }

    public long getMilliseconds() {
        return milliseconds;
    }
}

