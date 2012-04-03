package coypu;

/// <summary>
/// Options for how coypu interacts with the browser.
///
public class Options {
    final long DEFAULT_TIMEOUT_MILLISECONDS = 1000;
    final long DEFAULT_INTERVAL_MILLISECONDS = 100;

   /**
    *  New default options
    */
    public Options() {
        Timeout = TimeSpan.fromMilliseconds(DEFAULT_TIMEOUT_MILLISECONDS);
        RetryInterval = TimeSpan.fromMilliseconds(DEFAULT_INTERVAL_MILLISECONDS);
        WaitBeforeClick = TimeSpan.zero();
    }


   /**
    *  When retrying, how long to wait for elements to appear or actions to complete without error.
    *  <p>Default: 1sec
    */
    public TimeSpan Timeout;

   /**
    *  How long to wait between retries
    *  <p>Default: 100ms
    */
    public TimeSpan RetryInterval;

   /**
    *  How long to wait between finding an element and clicking it.
    *  <p>Default: zero
    */
    public TimeSpan WaitBeforeClick;

   /**
    *  By default Coypu will exclude any invisible elements, this allows you to override that behaviour
    *  <p>Default: true
    */
    public boolean ConsiderInvisibleElements;
}

