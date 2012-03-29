package Coypu;

/// <summary>
/// Options for how Coypu interacts with the browser.
/// </summary>
public class Options {
    final long DEFAULT_TIMEOUT_MILLISECONDS = 1000;
    final long DEFAULT_INTERVAL_MILLISECONDS = 100;

    /// <summary>
    /// New default options
    /// </summary>
    public Options() {
        Timeout = TimeSpan.FromMilliseconds(DEFAULT_TIMEOUT_MILLISECONDS);
        RetryInterval = TimeSpan.FromMilliseconds(DEFAULT_INTERVAL_MILLISECONDS);
        WaitBeforeClick = TimeSpan.Zero();
    }


    /// <summary>
    /// <para>When retrying, how long to wait for elements to appear or actions to complete without error.</para>
    /// <para>Default: 1sec</para>
    /// </summary>
    public TimeSpan Timeout;

    /// <summary>
    /// <para>How long to wait between retries</para>
    /// <para>Default: 100ms</para>
    /// </summary>
    public TimeSpan RetryInterval;

    /// <summary>
    /// <para>How long to wait between finding an element and clicking it.</para>
    /// <para>Default: zero</para>
    /// </summary>
    public TimeSpan WaitBeforeClick;

    /// <summary>
    /// <para>By default Coypu will exclude any invisible elements, this allows you to override that behaviour</para>
    /// <para>Default: true</para>
    /// </summary>
    public boolean ConsiderInvisibleElements;
}

