package Coypu;
/// <summary>
/// Options for how Coypu interacts with the browser.
/// </summary>
public class Options
{
    final double DEFAULT_TIMEOUT_SECONDS = 1;
    final double DEFAULT_INTERVAL_SECONDS = 0.1;

    /// <summary>
    /// New default options
    /// </summary>
    public Options()
    {
        Timeout = DEFAULT_TIMEOUT_SECONDS;
        RetryInterval = DEFAULT_INTERVAL_SECONDS;
        WaitBeforeClick = 0;
    }


    /// <summary>
    /// <para>When retrying, how long to wait for elements to appear or actions to complete without error.</para>
    /// <para>Default: 1sec</para>
    /// </summary>
    public double Timeout;

    /// <summary>
    /// <para>How long to wait between retries</para>
    /// <para>Default: 100ms</para>
    /// </summary>
    public double RetryInterval;

    /// <summary>
    /// <para>How long to wait between finding an element and clicking it.</para>
    /// <para>Default: zero</para>
    /// </summary>
    public double WaitBeforeClick;

    /// <summary>
    /// <para>By default Coypu will exclude any invisible elements, this allows you to override that behaviour</para>
    /// <para>Default: true</para>
    /// </summary>
    public boolean ConsiderInvisibleElements;
}
