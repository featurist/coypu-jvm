package coypu.unitTests.TestDoubles;

public interface Action<T> {
    public void invoke(T on);
}