package coypu;

/// <summary>
/// An element found by a coypu Driver
/// </summary>
public interface ElementFound extends Element {
    /// <summary>
    /// The native element is no longer attached to the DOM
    /// </summary>
    public boolean stale();
}
